package com.globalsoftwaresupport.ui;

import com.globalsoftwaresupport.bean.FormularioBe;
import com.globalsoftwaresupport.dao.FormularioDao;
import com.globalsoftwaresupport.spring.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

/**
 *
 * @author 06551256M
 */
public class FormularioNuevo extends Dialog {
    private final FormLayout formLayout = new FormLayout();
    private final IntegerField id = Objetosui.getIntegerField("Id");
    private final TextField nombre = Objetosui.getTextField("Nombre");
    private final RadioButtonGroup<String> tieneImpreso = Objetosui.getTieneImpresoRadio();
    private final Button grabar = Objetosui.getButton("Grabar");
    private final Button cancelar = Objetosui.getButton("Cancelar");
    private final Binder<FormularioBe> binder = new Binder<>();
    private FormularioBe formularioBe = null;

    public FormularioNuevo() {
        grabar.addClickListener(event -> {
            doGrabar();
        });
        cancelar.addClickListener(event -> {

            this.close();
        });

        binder.forField(id)
                .bind(FormularioBe::getId, null);

        binder.forField(nombre)
                .asRequired().bind(FormularioBe::getNombre, FormularioBe::setNombre);

        binder.forField(tieneImpreso).asRequired()
                .asRequired().bind(FormularioBe::getTieneImpreso, FormularioBe::setTieneImpreso);

        formLayout.add(id, tieneImpreso, nombre, grabar, cancelar);

        formLayout.setResponsiveSteps(new ResponsiveStep("0", 2));
        formLayout.setColspan(nombre, 2);

        this.add(formLayout);
    }

    /**
     *
     */
    public void doGrabar() {
        formularioBe = new FormularioBe(new FormularioDao().getSiguienteIdFormulario());
        if (new FormularioDao().getPorNobre(nombre.getValue()) == null) {
            try {
                binder.writeBean(formularioBe);
                MainView.FORMULARIOS.put(formularioBe.getId(), formularioBe);
                this.close();
            } catch (Exception e) {
                Notification.show("Error validando ", 3000, Notification.Position.MIDDLE);
            }
        }
    }

    public FormularioBe getFormularioBe() {
        return formularioBe;
    }

}
