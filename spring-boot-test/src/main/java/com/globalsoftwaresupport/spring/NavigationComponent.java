package com.globalsoftwaresupport.spring;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("/navigation")
@PageTitle("Navigations")
public class NavigationComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public NavigationComponent() {
		add(new RouterLink("Show Students", ShowStudentView.class));
		add(new RouterLink("Update Students", ShowStudentView.class));

		//Manejar la navegación
		Button button = new Button("Remove Student View", event -> {
			getUI().ifPresent(ui -> ui.navigate(RemoveStudentView.class));
		});
		
		add(button);
		
	}
}
