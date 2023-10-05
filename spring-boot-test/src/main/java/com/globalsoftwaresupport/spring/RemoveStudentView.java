package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/remove")
@PageTitle("Remove")
public class RemoveStudentView extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	public RemoveStudentView() {
		add(new Text("Removing students..."));
	}

}
