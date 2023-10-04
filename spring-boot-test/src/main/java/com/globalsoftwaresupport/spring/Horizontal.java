package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/horizontal")
@PageTitle("Horizontal")
public class Horizontal extends HorizontalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Horizontal() {
		
		add(new Button("button1"));
		add(new Button("button2"));
		add(new Button("button3"));
		add(new Button("button4"));
		add(new Button("button5"));
		
		setHeight("100%");
		
		// Horizontal Alligment
		setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
		
		setAlignItems(FlexComponent.Alignment.CENTER);
		
	}

}
