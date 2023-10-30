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

	private static final String BACKGROUND_COLOR = "#0073e6"; // Azul más claro y oscuro
	private static final String LIGHT_TEXT_COLOR = "#ffffff"; // Color de texto en fondo oscuro
	private static final String DARK_TEXT_COLOR = "#000000"; // Color de texto en fondo claro

	private final Button fechaButton = createStyledButton("Fecha");
	private final Button servicioButton = createStyledButton("Servicio");
	private final Button centroButton = createStyledButton("Centro");

	@Getter
	private String value;

	public EpisodiosOrd() {
		super();
		createLayout();
	}

	public void clear() {
		clearStyles();
		value = null;
	}

	private Button createStyledButton(String text) {
		Button button = new Button(text);
		button.getStyle().set("background-color", "transparent");
		button.getStyle().set("color", DARK_TEXT_COLOR); // Texto oscuro por defecto
		button.getStyle().set("border", "2px solid #ccc"); // Borde más sutil
		button.getStyle().set("border-radius", "8px");
		button.getStyle().set("cursor", "pointer"); // Agregar el cursor de puntero
		button.addClassName("viewIconClickable");
		return button;
	}

	private void createLayout() {
		this.setSpacing(false);

		fechaButton.addClickListener(buttonClickEvent -> {
			clearStyles();
			fechaButton.getStyle().set("background-color", BACKGROUND_COLOR);
			fechaButton.getStyle().set("color", LIGHT_TEXT_COLOR); // Texto claro cuando se selecciona
			value = "fecha";
			fireEvent(new ValueChangeEvent(this, value));
		});

		servicioButton.addClickListener(buttonClickEvent -> {
			clearStyles();
			servicioButton.getStyle().set("background-color", BACKGROUND_COLOR);
			servicioButton.getStyle().set("color", LIGHT_TEXT_COLOR); // Texto claro cuando se selecciona
			value = "servicio";
			fireEvent(new ValueChangeEvent(this, value));
		});

		centroButton.addClickListener(buttonClickEvent -> {
			clearStyles();
			centroButton.getStyle().set("background-color", BACKGROUND_COLOR);
			centroButton.getStyle().set("color", LIGHT_TEXT_COLOR); // Texto claro cuando se selecciona
			value = "centro";
			fireEvent(new ValueChangeEvent(this, value));
		});

		this.add(fechaButton, servicioButton, centroButton);
	}

	private void clearStyles() {
		fechaButton.getStyle().set("background-color", "transparent");
		fechaButton.getStyle().set("color", DARK_TEXT_COLOR); // Restablecer el color de texto
		servicioButton.getStyle().set("background-color", "transparent");
		servicioButton.getStyle().set("color", DARK_TEXT_COLOR); // Restablecer el color de texto
		centroButton.getStyle().set("background-color", "transparent");
		centroButton.getStyle().set("color", DARK_TEXT_COLOR); // Restablecer el color de texto
	}

	public Registration addValueChangeListener(@NonNull final ComponentEventListener<ValueChangeEvent> listener) {
		return addListener(ValueChangeEvent.class, listener);
	}

	@Getter
	public static class ValueChangeEvent extends ComponentEvent<EpisodiosOrd> {
		private static final long serialVersionUID = 1L;
		private final String value;

		public ValueChangeEvent(@NonNull final EpisodiosOrd source, @NonNull final String value) {
			super(source, false);
			Objects.requireNonNull(value, "value must not be null");
			this.value = value;
		}
	}
}
