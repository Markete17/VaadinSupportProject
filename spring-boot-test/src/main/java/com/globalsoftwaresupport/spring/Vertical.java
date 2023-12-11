package com.globalsoftwaresupport.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@Route("/vertical")
@PageTitle("Vertical")
@SpringComponent
@UIScope
public class Vertical extends VerticalLayout implements BeforeEnterObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//@Autowired
	//private KafkaToken kafkaToken;
	
	private String token;

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

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // TODO Auto-generated method stub
        
    }

//	@Override
//	public void beforeEnter(BeforeEnterEvent event) {
//		if(kafkaToken!=null) {
//			this.token = kafkaToken.getToken();
//			
//		} else {
//			this.token = "TOKEN NULL";
//		}
//		add(new Span(token));
//	}
}
