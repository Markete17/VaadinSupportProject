package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;

public class ContactForm extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	//Con Property Id se tiene que indicar el nombre de la propiedad de la clase
	
	@PropertyId("name")
	private TextField nameField;
	
	@PropertyId("email")
	private TextField emaiField;
	
	@PropertyId("age")
	private TextField ageField;
	
	private Button saveButton;
	
	// Bean we want to bind
	private Person person;
	
	private Binder<Person> binder;
	
	public ContactForm() {
		initComponents();
		initBinder();
		addComponents();
	}
	
	public void setPerson(Person person) {
		this.person = person;
		
		// set the values of the java object to the fields
		binder.readBean(person);
	}

	private void addComponents() {
		add(nameField, emaiField, ageField, saveButton);
		
	}

	private void initBinder() {
		this.binder = new BeanValidationBinder<>(Person.class);
		binder.bindInstanceFields(this);
	}

	private void initComponents() {
		
		this.person = new Person();
		
		this.nameField = new TextField("Name");
		this.emaiField = new TextField("Email");
		this.ageField = new TextField("Age");
		this.saveButton = new Button("Save");
		
		this.saveButton.addClickListener(event -> {
			try {
				// Aqui los datos de la interfaz van al objeto
				binder.writeBean(person);
				System.out.println("Data saved: "+person);
			} catch (Exception e) {
				Notification.show("Validation error!");
			}
		});
	}
	
}
