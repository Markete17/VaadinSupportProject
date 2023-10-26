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
        
        
        // Crear las pestañas
        Tabs tabs = new Tabs();
        Tab todosTab = new Tab(new Text("Todos"));
        Tab urgenciasTab = new Tab(new Text("Urgencias"));
        Tab consultasTab = new Tab(new Text("Consultas"));
        Tab hospTab = new Tab(new Text("Hosp"));
        Tab quirofanoTab = new Tab(new Text("Quirófano"));

        // Agregar los indicadores (badges) a las pestañas
        Span todosBadge = createBadge(7);
        Span urgenciasBadge = createBadge(2);
        Span consultasBadge = createBadge(1);
        Span hospBadge = createBadge(1);
        Span quirofanoBadge = createBadge(3);

        todosTab.add(todosBadge);
        urgenciasTab.add(urgenciasBadge);
        consultasTab.add(consultasBadge);
        hospTab.add(hospBadge);
        quirofanoTab.add(quirofanoBadge);
        
		// Accordion Panel
        AccordionPanel[] pacientes = {
        	    new AccordionPanel("HOSPITAL SEGOVIA", createDropDownList()),
        	    new AccordionPanel("HOSPITAL MAITE ROZAS", createDropDownList()),
        	    new AccordionPanel("HOSPITAL SALAMANCA", createDropDownList()),
        	    new AccordionPanel("HOSPITAL SEVERO OCHA", createDropDownList()),
        	    new AccordionPanel("HOSPITAL 12 OCTUBRE", createDropDownList()),
        	    new AccordionPanel("HOSPITAL JOSEP TRUETA", createDropDownList()),
        	    new AccordionPanel("HOSPITAL SANTA CATARINA", createDropDownList())
        	};

        	DetailsVariant[] variantsToApply = {DetailsVariant.SMALL};
        	
        	for (AccordionPanel paciente : pacientes) {
        	    paciente.addThemeVariants(variantsToApply);;
        	    paciente.addOpenedChangeListener(event -> {
        	        if (event.isOpened()) {
        	            // Cambiar el fondo del título cuando se abre
        	        	paciente.addThemeVariants(DetailsVariant.FILLED);
        	        } else {
        	            // Restaurar el fondo original del título cuando se cierra
        	            paciente.removeThemeVariants(DetailsVariant.FILLED);
        	        }
        	    });
        	}

        
        List<AccordionPanel> urgenciasList = new ArrayList<>();
        urgenciasList.add(pacientes[0]);
        urgenciasList.add(pacientes[1]);
        
        List<AccordionPanel> consultasList = new ArrayList<>();
        consultasList.add(pacientes[2]);
        
        List<AccordionPanel> quirofanoList = new ArrayList<>();
        quirofanoList.add(pacientes[3]);
        quirofanoList.add(pacientes[4]);
        quirofanoList.add(pacientes[5]);
        
        List<AccordionPanel> hospList = new ArrayList<>();
        hospList.add(pacientes[6]);
        
        List<AccordionPanel> todosList = new ArrayList<>();
        todosList.addAll(urgenciasList);
        todosList.addAll(consultasList);
        todosList.addAll(quirofanoList);
        todosList.addAll(hospList);
        
        this.map.put("urgencias", urgenciasList);
        this.map.put("consultas", consultasList);
        this.map.put("quirofano", quirofanoList);
        this.map.put("hosp", hospList);
        this.map.put("todos", todosList);
        

        
        Div content = new Div();
        

        // Agregar un SelectionListener para las pestañas
        tabs.addSelectedChangeListener(event -> {
            Tab selectedTab = event.getSelectedTab();
            content.removeAll();
            if (selectedTab == todosTab) {
            	accordion = new Accordion();
                for(AccordionPanel panel: this.map.get("todos")) {
                	accordion.add(panel);
                }
            	content.add(accordion);
                
            } else if (selectedTab == urgenciasTab) {
            	accordion = new Accordion();
                for(AccordionPanel panel: this.map.get("urgencias")) {
                	accordion.add(panel);
                }
                content.add(accordion);
            } else if (selectedTab == consultasTab) {
            	accordion = new Accordion();
                for(AccordionPanel panel: this.map.get("consultas")) {
                	accordion.add(panel);
                }
                content.add(accordion);
            } else if (selectedTab == hospTab) {
            	accordion = new Accordion();
                for(AccordionPanel panel: this.map.get("hosp")) {
                	accordion.add(panel);
                }
                content.add(accordion);
            } else if (selectedTab == quirofanoTab) {
            	accordion = new Accordion();
                for(AccordionPanel panel: this.map.get("quirofano")) {
                	accordion.add(panel);
                }
                content.add(accordion);
            }
        });
        
     // Agregar las pestañas al layout
        tabs.add(todosTab, urgenciasTab, consultasTab, hospTab, quirofanoTab);

        // Crear un contenedor de flexbox para tabs y contenido
        Div tabsAndContentContainer = new Div();
        tabsAndContentContainer.getStyle().set("display", "flex"); // Contenedor flexbox
        tabsAndContentContainer.getStyle().set("flex-direction", "column"); // Dirección vertical

        // Aplicar estilos para controlar la anchura
        tabs.getStyle().set("flex", "1"); // Hacer que las tabs ocupen el espacio restante
        content.getStyle().set("flex", "2"); // Hacer que el contenido ocupe más espacio que las tabs

        // Agregar los componentes al contenedor flexbox
        tabsAndContentContainer.add(tabs, content);

        // Agregar el contenedor de flexbox al layout principal
        add(titleDiv, ordText, episodiosOrd, filterText, tabsAndContentContainer);
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
