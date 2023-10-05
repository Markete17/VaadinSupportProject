package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/formlayout")
@PageTitle("formLayout")
public class FormLayoutComponent extends FormLayout {

	private static final long serialVersionUID = 4169927034842976007L;

	public FormLayoutComponent() {
		
		TextField firstName = new TextField("FIrst Name");
		TextField lastName = new TextField("Last Name");
		TextField username = new TextField("Username");
		PasswordField password = new PasswordField("Password");
		PasswordField confirm = new PasswordField("Confirm Password");
		
		// Si está dentro de 0 y 1500 px entonces hay una sola columna.
		setResponsiveSteps(
				new ResponsiveStep("0px", 1),
				new ResponsiveStep("1500px", 2));
		
		// Username va a ocupar 2 columnas
		setColspan(username, 2);
		
		add(firstName, lastName, username, password, confirm);
		
	}
}
