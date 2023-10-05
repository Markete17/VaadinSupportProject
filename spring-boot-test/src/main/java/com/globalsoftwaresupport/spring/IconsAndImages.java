package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/images")
@PageTitle("Images And Icons")
public class IconsAndImages extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	//https://vaadin.com/docs/latest/components/icons/default-icons
	
	public IconsAndImages() {
		
		// ICONOS
		
		//vaadin:arrow-circle-right
		Icon icon = new Icon("vaadin", "arrow-circle-right");
		add(icon);
		
		TextField field = new TextField("Date of User");
		
		// Prefix component para poner un icono en el input
		
		field.setPrefixComponent(new Icon("lumo","calendar"));
		
		// Sufix component para poner un icono en el input despues
		
		field.setSuffixComponent(new Icon("lumo","checkmark"));
		
		add(field);
		
		// IMAGENES
		// Las imagenes se van a almacenar en resouces/META-INF/resources/images hay que crearla
		Image image = new Image("images/aaa.png", "Alt logo");
		image.setWidth("80px");
		image.setHeight("89px");
		
		add(image);
	}
}
