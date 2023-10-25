package com.globalsoftwaresupport.ui;

import com.globalsoftwaresupport.bean.ComponenteBe;
import com.globalsoftwaresupport.bean.DependenciaBe;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

/**
 *
 * @author 06551256M
 */
public class DependenciaNueva extends Dialog {

    private final FormLayout formLayout = new FormLayout();
    private final IntegerField id = Objetosui.getIntegerField("Id");
    private final ComboBox<String> tipo = Objetosui.getComboTipoDependencias();
    private final TextField condicion = Objetosui.getTextField("Condición");
    private final TextField valores = Objetosui.getTextField("Valores");
    private final TextArea dependencia = Objetosui.getTextArea("Definición de dependencia");

    private final Button grabar = Objetosui.getButton("Grabar");
    private final Button cancelar = Objetosui.getButton("Cancelar");
    private final Binder<DependenciaBe> binder = new Binder<>();
    private DependenciaBe dependenciaBe = null;

    public DependenciaNueva(ComponenteBe componenteBe, DependenciaBe dependenciaBeparam) {

        this.dependenciaBe = dependenciaBeparam;

        grabar.addClickListener(event -> {
            doGrabar();
        });
        cancelar.addClickListener(event -> {
            dependenciaBe = null;
            this.close();
        });

        binder.forField(id)
                .bind(DependenciaBe::getId, null);

        binder.forField(tipo).asRequired().bind(DependenciaBe::getTipo, DependenciaBe::setTipo);
        binder.forField(condicion).asRequired().bind(DependenciaBe::getCondicion, DependenciaBe::setCondicion);
        binder.forField(valores).asRequired().bind(DependenciaBe::getValores, DependenciaBe::setValores);
        binder.forField(dependencia).asRequired().bind(DependenciaBe::getDependencias, DependenciaBe::setDependencias);

        formLayout.add(id, tipo, condicion, valores, dependencia, grabar, cancelar);

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        formLayout.setColspan(valores, 2);
        formLayout.setColspan(condicion, 2);

        this.add(formLayout);


    }

    public void doGrabar() {
        try {
            binder.writeBean(dependenciaBe);
            this.close();
        } catch (Exception e) {
            Notification.show("Error validando ", 3000, Notification.Position.MIDDLE);
        }
    }

    public DependenciaBe getDependenciaBe() {
        return dependenciaBe;
    }

}
