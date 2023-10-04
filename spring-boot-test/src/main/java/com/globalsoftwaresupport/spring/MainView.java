package com.globalsoftwaresupport.spring;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.*;

@Route("/ui")
@PageTitle("Página Bonita de Vaadin")
public class MainView extends VerticalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainView() {
        // Encabezado
        H1 header = new H1("Página de Vaadin");
        header.addClassName("header-title");

        HorizontalLayout navigationBar = new HorizontalLayout();
        navigationBar.setAlignItems(FlexComponent.Alignment.CENTER);
        navigationBar.addClassName("navigation-bar");

        TextField searchField = new TextField();
        searchField.setPlaceholder("Buscar...");
        searchField.addClassName("search-field");

        Button homeButton = new Button("Inicio");
        homeButton.addClassName("nav-button");
        Button aboutButton = new Button("Acerca de");
        aboutButton.addClassName("nav-button");
        Button contactButton = new Button("Contacto");
        contactButton.addClassName("nav-button");

        navigationBar.add(searchField, homeButton, aboutButton, contactButton);

        // Contenido principal
        Div content = new Div();
        content.addClassName("content");

        content.add(new H1("¡Bienvenido a nuestra Página de Vaadin!"));
        content.add(new Div());
        Button clickButton = new Button("Haga clic aquí");
        clickButton.addClickListener(e -> {
            clickButton.setText("¡Gracias por hacer clic!");
        });
        content.add(clickButton);

        // Agregar elementos al diseño principal
        add(header, navigationBar, content);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setClassName("main-layout");
        
        
        // FORMULARIO
        
        Paragraph paragraph = new Paragraph("Elementos del formulario");
        add(paragraph);
        
        // Text Input
        TextField nameField = new TextField("Name: ");
        
        nameField.setMinLength(3);
        nameField.setMaxLength(10);
        
        nameField.addValueChangeListener(event -> {
        	System.out.println("value has changed.");
        });
        
        add(nameField);
        
        //Binding
        // Vincular una clase Java a un formulario
        
        TextField textField = new TextField("Name Binder: ");
        Button saveButton = new Button("Save");
        
        Person person = new Person("Manuel",23);
        
        Binder<Person> personBinder = new Binder<>();
        
        personBinder.bind(textField, Person::getName, Person::setName);
        
        saveButton.addClickListener(event -> {
        	try {
        		personBinder.writeBean(person);
        		System.out.println(person);
			} catch (Exception e) {
				throw new RuntimeException();
			}
        	
        });
        
        add(textField);
        add(saveButton);
        
        
        // TEXT AREA
        TextArea textArea = new TextArea();
        textArea.setLabel("Description");
        textArea.setHeight("200px");
        textArea.setWidth("400px");
        textArea.setMaxLength(10);
        
        // Este valor hace que se sincroniza justo cuando se modifica
        textArea.setValueChangeMode(ValueChangeMode.EAGER);
        textArea.addValueChangeListener(event -> {
        	System.out.println("Ha cambiado");
        });
        
        add(textArea);
        
        
        // CHECKBOX
        
        Checkbox check = new Checkbox();
        check.setLabel("I agree the terms");
        
        check.setEnabled(true); //false
        
        add(check);
        
        check.addValueChangeListener(event -> {
        	System.out.println(check.getValue());
        });
        
        //CHECKBOX GROUP
        
        CheckboxGroup<String> group = new CheckboxGroup<>();
        group.setLabel("Days");
        group.setItems("Monday", "Tuesday", "Wednesday");
        
        //Para ponerlos vertical
        group.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        
        group.addValueChangeListener(event -> {
        	System.out.println(group.getValue());
        });
        
        add(group);
        
        // COMBO BOX
        
        List<Person> list = new ArrayList<>();
        list.add(new Person("Kevin",25));
        list.add(new Person("Adam",20));
        list.add(new Person("Ana",29));
        
        ComboBox<Person> box = new ComboBox<>("Employee");
        box.setItems(list);
        box.setPlaceholder("Select the employee");
        
        // Por defecto pilla el toString de la clase
        //Especificar que quieres que se muestre en el checkbox
        box.setItemLabelGenerator(p -> p.getName() + " - " + p.getAge());
        
        box.addValueChangeListener(event -> {
        	System.out.println(box.getValue());
        });
        
        add(box);
    }
}
