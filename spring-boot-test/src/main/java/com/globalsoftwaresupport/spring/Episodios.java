package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoIcon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Route("episodios")
@PageTitle("Episodios - Paciente")
@CssImport(value = "./styles/styles.css", themeFor = "vaadin-accordion")
public class Episodios extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    private EpisodiosOrd episodiosOrd = new EpisodiosOrd();
    private Map<String, List<AccordionPanel>> map = new HashMap();
    private Accordion accordion = new Accordion();

    public Episodios() {
        H3 title = new H3("Resumen de episodios");
        Div titleDiv = new Div(title);
        titleDiv.getStyle().set("margin-bottom", "0");

        Text ordText = new Text("Ordenar por:");
        Text filterText = new Text("Filtrar por:");
        
        EpisodiosTabbedAccordion episodiosTabbedAccordion = new EpisodiosTabbedAccordion();

        // Agregar el contenedor de flexbox al layout principal
        add(titleDiv, ordText, episodiosOrd, filterText, episodiosTabbedAccordion);
    }
    
    private Span createBadge(int count) {
        Span badge = new Span(String.valueOf(count));
        badge.getStyle().set("background-color", "blue");
        badge.getStyle().set("color", "white");
        badge.getStyle().set("border-radius", "50%");
        badge.getStyle().set("padding", "2px 6px");
        badge.getStyle().set("margin-left", "5px");
        return badge;
    }
    
    private VerticalLayout createDropDownList() {
        VerticalLayout dropDownList = new VerticalLayout();

        
        Span descripcion = new Span();
        descripcion.getElement().setProperty("innerHTML", "<b>Descripcion: </b> Fractura fémur");
        Span fechaIngreso = new Span();
        fechaIngreso.getElement().setProperty("innerHTML", "<b>Fecha Ingreso:</b> 22/02/2019");
        Span fechaAlta = new Span();
        fechaAlta.getElement().setProperty("innerHTML", "<b>Fecha Alta:</b> 22/02/2023");
        
        
        descripcion.getStyle().set("margin-left", "15px");
        fechaIngreso.getStyle().set("margin-left", "15px");
        fechaAlta.getStyle().set("margin-left", "15px");

        // Crear un HorizontalLayout para organizar los botones horizontalmente
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.getElement().getStyle().set("margin-top", "10px");

        // Crear un botón con el icono de un ojo con margen
        Button botonOjo = new Button(new Icon(VaadinIcon.EYE));
        botonOjo.getElement().getStyle().set("margin-left", "15px");

        // Crear un botón con el icono de un papel con margen
        Button botonPapel = new Button(new Icon(VaadinIcon.FILE_TEXT_O));
        botonPapel.getElement().getStyle().set("margin-left", "-5%");

        // Agregar los botones al HorizontalLayout
        buttonsLayout.add(botonOjo, botonPapel);

        // Configurar la alineación de los botones al centro
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        // Agregar los elementos de texto al VerticalLayout
        dropDownList.add(descripcion, fechaIngreso, fechaAlta);

        // Agregar el HorizontalLayout con los botones al VerticalLayout
        dropDownList.add(buttonsLayout);
        dropDownList.setSpacing(false);
        dropDownList.setPadding(false);

        return dropDownList;
    }

}
