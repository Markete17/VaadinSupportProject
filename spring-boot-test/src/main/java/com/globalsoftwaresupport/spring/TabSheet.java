package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.tabs.Tabs.Orientation;

@Route("/tabsheet")
@PageTitle("Tabsheet")
public class TabSheet extends VerticalLayout {

	private static final long serialVersionUID = -4539536563213301714L;
	
	public TabSheet() {
	
	Tab tab1 = new Tab("Orders");
	Tab tab2 = new Tab("Payments");
	Tab tab3 = new Tab("Services");
	
	// Para deshabilitar
	tab3.setEnabled(false);

	Tabs mainTab = new Tabs(tab1, tab2, tab3);
	
	// Para dejar seleccionada un tab por defecto
	mainTab.setSelectedTab(tab2);
	
	//Cambiar la orientacion (por defecto es horizontal)
	mainTab.setOrientation(Orientation.VERTICAL);
	
	// Icons vaadin
	//https://vaadin.com/docs/latest/components/icons
	
	Tab tab4 = new Tab(VaadinIcon.BELL.create(), new Span("Orders"));
	Tab tab5 = new Tab(VaadinIcon.COG.create(), new Span("Orders"));
	Tab tab6 = new Tab(VaadinIcon.USER.create(), new Span("Orders"));
	
	// Poner tema para que el icono este arriba
	tab4.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
	
	Tabs mainTab2 = new Tabs(tab4, tab5, tab6);
	
	//centrar Tabs
	mainTab2.addThemeVariants(TabsVariant.LUMO_CENTERED);
	mainTab2.setWidth("100%");
	
	Tab tab7 = new Tab(VaadinIcon.ACCESSIBILITY.create(), new Span("Orders"));
	Tab tab8 = new Tab(VaadinIcon.ACCORDION_MENU.create(), new Span("Orders"));
	Tab tab9 = new Tab(VaadinIcon.AMBULANCE.create(), new Span("Orders"));
	
	// Poner tema para que el icono este arriba
	tab7.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
	tab8.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
	tab9.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
	
	Tabs mainTab3 = new Tabs(tab7, tab8, tab9);
	
	//centrar Tabs
	mainTab3.addThemeVariants(TabsVariant.LUMO_EQUAL_WIDTH_TABS);
	mainTab3.setWidth("100%");
	
	add(mainTab);
	add(mainTab2);
	add(mainTab3);
	}
	
	
	
}
