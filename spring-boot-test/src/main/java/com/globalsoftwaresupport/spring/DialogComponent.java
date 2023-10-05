package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/dialog")
@PageTitle("dialog")
public class DialogComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private TextField nameField;
	private TextField ageField;
	
	
	public DialogComponent() {
	
	Dialog dialog = new Dialog();
	dialog.setHeaderTitle("Add New Student");
	
	// Text fields for the user input
	VerticalLayout dialogLayout = createDialogLayout();
	dialog.add(dialogLayout);
	
	// Botones
	Button cancel = new Button("Cancel", event -> {
		dialog.close();
	});
	Button save = new Button("Save", event -> {
		System.out.println(nameField.getValue() + " - " + ageField.getValue() );
		dialog.close();
	});
	
	save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	
	dialog.getFooter().add(cancel);
	dialog.getFooter().add(save);
	
	// button para ver el dialog
	Button button = new Button("Show Dialog",
			event -> {
				dialog.open();
			});
	add(button);
	}

	private VerticalLayout createDialogLayout() {
		this.nameField = new TextField("Name");
		this.ageField = new TextField("Age");
		
		VerticalLayout layout = new VerticalLayout(nameField, ageField);
		layout.getStyle().set("width", "250px").set("max-width", "100%");
		return layout;
	}
}
