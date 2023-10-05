package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/update")
@PageTitle("Update")
//BeforeEnterObserver implementa un callback antes de entrar a la pagina
public class UpdateStudentView extends VerticalLayout implements BeforeEnterObserver {

	private static final long serialVersionUID = 1L;
	
	public UpdateStudentView() {
		
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		add(new Text("Updating students..."));
		
	}

}
