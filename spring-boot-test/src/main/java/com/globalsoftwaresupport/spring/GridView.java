package com.globalsoftwaresupport.spring;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/grid")
@PageTitle("Grid")
public class GridView extends VerticalLayout {

	
	private static final long serialVersionUID = 1L;

	public GridView() {
		/*
		 * En este caso, cuando se pasa false como segundo parámetro al constructor de Grid, significa que se desactiva la creación automática de columnas.
		 *  Esto implica que tendrás que configurar 
		 *  manualmente las columnas que deseas mostrar 
		 *  en la cuadrícula utilizando métodos como 
		 *  addColumn() o configurando las propiedades de columna según sea necesario.
		 * */
		Grid<Person> grid = new Grid<>(Person.class,false);
		
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Adam", 21, "adam@gmail.com"));
		persons.add(new Person("Kevin", 32, "kevin@gmail.com"));
		persons.add(new Person("Paula", 53, "paulagmail.com"));
		persons.add(new Person("Manuel", 21, "sffd@gmail.com"));
		persons.add(new Person("Samana", 32, "a32@gmail.com"));
		persons.add(new Person("Marcos", 43, "adsd@gmail.com"));
		persons.add(new Person("Guille", 12, "agdf@gmail.com"));
		persons.add(new Person("Manuel", 21, "sffd@gmail.com"));
		persons.add(new Person("Samana", 32, "a32@gmail.com"));
		persons.add(new Person("Marcos", 43, "adsd@gmail.com"));
		persons.add(new Person("Guille", 12, "agdf@gmail.com"));
		
		
		// Default height: 400px
		
		grid.addColumn(Person::getName)
	    .setHeader(new Html("<b>Name</b>")).setSortable(true)
	    .setTextAlign(ColumnTextAlign.CENTER);

	grid.addColumn(Person::getEmail)
	    .setHeader(new Html("<b>Email</b>")).setSortable(true)
	    .setTextAlign(ColumnTextAlign.CENTER);

	grid.addColumn(Person::getAge)
	    .setHeader(new Html("<b>Age</b>")).setSortable(true)
	    .setTextAlign(ColumnTextAlign.CENTER);
		
		grid.setItems(persons);
		
		// Customize the theme con la clase GridVariant
		grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
		
		// Para que se puedan seleccionar varios a la vez
		grid.setSelectionMode(SelectionMode.MULTI);
		
		Button button = new Button("Remove");
		
		button.addClickListener(event -> {
			persons.removeAll(grid.getSelectedItems());
			grid.getDataProvider().refreshAll();
		});
		
		button.getElement().getStyle().set("cursor", "pointer");
		
		add(button);
		
		add(grid);
		
		
		
	}
}
