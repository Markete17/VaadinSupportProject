package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("episodios")
@PageTitle("Episodios - Paciente")
@CssImport(value = "./styles/styles.css", themeFor = "vaadin-accordion")
public class Episodios extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private EpisodiosOrd episodiosOrd = new EpisodiosOrd();

	public Episodios() {
		H3 title = new H3("Resumen de episodios");
		Div titleDiv = new Div(title);
		titleDiv.getStyle().set("margin", "0");

		Text ordText = new Text("Ordenar por:");

		Span ordSpan = new Span(ordText);
		ordSpan.getElement().getStyle().set("margin", "0");

		Text filterText = new Text("Filtrar por:");
		Span filterSpan = new Span(filterText);
		filterSpan.getElement().getStyle().set("margin", "0");

		EpisodiosTabbedAccordion episodiosTabbedAccordion = new EpisodiosTabbedAccordion();

		// Agregar el contenedor de flexbox al layout principal
		add(titleDiv, ordSpan, episodiosOrd, filterSpan, episodiosTabbedAccordion);
	}

}
