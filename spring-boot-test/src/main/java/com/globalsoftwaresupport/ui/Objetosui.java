package com.globalsoftwaresupport.ui;

import java.util.ArrayList;
import java.util.Arrays;

import com.globalsoftwaresupport.bean.AtributoBe;
import com.globalsoftwaresupport.bean.CampoBe;
import com.globalsoftwaresupport.bean.ComponenteBe;
import com.globalsoftwaresupport.bean.DependenciaBe;
import com.globalsoftwaresupport.bean.FormularioBe;
import com.globalsoftwaresupport.dao.FormularioDao;
import com.globalsoftwaresupport.spring.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.component.treegrid.TreeGrid;

/**
 *
 * @author 06551256M
 */
public class Objetosui {

    /**
     *
     * @return
     */
    public static Grid<CampoBe> getGridCampos() {
        Grid<CampoBe> grid = new Grid<>();
        grid.addColumn(CampoBe::getCampo).setHeader("Campo");
        return grid;
    }

    /**
     *
     * @return
     */
    public static Grid<ComponenteBe> getGridComponentes() {
        Grid<ComponenteBe> grid = new Grid<>();
        grid.addColumn(ComponenteBe::getId).setHeader("Id");
        grid.addColumn(ComponenteBe::getDescripcion).setHeader("Campo").setWidth("200px");
        grid.addColumn(ComponenteBe::getOrden).setHeader("Orden");
        grid.addColumn(ComponenteBe::getCode).setHeader("Code");
        grid.addColumn(ComponenteBe::getCodesystem).setHeader("CodeSystem");
        grid.addColumn(ComponenteBe::getPerteneceString).setHeader("Pertenece");

        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        return grid;
    }

    public static Grid<DependenciaBe> getGridDependencias() {
        Grid<DependenciaBe> grid = new Grid<>();
        grid.addColumn(DependenciaBe::getTipo).setHeader("Tipo");
        grid.addColumn(DependenciaBe::getCampoDependienteString).setHeader("Campo");
        grid.addColumn(DependenciaBe::getCondicion).setHeader("Condición");
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        grid.setWidthFull();
        return grid;
    }

    public static Grid<AtributoBe> getGridatributos() {
        Grid<AtributoBe> grid = new Grid<>();
        grid.addColumn(AtributoBe::getAtributo).setHeader("Tipo de atributo");
        grid.addColumn(AtributoBe::getValor).setHeader("Valor");
        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        grid.setWidthFull();
        return grid;
    }

    /**
     *
     * @return
     */
    public static TreeGrid<ComponenteBe> getTreeGridComponentes() {
        TreeGrid<ComponenteBe> grid = new TreeGrid<>();
        grid.addHierarchyColumn(ComponenteBe::getId).setHeader("Id");

        grid.addColumn(ComponenteBe::getCampoString).setHeader("Tipo");
        grid.addColumn(ComponenteBe::getDescripcion).setHeader("Nombre").setWidth("200px");
        // grid.addColumn(ComponenteBe::getOrden).setHeader("Orden");
        // grid.addColumn(ComponenteBe::getCode).setHeader("Code");
        //  grid.addColumn(ComponenteBe::getCodesystem).setHeader("CodeSystem");
        grid.addColumn(ComponenteBe::getPerteneceString).setHeader("Pertenece");

        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        return grid;
    }
    /**
     *
     * @return
     */
    public static ComboBox<String> getComboCampo() {
        ComboBox<String> combo = new ComboBox<>("Tipo de campo");
        combo.setItems(CampoBe.getListaNombre());
        combo.setClearButtonVisible(true);
        combo.addThemeVariants(ComboBoxVariant.LUMO_SMALL);
        return combo;
    }

    /**
     *
     * @return
     */
    public static ComboBox<String> getComboCodeSystem() {
        ComboBox<String> combo = new ComboBox<>();
        combo.setLabel("Code system ");
        combo.setItems(new ArrayList<>(Arrays.asList("Loinc", "Seram", "CI)", "CIE10", "HL7", "ISBT")));
        combo.setClearButtonVisible(true);
        combo.addThemeVariants(ComboBoxVariant.LUMO_SMALL);
        return combo;
    }

    public static ComboBox<String> getComboTipoDependencias() {
        ComboBox<String> combo = new ComboBox<>();
        combo.setLabel("Dependencias  ");
        combo.setItems(new ArrayList<>(Arrays.asList("DeDatos", "Mostrar")));
        combo.setClearButtonVisible(true);
        combo.addThemeVariants(ComboBoxVariant.LUMO_SMALL);
        return combo;
    }
    /**
     *
     * @param formularioBe
     * @return
     */
    public static ComboBox<ComponenteBe> getComboPadres(FormularioBe formularioBe) {
        ComboBox<ComponenteBe> combo = new ComboBox<>();
        combo.setLabel("Elige contenedor");
        combo.setItems(new FormularioDao().getPadres(formularioBe));
        combo.setWidth("150px");
        combo.addThemeVariants(ComboBoxVariant.LUMO_SMALL);
        combo.setItemLabelGenerator(ComponenteBe::getDescripcion);
        combo.setClearButtonVisible(true);
        return combo;
    }

    /**
     *
     * @return
     */
    public static ComboBox<FormularioBe> getComboFormulario() {
        ComboBox<FormularioBe> combo = new ComboBox<>();
        combo.setLabel("Elige Formulario");
        combo.setItems(MainView.FORMULARIOS.values());
        combo.setWidth("150px");
        combo.addThemeVariants(ComboBoxVariant.LUMO_SMALL);
        combo.setItemLabelGenerator(FormularioBe::getNombreCombo);
        combo.setClearButtonVisible(true);
        if (MainView.FORMULARIOS.size() > 0) {
            combo.setValue(MainView.FORMULARIOS.get(0));
        }
        combo.setMinWidth("200px");
        return combo;
    }

    /**
     *
     * @param accion
     * @param icon
     * @return
     */
    public static Button getButtonGrid(Runnable accion, VaadinIcon icon) {
        Button button = new Button();
        button.addThemeVariants(ButtonVariant.LUMO_ICON,
                // ButtonVariant.LUMO_ERROR,
                ButtonVariant.LUMO_TERTIARY);
        button.addClickListener(e -> accion.run());
        button.setIcon(new Icon(icon));
        return button;
    }

    /**
     *
     * @param label
     * @return
     */
    public static TextField getTextField(String label) {
        TextField textField = new TextField(label);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setClearButtonVisible(true);
        return textField;

    }

    public static IntegerField getIntegerField(String label) {
        IntegerField integerField = new IntegerField(label);
        integerField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        integerField.setClearButtonVisible(true);
        return integerField;

    }
    /**
     *
     * @param label
     * @return
     */
    public static TextArea getTextArea(String label) {
        TextArea textArea = new TextArea(label);
        textArea.addThemeVariants(TextAreaVariant.LUMO_SMALL.LUMO_SMALL);
        textArea.setClearButtonVisible(true);
        return textArea;

    }

    /**
     *
     * @param label
     * @return
     */
    public static Button getButton(String label) {
        Button button = new Button(label);
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return button;
    }

    public static RadioButtonGroup<String> getTieneImpresoRadio() {
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setLabel("Tiene impreso asociado");
        radioGroup.setItems(FormularioBe.CON_IMPRESO, FormularioBe.SIN_IMPRESO);
        return radioGroup;
    }
}
