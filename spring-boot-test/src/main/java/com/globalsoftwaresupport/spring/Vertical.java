package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/vertical")
@PageTitle("Vertical")
public class Vertical extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Vertical Layout coloca los componentes de arriba a bajo
	public Vertical() {
		add(new Button("boton1"));
		add(new Button("boton2"));
		add(new Button("boton3"));
		add(new Button("boton4"));
		add(new Button("boton5"));
		
		
		setHeight("100%");
		
		// Vertical Aligment (por defecto)
		
		// Se puede poner el contenido en diferentes disposiciones
		//setJustifyContentMode(FlexComponent.JustifyContentMode.START);
		//setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		//setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		//setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
		
		
		// Horizontal Alignment
		setAlignItems(FlexComponent.Alignment.CENTER);
		//setJustifyContentMode(FlexComponent.JustifyContentMode.START);
		//setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		//setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		//setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
		
		setAlignItems(FlexComponent.Alignment.STRETCH);
		setAlignItems(FlexComponent.Alignment.END);
	}
}
