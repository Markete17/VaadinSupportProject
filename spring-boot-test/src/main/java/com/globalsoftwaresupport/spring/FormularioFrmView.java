package com.globalsoftwaresupport.spring;

import java.util.ArrayList;
import java.util.HashMap;

import com.globalsoftwaresupport.bean.ComponenteBe;
import com.globalsoftwaresupport.bean.FormularioBe;
import com.globalsoftwaresupport.dao.FormularioDao;
import com.globalsoftwaresupport.ui.ComponenteFrm;
import com.globalsoftwaresupport.ui.CreaFormularios;
import com.globalsoftwaresupport.ui.FormularioNuevo;
import com.globalsoftwaresupport.ui.Objetosui;
import com.globalsoftwaresupport.util.Utilidades;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;

import lombok.extern.log4j.Log4j2;

/**
 *
 * @author 06551256M
 */
@Route(value = "editar-formularios", layout = MainView.class)
@Log4j2
@CssImport("./styles/styles.css")
public class FormularioFrmView extends HorizontalLayout {

    private final VerticalLayout izquierda = new VerticalLayout();
    private final HorizontalLayout filaFormularios = new HorizontalLayout();

    private final VerticalLayout derecha = new VerticalLayout();

    private final HashMap<Integer, ComponenteBe> hasHMapComponente = new HashMap<>();

    private final ComboBox<FormularioBe> formularioComboBox = Objetosui.getComboFormulario();

    private final TreeGrid<ComponenteBe> componenteTree = Objetosui.getTreeGridComponentes();

    private FormularioBe formularioBe = null;

    private Button anadirFormulari = Objetosui.getButton("Nuevo Formulario");

    public FormularioFrmView() {
        this.add(izquierda, derecha);

        // el combo de los formularios
        formularioBe = formularioComboBox.getValue();

        formularioComboBox.setLabel("");
        formularioComboBox.addValueChangeListener(event -> {
            if (formularioComboBox.getValue() != null) {
                formularioBe = event.getValue();
                componenteTree.setItems(new FormularioDao().getPadres(formularioBe), ComponenteBe::getHijos);

                formularioComboBox.focus();
                doPintaFormulario();
            }
        });

        anadirFormulari.addClickListener(even -> {
            FormularioNuevo formularioNuevo = new FormularioNuevo();
            formularioNuevo.addDetachListener(event1 -> {
                if (formularioNuevo.getFormularioBe() != null) {
                    formularioComboBox.setItems(MainView.FORMULARIOS.values());
                    doGrabarDatos();
                }
            });
            formularioNuevo.addDialogCloseActionListener(event2 -> {
                if (formularioNuevo.getFormularioBe() != null) {
                    MainView.FORMULARIOS.put(formularioNuevo.getFormularioBe().getId(), formularioNuevo.getFormularioBe());
                    formularioComboBox.setItems(MainView.FORMULARIOS.values());
                    doGrabarDatos();
                }
            });
            formularioNuevo.open();

        });


        Button anadir = new Button("Añadir componente al formulario");
        anadir.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        anadir.addClickListener(event -> {
            if (formularioComboBox.getValue() != null) {
                ComponenteBe componenteBe = new ComponenteBe();
                componenteBe.setIdFormulario(formularioBe.getId());
                componenteBe.setId(formularioBe.getSiguienteIdComponente());
                doFormularioComponente(componenteBe);
            } else {
                Notification.show("Elige formualrio", 4000, Notification.Position.MIDDLE);
            }
        });

        filaFormularios.add(formularioComboBox, anadirFormulari, anadir);

        if (formularioBe != null && !formularioBe.getComponentes().isEmpty()) {
            componenteTree.expand(new FormularioDao().getPadres(formularioBe));
        }
        componenteTree.setItems(hasHMapComponente.values(), this::getPadre);
        componenteTree.addComponentColumn(compoenete -> Objetosui.getButtonGrid(() -> {
            this.doBorrar();
        }, VaadinIcon.TRASH))
                .setHeader("Borra");

        componenteTree.addComponentColumn(compoenete -> Objetosui.getButtonGrid(() -> {
            this.doFormularioComponente(compoenete);
        }, VaadinIcon.EDIT))
                .setHeader("Edita");
        componenteTree.setClassNameGenerator(compo -> {
            if (Utilidades.esPar(compo.getId())) {
                return "line-bean-baja";
            } else {
                return "my-style-1";
            }
        });

        izquierda.add(filaFormularios, componenteTree);

    }

    public void doBorrar() {

    }

    public ArrayList<ComponenteBe> getPadre(ComponenteBe componenteBep) {
        return new FormularioDao().getHijos(formularioBe, componenteBep);
    }

    /**
     *
     * @param componenteBe
     */
    public void doFormularioComponente(ComponenteBe componenteBe) {
        ComponenteFrm datosFrm = new ComponenteFrm(formularioBe, componenteBe);

        datosFrm.addDetachListener(event1 -> {
            if (datosFrm.getComponenteBe() != null) {
                ComponenteBe componenteBe1 = datosFrm.getComponenteBe();
                formularioBe.getComponentes().put(componenteBe1.getId(), componenteBe1);
                componenteTree.setItems(new FormularioDao().getPadres(formularioBe), ComponenteBe::getHijos);
                doGrabarDatos();
                doPintaFormulario();
            }
        });
        /*
        datosFrm.addDialogCloseActionListener(event2 -> {
            if (datosFrm.getComponenteBe() != null) {
                hasHMapComponente.put(datosFrm.getComponenteBe().getId(), datosFrm.getComponenteBe());
                componenteTree.setItems(new FormularioDao().getPadres(formularioBe), ComponenteBe::getHijos);
                componenteTree.expand(new FormularioDao().getPadres(formularioBe));
                formularioBe.setComponentes(hasHMapComponente);
                doGrabarDatos();
                doPintaFormulario();
            }
        });
         */
        datosFrm.open();
    }

    public void doPintaFormulario() {
        derecha.removeAll();
        derecha.add(new CreaFormularios(formularioBe));

    }

    /**
     *
     */
    public void doGrabarDatos() {

        if (new FormularioDao().haGrabado(MainView.FORMULARIOS.values()) == true) {

        } else {
            Notification.show("Error grabando dato", 5000, Notification.Position.MIDDLE);
        }

    }
}
