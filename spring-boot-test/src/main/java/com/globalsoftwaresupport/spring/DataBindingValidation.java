package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/dataValidation")
@PageTitle("Validation")
public class DataBindingValidation extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public DataBindingValidation() {
		ContactForm form = new ContactForm();
		add(form);
	}
}
