package com.globalsoftwaresupport.spring;


import java.util.HashMap;

import com.globalsoftwaresupport.bean.ComponenteBe;
import com.globalsoftwaresupport.bean.FormularioBe;
import com.globalsoftwaresupport.util.Utilidades;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import lombok.extern.log4j.Log4j2;

@Route("formularios")
@Log4j2
public class MainView extends AppLayout {

    private Tabs menu = null;

    private H1 viewTitle;

    public static HashMap<Integer, FormularioBe> FORMULARIOS = new HashMap<>();
    public static HashMap<String, ComponenteBe> COMPONENTES = new HashMap<>();

    /**
     *
     */
    public MainView() {
        try {

            FormularioBe formularioBe = new FormularioBe(1, "Informe de urgencias ginecológico");
            FORMULARIOS.put(1, formularioBe);

            formularioBe = new FormularioBe(2, "Informe de urgencias pediatría");
            FORMULARIOS.put(2, formularioBe);

            formularioBe = new FormularioBe(3, "Informe de urgencias generales");
            FORMULARIOS.put(3, formularioBe);

            log.info("Iniciando MainView");

            setPrimarySection(Section.DRAWER);

            addToNavbar(true, createHeaderContent());

            menu = createMenu();
            addToDrawer(createDrawerContent(menu));
        } catch (Exception ex) {
            log.error(Utilidades.getStackTrace(ex));
        }
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();

        // Configure styling for the drawer
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.START);

        // Display the logo and the menu in the drawer
        layout.add(menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    /**
     *
     * @return
     */
    private Component[] createMenuItems() {
        return new Tab[]{
            createTab("Formularios", FormularioFrmView.class, VaadinIcon.HEART),
            createTab("Actualiza pacientes", UrgenciasView.class, VaadinIcon.ABACUS)
        };
    }

    private static Tab createTab(String text,
            Class<? extends Component> navigationTarget, VaadinIcon icon) {
        final Tab tab = new Tab();

        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();

        // Configure styling for the header
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        // Have the drawer toggle button on the left
        layout.add(new DrawerToggle());

        // Placeholder for the title of the current view.
        // The title will be set after navigation.
        viewTitle = new H1();
        layout.add(viewTitle);

        // A user icon
        //   layout.add(new Image("images/user.svg", "Avatar"));
        return layout;
    }

    /**
     *
     * @param viewIcon
     * @param viewName
     * @return
     */
    private Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName.replaceAll("View", "")));
        link.setTabIndex(-1);
        return new Tab(link);
    }

}
