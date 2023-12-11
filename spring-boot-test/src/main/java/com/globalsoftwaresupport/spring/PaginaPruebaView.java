package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("pagina")
@PageTitle("Microfrontends")
public class PaginaPruebaView extends Div {

	private static final long serialVersionUID = 1L;

    public PaginaPruebaView() {
    	HorizontalLayout horizontalLayout = new HorizontalLayout();
    	

        // Crear el primer IFrame
        IFrame iframe1 = createIFrame("http://localhost:8380/episodios");

        // Crear el segundo IFrame
        IFrame iframe2 = createIFrame("http://localhost:8380/episodios"); // Cambia la URL según sea necesario

        
        IFrame iframe3 = createIFrame("http://localhost:8380/episodios");
        
        // Organizar los componentes en un diseño horizontal
        horizontalLayout.add(iframe1, iframe2, iframe3);
        
        add(horizontalLayout);
    }

    private IFrame createIFrame(String url) {
        IFrame iframe = new IFrame(url);
        iframe.getElement().getStyle().set("border", "0");
        iframe.getElement().getStyle().set("margin", "0");
        iframe.getElement().getStyle().set("padding", "0");
        iframe.setWidth("530px");
        iframe.setHeight("300px");
        iframe.getElement().getStyle().set("overflow", "hidden");

        return iframe;
    }

}
