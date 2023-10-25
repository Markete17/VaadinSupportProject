package com.globalsoftwaresupport.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.globalsoftwaresupport.bean.AtributoBe;
import com.globalsoftwaresupport.bean.CampoBe;
import com.globalsoftwaresupport.bean.ComponenteBe;
import com.globalsoftwaresupport.bean.FormularioBe;
import com.globalsoftwaresupport.dao.FormularioDao;
import com.globalsoftwaresupport.util.Utilidades;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author 06551256M
 */
public class CreaFormularios extends VerticalLayout {

    public CreaFormularios(FormularioBe formularioBe) {
        this.add(new H1(formularioBe.getNombre()));
        for (ComponenteBe co : new FormularioDao().getPadres(formularioBe)) {
            if (co.getCampo().getCampo().equals(CampoBe.FILA)) {
                HorizontalLayout h = new HorizontalLayout();
                for (AtributoBe atributo : co.getCampo().getAtributos()) {
                    if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                        switch (atributo.getAtributo()) {
                            case CampoBe.COLORFONDO:
                                // h.getStyle().set("background-color", "#FF5733");
                                h.getStyle().set("background-color", atributo.getValor());
                                break;
                        }
                    }
                }
                for (ComponenteBe cohijo : new FormularioDao().getHijos(formularioBe, co)) {
                    if (cohijo.getCampo().getCampo().equals(CampoBe.FECHA)) {
                        DatePicker date = new DatePicker();
                        date.setLabel(cohijo.getDescripcion());
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                            switch (atributo.getAtributo()) {
                                case CampoBe.ANCHO:
                                    date.setWidth(atributo.getValor());
                                    date.setMaxWidth(atributo.getValor());
                                    date.setMinWidth(atributo.getValor());
                                    break;
                                }
                            }
                        }
                        h.add(date);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.TEXTO)) {
                        TextField text = new TextField();
                        text.setLabel(cohijo.getDescripcion());
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                            switch (atributo.getAtributo()) {
                                case CampoBe.ANCHO:
                                    text.setWidth(atributo.getValor());
                                    text.setMaxWidth(atributo.getValor());
                                    text.setMinWidth(atributo.getValor());
                                    break;
                                case CampoBe.ALTO:
                                    text.setHeight(atributo.getValor());
                                    text.setMaxHeight(atributo.getValor());
                                    text.setMinHeight(atributo.getValor());
                                    break;
                                case CampoBe.VALOR:
                                    text.setValue(atributo.getValor());
                                    break;
                                }
                            }
                        }
                        h.add(text);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.AREA)) {
                        TextArea textarea = new TextArea();
                        textarea.setLabel(cohijo.getDescripcion());
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                            switch (atributo.getAtributo()) {
                                case CampoBe.ANCHO:
                                    textarea.setWidth(atributo.getValor());
                                    textarea.setMaxWidth(atributo.getValor());
                                    textarea.setMinWidth(atributo.getValor());
                                    break;
                                case CampoBe.ALTO:
                                    textarea.setHeight(atributo.getValor());
                                    textarea.setMaxHeight(atributo.getValor());
                                    textarea.setMinHeight(atributo.getValor());
                                    break;
                                case CampoBe.VALOR:
                                    textarea.setValue(atributo.getValor());
                                    break;
                                }
                            }
                        }
                        h.add(textarea);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.COMBO)) {
                        ComboBox<String> comobo = new ComboBox<>();
                        comobo.setLabel(cohijo.getDescripcion());
                        if (cohijo.getValores() != null) {
                            ArrayList<String> lista = new ArrayList<>();
                            lista = Utilidades.getLista(cohijo.getValores());
                            comobo.setItems(lista);
                        }
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                            switch (atributo.getAtributo()) {
                                case CampoBe.ANCHO:
                                    comobo.setWidth(atributo.getValor());
                                    comobo.setMaxWidth(atributo.getValor());
                                    comobo.setMinWidth(atributo.getValor());
                                    break;
                                case CampoBe.VALOR:
                                    comobo.setValue(atributo.getValor());
                                    break;
                                }
                            }
                        }
                        h.add(comobo);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.CHECK)) {
                        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
                        checkboxGroup.setLabel(cohijo.getDescripcion());
                        if (cohijo.getValores() != null) {
                            ArrayList<String> lista = new ArrayList<>();
                            lista = Utilidades.getLista(cohijo.getValores());
                            checkboxGroup.setItems(lista);
                        }
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                                switch (atributo.getAtributo()) {
                                    case CampoBe.VALOR:
                                        Set<String> conjunto = new HashSet();
                                        conjunto.add(atributo.getValor());
                                        checkboxGroup.setValue(conjunto);
                                        break;
                                    case CampoBe.ALINEACION:
                                        if (atributo.getValor().equals(CampoBe.ALINEACION_VERTICAL)) {
                                            checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
                                        }
                                        break;
                                }
                            }
                        }
                        h.add(checkboxGroup);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.RADIO)) {
                        RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>();
                        radioButtonGroup.setLabel(cohijo.getDescripcion());
                        if (cohijo.getValores() != null) {
                            ArrayList<String> lista = new ArrayList<>();
                            lista = Utilidades.getLista(cohijo.getValores());
                            radioButtonGroup.setItems(lista);
                        }
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                                switch (atributo.getAtributo()) {
                                    case CampoBe.VALOR:
                                        radioButtonGroup.setValue(atributo.getValor());
                                        break;
                                    case CampoBe.ALINEACION:
                                        if (atributo.getValor().equals(CampoBe.ALINEACION_VERTICAL)) {
                                            radioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL.LUMO_VERTICAL);
                                        }
                                        break;
                                }
                            }
                        }
                        h.add(radioButtonGroup);
                    }
                }
                this.add(h);
            } else if (co.getCampo().getCampo().equals(CampoBe.COLUMNA)) {
                // columna
                VerticalLayout v = new VerticalLayout();
                for (AtributoBe atributo : co.getCampo().getAtributos()) {
                    if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                        switch (atributo.getAtributo()) {
                            case CampoBe.COLORFONDO:
                                // h.getStyle().set("background-color", "#FF5733");
                                v.getStyle().set("background-color", atributo.getValor());
                                break;
                        }
                    }
                }
                for (ComponenteBe cohijo : new FormularioDao().getHijos(formularioBe, co)) {
                    if (cohijo.getCampo().getCampo().equals(CampoBe.FECHA)) {
                        DatePicker date = new DatePicker();
                        date.setLabel(cohijo.getDescripcion());
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                                switch (atributo.getAtributo()) {
                                    case CampoBe.ANCHO:
                                        date.setWidth(atributo.getValor());
                                        date.setMaxWidth(atributo.getValor());
                                        date.setMinWidth(atributo.getValor());
                                        break;
                                }
                            }
                        }
                        v.add(date);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.TEXTO)) {
                        TextField text = new TextField();
                        text.setLabel(cohijo.getDescripcion());
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                                switch (atributo.getAtributo()) {
                                    case CampoBe.ANCHO:
                                        text.setWidth(atributo.getValor());
                                        text.setMaxWidth(atributo.getValor());
                                        text.setMinWidth(atributo.getValor());
                                        break;
                                    case CampoBe.ALTO:
                                        text.setHeight(atributo.getValor());
                                        text.setMaxHeight(atributo.getValor());
                                        text.setMinHeight(atributo.getValor());
                                        break;
                                    case CampoBe.VALOR:
                                        text.setValue(atributo.getValor());
                                        break;
                                }
                            }
                        }
                        v.add(text);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.AREA)) {
                        TextArea textarea = new TextArea();
                        textarea.setLabel(cohijo.getDescripcion());
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                                switch (atributo.getAtributo()) {
                                    case CampoBe.ANCHO:
                                        textarea.setWidth(atributo.getValor());
                                        textarea.setMaxWidth(atributo.getValor());
                                        textarea.setMinWidth(atributo.getValor());
                                        break;
                                    case CampoBe.ALTO:
                                        textarea.setHeight(atributo.getValor());
                                        textarea.setMaxHeight(atributo.getValor());
                                        textarea.setMinHeight(atributo.getValor());
                                        break;
                                    case CampoBe.VALOR:
                                        textarea.setValue(atributo.getValor());
                                        break;
                                }
                            }
                        }
                        v.add(textarea);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.COMBO)) {
                        ComboBox<String> comobo = new ComboBox<>();
                        comobo.setLabel(cohijo.getDescripcion());
                        if (cohijo.getValores() != null) {
                            ArrayList<String> lista = new ArrayList<>();
                            lista = Utilidades.getLista(cohijo.getValores());
                            comobo.setItems(lista);
                        }
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                                switch (atributo.getAtributo()) {
                                    case CampoBe.ANCHO:
                                        comobo.setWidth(atributo.getValor());
                                        comobo.setMaxWidth(atributo.getValor());
                                        comobo.setMinWidth(atributo.getValor());
                                        break;
                                    case CampoBe.VALOR:
                                        comobo.setValue(atributo.getValor());
                                        break;
                                }
                            }
                        }
                        v.add(comobo);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.CHECK)) {
                        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
                        checkboxGroup.setLabel(cohijo.getDescripcion());
                        if (cohijo.getValores() != null) {
                            ArrayList<String> lista = new ArrayList<>();
                            lista = Utilidades.getLista(cohijo.getValores());
                            checkboxGroup.setItems(lista);
                        }
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                                switch (atributo.getAtributo()) {
                                    case CampoBe.VALOR:
                                        Set<String> conjunto = new HashSet();
                                        conjunto.add(atributo.getValor());
                                        checkboxGroup.setValue(conjunto);
                                        break;
                                    case CampoBe.ALINEACION:
                                        if (atributo.getValor().equals(CampoBe.ALINEACION_VERTICAL)) {
                                            checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
                                        }
                                        break;
                                }
                            }
                        }
                        v.add(checkboxGroup);
                    } else if (cohijo.getCampo().getCampo().equals(CampoBe.RADIO)) {
                        RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>();
                        radioButtonGroup.setLabel(cohijo.getDescripcion());
                        if (cohijo.getValores() != null) {
                            ArrayList<String> lista = new ArrayList<>();
                            lista = Utilidades.getLista(cohijo.getValores());
                            radioButtonGroup.setItems(lista);
                        }
                        for (AtributoBe atributo : cohijo.getCampo().getAtributos()) {
                            if (atributo.getValor() != null && !atributo.getValor().isEmpty()) {
                                switch (atributo.getAtributo()) {
                                    case CampoBe.VALOR:
                                        radioButtonGroup.setValue(atributo.getValor());
                                        break;
                                    case CampoBe.ALINEACION:
                                        if (atributo.getValor().equals(CampoBe.ALINEACION_VERTICAL)) {
                                            radioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL.LUMO_VERTICAL);
                                        }
                                        break;
                                }
                            }
                        }
                        v.add(radioButtonGroup);
                    }
                }
                this.add(v);
            }

        }
        if (formularioBe != null && formularioBe.getComponentes().size() > 3) {
        HorizontalLayout h = new HorizontalLayout();
        h.add(Objetosui.getButton("Grabar "));
        h.add(Objetosui.getButton("Consolidar "));
            h.add(Objetosui.getButton("Cancelar "));
            this.add(h);
        }
    }

}
