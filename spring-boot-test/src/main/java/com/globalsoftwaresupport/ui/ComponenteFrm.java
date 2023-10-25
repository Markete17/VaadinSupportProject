package com.globalsoftwaresupport.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.globalsoftwaresupport.bean.AtributoBe;
import com.globalsoftwaresupport.bean.CampoBe;
import com.globalsoftwaresupport.bean.ComponenteBe;
import com.globalsoftwaresupport.bean.DependenciaBe;
import com.globalsoftwaresupport.bean.FormularioBe;
import com.globalsoftwaresupport.dao.CamposFactory;
import com.globalsoftwaresupport.util.Utilidades;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import lombok.extern.log4j.Log4j2;

/**
 *
 * @author 06551256M
 */
@Log4j2
public class ComponenteFrm extends Dialog {

    private final VerticalLayout contenedorVentana = new VerticalLayout();
    private final FormLayout formularioLayaout = new FormLayout();

    private final ComboBox<String> campoCombo = Objetosui.getComboCampo();
    private final TextField descripcion = Objetosui.getTextField("Descripcion");
    private final IntegerField orden = new IntegerField("Orden");
    private final TextField code = Objetosui.getTextField("Code");
    private final ComboBox<String> codesystem = Objetosui.getComboCodeSystem();
    private final TextArea valores = Objetosui.getTextArea("Valores");

    private final Button cancelar = Objetosui.getButton("Cancela");
    private final Button grabar = Objetosui.getButton("Grabar ");
    private final Button predefinidos = Objetosui.getButton("Predefinidos ");
    private final Button dependencias = Objetosui.getButton("Dependencias ");

    private ComboBox<ComponenteBe> padresCombo = null;
    private ComponenteBe componenteBe = null;

    private Binder<ComponenteBe> binder = new Binder<>();

    private HashMap<String, AtributoBe> hashMapAtributos = new HashMap<>();

    private TabSheet tabSheet = new TabSheet();

    private FormularioBe formularioBe = null;

    private Grid<AtributoBe> gridAtributo = Objetosui.getGridatributos();

    private Grid<DependenciaBe> gridDependencia = Objetosui.getGridDependencias();

    public ComponenteFrm(FormularioBe formularioBeparam, ComponenteBe componenteBeParam) {
        this.setMaxWidth("900px");
        try {
            this.formularioBe = formularioBeparam;
            this.componenteBe = componenteBeParam;

            tabSheet.setWidthFull();
            tabSheet.addThemeVariants(TabSheetVariant.LUMO_BORDERED);
            tabSheet.add("Atributos", gridAtributo);
            tabSheet.add("Dependencias", gridDependencia);

            this.add(contenedorVentana);
            contenedorVentana.add(new H3(formularioBe.getNombre()), formularioLayaout, tabSheet);
            padresCombo = Objetosui.getComboPadres(formularioBe);
            formularioLayaout.setResponsiveSteps(new ResponsiveStep("0", 4),
                    new ResponsiveStep("500px", 4));
            formularioLayaout.setColspan(valores, 4);

            formularioLayaout.add(campoCombo, descripcion, orden, code, codesystem, padresCombo, valores,
                    cancelar, grabar, predefinidos, dependencias);

            binder.forField(descripcion).asRequired().withNullRepresentation("").bind(ComponenteBe::getDescripcion, ComponenteBe::setDescripcion);
            //   binder.forField(campoCombo).asRequired().withNullRepresentation(new CampoBe()).bind(ComponenteBe::getCampo, ComponenteBe::setCampo);
            binder.forField(orden).bind(ComponenteBe::getOrden, ComponenteBe::setOrden);
            binder.forField(code).withNullRepresentation("").bind(ComponenteBe::getCode, ComponenteBe::setCode);
            binder.forField(codesystem).withNullRepresentation("").bind(ComponenteBe::getCodesystem, ComponenteBe::setCodesystem);
            binder.forField(padresCombo).bind(ComponenteBe::getPertenece, ComponenteBe::setPertenece);
            binder.forField(valores).bind(ComponenteBe::getValores, ComponenteBe::setValores);

            binder.readBean(componenteBe);
            if (componenteBe.getCampo() != null && componenteBe.getCampo().getCampo() != null) {
                campoCombo.setValue(componenteBe.getCampo().getCampo());
                doEventoComboCampo();
            }

            // eventos
            campoCombo.addValueChangeListener(event -> {
                doEventoComboCampo();
            });

            cancelar.addClickListener(event -> {
                componenteBe = null;
                this.close();
            });

            grabar.addClickListener(event -> {
                doGrabar();
            });

            predefinidos.addClickListener(event -> {
                AyudaComponentes ayudaComponentes = new AyudaComponentes();
                ayudaComponentes.addDetachListener(event1 -> {
                    if (ayudaComponentes.getComponenteBe() != null) {
                        ComponenteBe compo = ayudaComponentes.getComponenteBe();
                        if (compo.getCampo() != null) {
                            try {
                                binder.readBean(compo);
                                if (compo != null && compo.getCampo() != null && compo.getCampo().getCampo() != null) {
                                    campoCombo.setValue(compo.getCampo().getCampo());
                                }
                                binder.writeBean(componenteBe);
                            } catch (Exception e) {
                                Notification.show("Error bind predefinido", 4000, Notification.Position.MIDDLE);
                            }
                            componenteBe.setCampo(buildCampo(compo.getCampo().getCampo()));
                        }
                    }
                });
                ayudaComponentes.open();
            });

            gridAtributo.addItemClickListener(event -> {
                AtributosFrm atributosFrm = new AtributosFrm(event.getItem());
                atributosFrm.addDetachListener(event1 -> {
                    AtributoBe attr = atributosFrm.getAtributoBe();
                    hashMapAtributos.put(attr.getAtributo(), attr);
                    gridAtributo.setItems(hashMapAtributos.values());
                });

                atributosFrm.open();
            });

            dependencias.addClickListener(event -> {
                DependenciaBe dependenciaBe = new DependenciaBe();
                dependenciaBe.setIdComponente(componenteBe.getId());
                DependenciaNueva dependenciaNueva = new DependenciaNueva(componenteBe, dependenciaBe);
                dependenciaNueva.addDetachListener(event1 -> {
                    if (dependenciaNueva.getDependenciaBe() != null) {
                        componenteBe.getDependencias().put(dependenciaNueva.getDependenciaBe().getId(), dependenciaNueva.getDependenciaBe());
                        gridDependencia.setItems(componenteBe.getDependencias().values());
                    }
                });
                dependenciaNueva.open();
            });

        } catch (Exception e) {
            Notification.show(e.getMessage(), 3000, Notification.Position.MIDDLE);
            log.error(Utilidades.getStackTrace(e));
        }
    }

    public void doEventoComboCampo() {
        descripcion.focus();
        if (campoCombo.getValue() != null) {
        componenteBe.setCampo(buildCampo(campoCombo.getValue()));
        hashMapAtributos = new HashMap<>();

        for (AtributoBe att : componenteBe.getCampo().getAtributos()) {
            hashMapAtributos.put(att.getAtributo(), att);
        }
        gridAtributo.setItems(new ArrayList<>(hashMapAtributos.values()));

        if (componenteBe.getCampo().getTipo().equals(CampoBe.TIPO_CONTENEDOR)) {
            code.setEnabled(false);
            code.clear();
            codesystem.setEnabled(false);
            codesystem.clear();
            padresCombo.setEnabled(false);
            padresCombo.clear();
            valores.setEnabled(false);
            valores.clear();
            orden.clear();
            orden.setEnabled(false);
        } else {
            code.setEnabled(true);
            codesystem.setEnabled(true);
            padresCombo.setEnabled(true);
            valores.setEnabled(true);
            orden.setEnabled(true);
            }
        } else {
            hashMapAtributos = new HashMap<>();
            gridAtributo.setItems(new ArrayList<>(hashMapAtributos.values()));
        }
    }

    public void doGridAtributos() {
        gridAtributo.addColumn(AtributoBe::getAtributo).setHeader("Atributo");
        gridAtributo.addColumn(AtributoBe::getValor).setHeader("Valor");
        gridAtributo.addItemClickListener(event -> {
            AtributosFrm atributosFrm = new AtributosFrm(event.getItem());
            atributosFrm.addDetachListener(event1 -> {
                AtributoBe attr = atributosFrm.getAtributoBe();
                hashMapAtributos.put(attr.getAtributo(), attr);
                gridAtributo.setItems(hashMapAtributos.values());
            });

            atributosFrm.open();
        });
    }


    public void doGrabar() {
        try {
            if (haValidado() == true) {
                binder.writeBean(componenteBe);
                //    componenteBe.setIdFormulario(formularioBe.getId());
                //    componenteBe.setId(formularioBe.getSiguienteIdComponente());
                componenteBe.getCampo().setAtributos(new ArrayList<>(hashMapAtributos.values()));
                this.close();
            }
        } catch (Exception e) {
            Notification.show("Error de validación", 4000, Notification.Position.MIDDLE);
            log.error(Utilidades.getStackTrace(e));
        }
    }

    public CampoBe buildCampo(String nombre) {
        CampoBe campo = null;
        switch (nombre) {
            case CampoBe.AREA:
                campo = CamposFactory.getCampo(CampoBe.AREA, CampoBe.AREA_ATTR);
                break;
            case CampoBe.CHECK:
                campo = CamposFactory.getCampo(CampoBe.CHECK, CampoBe.CHECK_ATTR);
                break;
            case CampoBe.COLUMNA:
                campo = CamposFactory.getCampo(CampoBe.COLUMNA, CampoBe.COLUMNA_ATTR);
                break;
            case CampoBe.COMBO:
                campo = CamposFactory.getCampo(CampoBe.COMBO, CampoBe.COMBO_ATTR);
                break;
            case CampoBe.FECHA:
                campo = CamposFactory.getCampo(CampoBe.FECHA, CampoBe.FECHA_ATTR);
                break;
            case CampoBe.FILA:
                campo = CamposFactory.getCampo(CampoBe.FILA, CampoBe.FILA_ATTR);
                break;
            case CampoBe.TEXTO:
                campo = CamposFactory.getCampo(CampoBe.TEXTO, CampoBe.TEXTO_ATTR);
                break;
            case CampoBe.RADIO:
                campo = CamposFactory.getCampo(CampoBe.RADIO, CampoBe.RADIO_ATTR);
                break;
            default:
                Notification.show("Tipo de campo no definido ", 4000, Notification.Position.MIDDLE);
        }
        return campo;
    }

    public ComponenteBe getComponenteBe() {
        return componenteBe;
    }

    public Boolean haValidado() {
        Boolean valido = true;
        if (componenteBe.getCampo().getTipo().equals(CampoBe.TIPO_CONTENEDOR)) {
            if (padresCombo.getValue() != null) {
                Notification.show(" No es recursivo ", 3000, Notification.Position.MIDDLE);
                padresCombo.clear();
                valido = false;
            }
        } else if (componenteBe.getCampo().getTipo().equals(CampoBe.TIPO_INPUT)) {
            if (padresCombo.getValue() == null) {
                Notification.show(" Necesita conetenedor   ", 3000, Notification.Position.MIDDLE);
                padresCombo.focus();
                valido = false;
            }
            if (componenteBe.getCampo().getCampo().equals(CampoBe.COMBO) || componenteBe.getCampo().getCampo().equals(CampoBe.CHECK)) {
                if (valores.getValue() == null) {
                    Notification.show("Son necesarios valores ", 3000, Notification.Position.MIDDLE);
                    valores.focus();
                    valido = false;
                } else {
                    if (!valores.getValue().contains(",")) {
                        Notification.show("Los valores separados por comas ", 3000, Notification.Position.MIDDLE);
                        valido = false;
                    }
                }
            }
        }
        return valido;
    }
}
