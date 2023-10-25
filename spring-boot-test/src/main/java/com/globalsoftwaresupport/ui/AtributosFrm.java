package com.globalsoftwaresupport.ui;

import com.globalsoftwaresupport.bean.AtributoBe;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author 06551256M
 */
public class AtributosFrm extends Dialog {

    AtributoBe atributoBe = new AtributoBe();
    FormLayout formulario = new FormLayout();

    public AtributosFrm(AtributoBe atributoBeParam) {
        this.add(formulario);
        this.atributoBe = atributoBeParam;
        TextField atributo = new TextField("Atributo");
        atributo.setPlaceholder("nombre atributo");
        if (atributoBe.getAtributo() != null) {
            atributo.setValue(atributoBe.getAtributo());
        }
        atributo.setEnabled(false);

        TextField valor = new TextField("Valor");
        valor.setPlaceholder("valor atributo");
        valor.focus();
        if (atributoBe != null) {
            valor.setValue(atributoBe.getValor());
        }
        Button grabar = new Button("Grabar");
        grabar.addClickListener(event -> {
            atributoBe.setValor(valor.getValue());
            this.close();
        });
        formulario.add(atributo, valor, grabar);
    }

    public AtributoBe getAtributoBe() {
        return atributoBe;
    }

}
