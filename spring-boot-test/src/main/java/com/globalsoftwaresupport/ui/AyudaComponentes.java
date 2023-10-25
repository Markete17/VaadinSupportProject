package com.globalsoftwaresupport.ui;

import com.globalsoftwaresupport.bean.ComponenteBe;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;

/**
 *
 * @author 06551256M
 */
public class AyudaComponentes extends Dialog {

    public ComponenteBe componenteBe = null;

    public AyudaComponentes() {
        this.setWidth("500px");
        Grid<ComponenteBe> grid = Objetosui.getGridComponentes();
        grid.setWidth("500px");
        grid.setItems(ComponenteBe.getPredefinidos());
        grid.addItemClickListener(event -> {
            componenteBe = event.getItem();
            this.close();
        });
        this.add(grid);
    }

    public ComponenteBe getComponenteBe() {
        return componenteBe;
    }
}
