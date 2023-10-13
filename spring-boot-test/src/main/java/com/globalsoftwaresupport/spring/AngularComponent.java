package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/angular")
@PageTitle("Angular")
public class AngularComponent extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    public AngularComponent() {

        setSizeFull();

        // Configura el iframe para ocupar toda la pantalla sin barras de desplazamiento
        String iframeHtml = "<iframe src='./angular-app/index.html' style='width: 100%; height: 100%; border: none; overflow: hidden;'></iframe>";
        add(new Html(iframeHtml));

        // Configura el componente HTML para que también ocupe toda la pantalla
        setFlexGrow(1, new Html(iframeHtml));
    }
}


