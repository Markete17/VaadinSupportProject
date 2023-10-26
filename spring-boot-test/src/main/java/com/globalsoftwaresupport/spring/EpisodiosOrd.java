package com.globalsoftwaresupport.spring;

import java.util.Objects;

import org.springframework.lang.NonNull;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;

import lombok.Getter;

public class EpisodiosOrd extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	private static final String BACKGROUND_COLOR = "#FFF6C0";
	
    private final Button fechaButton = new Button("Fecha");
    private final Button servicioButton = new Button("Servicio");
    private final Button centroButton = new Button("Centro");
    // Valor seleccionado
    @Getter
    private Boolean value;

    public EpisodiosOrd() {
        super();
        createLayout();
        clearStyles();
    }

    public void clear() {
        clearStyles();
        value = null;
        //fireEvent(new ValueChangeEvent(this, FilterForm.empty(EpisodiosRestDtoDataProvider.FILTER_MENORES)));
    }

    private void createLayout() {
        this.setSpacing(false);

        fechaButton.addClickListener(buttonClickEvent -> {
            clearStyles();
			fechaButton.getStyle().set("color", "black");
			fechaButton.getStyle().set("background-color", BACKGROUND_COLOR); // Cambia el fondo del botón cuando se selecciona
            servicioButton.getStyle().set("color", "grey");
            centroButton.getStyle().set("color", "grey");

            value = null;
            //fireEvent(new ValueChangeEvent(this, FilterForm.empty(EpisodiosRestDtoDataProvider.FILTER_MENORES)));
        });

        servicioButton.addClickListener(buttonClickEvent -> {
            clearStyles();
            fechaButton.getStyle().set("color", "grey");
            servicioButton.getStyle().set("color", "black");
            servicioButton.getStyle().set("background-color", BACKGROUND_COLOR); // Cambia el fondo del botón cuando se selecciona
            centroButton.getStyle().set("color", "grey");

            value = Boolean.FALSE;
            //fireEvent(new ValueChangeEvent(this, FilterForm.of(EpisodiosRestDtoDataProvider.FILTER_MENORES, value)));
        });

        centroButton.addClickListener(buttonClickEvent -> {
            clearStyles();
            fechaButton.getStyle().set("color", "grey");
            servicioButton.getStyle().set("color", "grey");
            centroButton.getStyle().set("color", "black");
            centroButton.getStyle().set("background-color", BACKGROUND_COLOR); // Cambia el fondo del botón cuando se selecciona

            value = Boolean.TRUE;
            //fireEvent(new ValueChangeEvent(this, FilterForm.of(EpisodiosRestDtoDataProvider.FILTER_MENORES, value)));
        });

        fechaButton.getStyle().set("border", "solid 2px");
        servicioButton.getStyle().set("border", "solid 2px");
        centroButton.getStyle().set("border", "solid 2px");

        fechaButton.getStyle().set("border-bottom-left-radius", "8px");
        fechaButton.getStyle().set("border-top-left-radius", "8px");
        fechaButton.getStyle().set("border-bottom-right-radius", "0px");
        fechaButton.getStyle().set("border-top-right-radius", "0px");
        fechaButton.getStyle().set("border-right-radius", "0px");

        servicioButton.getStyle().set("border-radius", "0px");

        centroButton.getStyle().set("border-bottom-left-radius", "0px");
        centroButton.getStyle().set("border-top-left-radius", "0px");
        centroButton.getStyle().set("border-bottom-right-radius", "8px");
        centroButton.getStyle().set("border-top-right-radius", "8px");

        fechaButton.addClassName("viewIconClickable");
        servicioButton.addClassName("viewIconClickable");
        centroButton.addClassName("viewIconClickable");

        this.add(fechaButton, servicioButton, centroButton);
    }

    private void clearStyles() {
        fechaButton.getStyle().set("color", "black");
        servicioButton.getStyle().set("color", "black");
        centroButton.getStyle().set("color", "black");
        
        fechaButton.getStyle().set("background-color", "");
        servicioButton.getStyle().set("background-color", "");
        centroButton.getStyle().set("background-color", "");
    }

    public Registration addValueChangeListener(@NonNull final ComponentEventListener<ValueChangeEvent> listener) {
        return addListener(ValueChangeEvent.class, listener);
    }

    /**
     * Evento de selección de un valor en alguno de los botones del componente.
     */
    @Getter
    public static class ValueChangeEvent extends ComponentEvent<EpisodiosOrd> {
        /**
         * -- GETTER --
         * Obtiene el filtro seleccionado, nombre y valor.
         */
        private final FilterForm filterForm;

        public ValueChangeEvent(@NonNull final EpisodiosOrd source, @NonNull final FilterForm filterForm) {
            super(source, false);
            Objects.requireNonNull(filterForm, "filterForm must not be null");

            this.filterForm = filterForm;
        }

    }

}
