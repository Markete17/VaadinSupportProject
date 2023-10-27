package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EpisodiosTabbedAccordion extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    private Map<String, List<AccordionPanel>> map = new HashMap<>();
    private Accordion accordion = new Accordion();

    private final String LIGHT_BLUE_COLOR = "#0073e6"; // Azul más claro
    private final String DARK_BLUE_COLOR = "#000000"; // Color de texto oscuro
    private final String LIGHT_TEXT_COLOR = "#ffffff"; // Color de texto en fondo oscuro

    public EpisodiosTabbedAccordion() {
        createLayout();
    }

    private void createLayout() {
        // Crear las pestañas
        Tabs tabs = new Tabs();
        Tab todosTab = createTab("Todos");
        Tab urgenciasTab = createTab("Urgencias");
        Tab consultasTab = createTab("Consultas");
        Tab hospTab = createTab("Hosp");
        Tab quirofanoTab = createTab("Quirófano");

        // Agregar los indicadores (badges) a las pestañas
        todosTab.add(createBadge(7));
        urgenciasTab.add(createBadge(2));
        consultasTab.add(createBadge(1));
        hospTab.add(createBadge(1));
        quirofanoTab.add(createBadge(3));

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

        DetailsVariant[] variantsToApply = { DetailsVariant.SMALL };

        for (AccordionPanel paciente : pacientes) {
            paciente.addThemeVariants(variantsToApply);
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

        // Mapa que relaciona las pestañas con los datos correspondientes
        Map<Tab, List<AccordionPanel>> tabToAccordionDataMap = new HashMap<>();
        tabToAccordionDataMap.put(todosTab, map.get("todos"));
        tabToAccordionDataMap.put(urgenciasTab, map.get("urgencias"));
        tabToAccordionDataMap.put(consultasTab, map.get("consultas"));
        tabToAccordionDataMap.put(hospTab, map.get("hosp"));
        tabToAccordionDataMap.put(quirofanoTab, map.get("quirofano"));

        // Agregar un SelectionListener para las pestañas
        tabs.addSelectedChangeListener(event -> {
            Tab selectedTab = event.getSelectedTab();
            content.removeAll();
            Accordion accordion = createAccordionForTab(selectedTab, tabToAccordionDataMap);
            content.add(accordion);
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

        add(tabsAndContentContainer);
    }

    private Tab createTab(String label) {
        Tab tab = new Tab(new Text(label));
        tab.getElement().getStyle().set("cursor", "pointer"); // Agregar el cursor de puntero
        return tab;
    }

    private Span createBadge(int count) {
        Span badge = new Span(String.valueOf(count));
        badge.getStyle().set("background-color", LIGHT_BLUE_COLOR); // Usar un tono más claro de azul
        badge.getStyle().set("color", "white");
        badge.getStyle().set("border-radius", "50%");
        badge.getStyle().set("padding", "4px 8px");
        badge.getStyle().set("margin-left", "5px");
        badge.getStyle().set("font-weight", "bold");
        badge.getStyle().set("font-size", "12px");
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

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.getElement().getStyle().set("margin-top", "10px");

        Button botonOjo = new Button(new Icon(VaadinIcon.EYE));
        botonOjo.getElement().getStyle().set("margin-left", "15px");

        Button botonPapel = new Button(new Icon(VaadinIcon.FILE_TEXT_O));
        botonPapel.getElement().getStyle().set("margin-left", "-5%");

        buttonsLayout.add(botonOjo, botonPapel);
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        dropDownList.add(descripcion, fechaIngreso, fechaAlta);
        dropDownList.add(buttonsLayout);
        dropDownList.setSpacing(false);
        dropDownList.setPadding(false);

        return dropDownList;
    }

    private Accordion createAccordionForTab(Tab selectedTab, Map<Tab, List<AccordionPanel>> tabToAccordionDataMap) {
        Accordion accordion = new Accordion();
        List<AccordionPanel> panels = tabToAccordionDataMap.get(selectedTab);

        if (panels != null) {
            for (AccordionPanel panel : panels) {
                accordion.add(panel);
            }
        }

        return accordion;
    }
}
