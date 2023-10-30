package com.globalsoftwaresupport.spring;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class EpisodiosTabbedAccordion extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private final String LIGHT_BLUE_COLOR = "#0073e6";
	private List<Episodio> episodios = new ArrayList<>();
	private Tab selectedTab;

	public EpisodiosTabbedAccordion(EpisodiosOrd episodiosOrd) {
		createLayout(episodiosOrd);
	}

	private void createLayout(EpisodiosOrd episodiosOrd) {

		this.generarEpisodiosAleatorios(7);

		// Crear las pestañas
		Tabs tabs = new Tabs();
		Tab todosTab = createTab("Todos");
		Tab urgenciasTab = createTab("Urgencias");
		Tab consultasTab = createTab("Consultas");
		Tab hospTab = createTab("Hosp");
		Tab quirofanoTab = createTab("Quirófano");

		// Agregar los indicadores (badges) a las pestañas
		todosTab.add(createBadge(this.episodios.size()));
		urgenciasTab.add(createBadge(contarEpisodiosDeTipo("urgencias")));
		consultasTab.add(createBadge(contarEpisodiosDeTipo("consultas")));
		hospTab.add(createBadge(contarEpisodiosDeTipo("hosp")));
		quirofanoTab.add(createBadge(contarEpisodiosDeTipo("quirofano")));

		Div content = new Div();

		// Agregar un SelectionListener para las pestañas
		tabs.addSelectedChangeListener(event -> {
			this.selectedTab = event.getSelectedTab();
			actualizarAccordion(content, episodiosOrd);
		});

		episodiosOrd.addValueChangeListener(event -> {
			if (episodiosOrd.getValue() != null) {
				actualizarAccordion(content, episodiosOrd);
			}
		});

		// Agregar las pestañas al layout
		tabs.add(todosTab, urgenciasTab, consultasTab, hospTab, quirofanoTab);

		// Crear un contenedor de flexbox para tabs y contenido
		Div tabsAndContentContainer = new Div();
		tabsAndContentContainer.getStyle().set("display", "flex"); // Contenedor flexbox
		tabsAndContentContainer.getStyle().set("flex-direction", "column"); // Dirección vertical

		// Aplicar estilos para controlar la anchura
		tabs.getStyle().set("flex", "1"); // Hacer que las tabs ocupen el espacio restante
		content.getStyle().set("flex", "2"); // Hacer que el contenido ocupe más espacio que las tabs
		// Agregar los componentes al contenedor flexbox
		tabsAndContentContainer.add(tabs, content);

		add(tabsAndContentContainer);
	}

	private Tab createTab(String label) {
		Tab tab = new Tab(new Text(label));
		tab.getElement().getStyle().set("cursor", "pointer"); // Agregar el cursor de puntero
		return tab;
	}

	private Span createBadge(int count) {
		Span badge = new Span(String.valueOf(count));
		badge.getStyle().set("background-color", LIGHT_BLUE_COLOR);
		badge.getStyle().set("color", "white");
		badge.getStyle().set("border-radius", "50%");
		badge.getStyle().set("padding", "4px 8px");
		badge.getStyle().set("margin-left", "5px");
		badge.getStyle().set("font-weight", "bold");
		badge.getStyle().set("font-size", "12px");
		return badge;
	}

	private VerticalLayout createDropDownList(Episodio episodio) {
		VerticalLayout dropDownList = new VerticalLayout();

		Span descripcion = new Span();
		descripcion.getElement().setProperty("innerHTML", "<b>Descripcion: </b> Fractura fémur");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fechaAltaFormateada = dateFormat.format(episodio.getFechaAlta());
		String fechaIngresoFormateada = dateFormat.format(episodio.getFechaIngreso());

		Span fechaIngreso = new Span();
		fechaIngreso.getElement().setProperty("innerHTML", "<b>Fecha Ingreso: </b> " + fechaIngresoFormateada);

		Span fechaAlta = new Span();
		fechaAlta.getElement().setProperty("innerHTML", "<b>Fecha Alta: </b>" + fechaAltaFormateada);

		Span centro = new Span();
		centro.getElement().setProperty("innerHTML", "<b>Centro: </b>" + episodio.getCentro());

		Span servicio = new Span();
		servicio.getElement().setProperty("innerHTML", "<b>Servicio: </b>" + episodio.getServicio());

		descripcion.getStyle().set("margin-left", "15px");
		fechaIngreso.getStyle().set("margin-left", "15px");
		fechaAlta.getStyle().set("margin-left", "15px");
		servicio.getStyle().set("margin-left", "15px");
		centro.getStyle().set("margin-left", "15px");

		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.getElement().getStyle().set("margin-top", "10px");

		Button botonOjo = new Button(new Icon(VaadinIcon.EYE));
		botonOjo.getElement().getStyle().set("margin-left", "15px");

		Button botonPapel = new Button(new Icon(VaadinIcon.FILE_TEXT_O));
		botonPapel.getElement().getStyle().set("margin-left", "-5%");

		buttonsLayout.add(botonOjo, botonPapel);
		buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		dropDownList.add(descripcion, fechaIngreso, fechaAlta, centro, servicio);
		dropDownList.add(buttonsLayout);
		dropDownList.setSpacing(false);
		dropDownList.setPadding(false);

		return dropDownList;
	}

	private Accordion createAccordionForTab(String tipo, List<Episodio> sortedEpisodios) {
		Accordion accordion = new Accordion();
		for (Episodio episodio : sortedEpisodios) {
			if ("todos".equals(tipo) || episodio.getTipo().equals(tipo)) {
				AccordionPanel panel = crearPanel(episodio);
				panel.addThemeVariants(DetailsVariant.SMALL);
				panel.addOpenedChangeListener(event -> {
					if (event.isOpened()) {
						// Cambiar el fondo del título cuando se abre
						panel.addThemeVariants(DetailsVariant.FILLED);
					} else {
						// Restaurar el fondo original del título cuando se cierra
						panel.removeThemeVariants(DetailsVariant.FILLED);
					}
				});
				accordion.add(panel);
			}
		}
		return accordion;
	}

	private void generarEpisodiosAleatorios(int cantidad) {

		// Listas de datos aleatorios
		List<String> nombres = new ArrayList<>();
		nombres.add("HOSPITAL SEGOVIA");
		nombres.add("HOSPITAL MAITE ROZAS");
		nombres.add("HOSPITAL SALAMANCA");
		nombres.add("HOSPITAL SEVERO OCHOA");
		nombres.add("HOSPITAL 12 OCTUBRE");
		nombres.add("HOSPITAL JOSEP TRUETA");
		nombres.add("HOSPITAL SANTA CATARINA");

		List<String> descripciones = new ArrayList<>();
		descripciones.add("Fractura Femur");
		descripciones.add("Cirugía de corazón");
		descripciones.add("Consulta de rutina");
		descripciones.add("Cirugía de rodilla");
		descripciones.add("Examen de rayos X");
		descripciones.add("Tratamiento de pruebas");
		descripciones.add("Prueba de laboratorio");

		List<String> tipos = new ArrayList<>();
		tipos.add("urgencias");
		tipos.add("consultas");
		tipos.add("quirofano");
		tipos.add("hosp");

		// Crear episodios aleatorios
		Random rand = new Random();
		for (int i = 0; i < cantidad; i++) {
			String nombre = nombres.get(i);
			String descripcion = descripciones.get(rand.nextInt(descripciones.size()));
			Date fechaAlta = generarFechaAleatoria();
			Date fechaIngreso = generarFechaAleatoria();
			String centro = this.generarNombreAleatorio();
			String servicio = this.generarNombreAleatorio();
			String tipo = tipos.get(rand.nextInt(tipos.size()));

			Episodio episodio = new Episodio(nombre, descripcion, fechaAlta, fechaIngreso, centro, servicio, tipo);
			this.episodios.add(episodio);
		}
	}

	private String generarNombreAleatorio() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder nombreAleatorio = new StringBuilder();

		Random rand = new Random();

		for (int i = 0; i < 4; i++) {
			int index = rand.nextInt(letras.length());
			char letra = letras.charAt(index);
			nombreAleatorio.append(letra);
		}

		return nombreAleatorio.toString();
	}

	private static Date generarFechaAleatoria() {
		Random rand = new Random();
		long now = System.currentTimeMillis();
		long randomTime = now - rand.nextLong() % (365 * 24 * 60 * 60 * 1000); // Fecha aleatoria en el último año
		return new Date(randomTime);
	}

	private AccordionPanel crearPanel(Episodio episodio) {
		return new AccordionPanel(episodio.getNombre(), createDropDownList(episodio));
	}

	private int contarEpisodiosDeTipo(String tipo) {
		int contador = 0;

		for (Episodio episodio : episodios) {
			if (episodio.getTipo().equals(tipo)) {
				contador++;
			}
		}

		return contador;
	}

	private List<Episodio> ordenarEpisodios(String atributoOrdenamiento) {

		List<Episodio> sortedEpisodios = new ArrayList<>(episodios);
		if (atributoOrdenamiento != null) {

			Comparator<Episodio> comparator = null;

			switch (atributoOrdenamiento) {
			case "fecha":
				comparator = Comparator.comparing(Episodio::getFechaIngreso);
				break;
			case "centro":
				comparator = Comparator.comparing(episodio -> episodio.getCentro().toLowerCase(),
						String.CASE_INSENSITIVE_ORDER);
				break;
			case "servicio":
				comparator = Comparator.comparing(episodio -> episodio.getServicio().toLowerCase(),
						String.CASE_INSENSITIVE_ORDER);
				break;
			}

			if (comparator != null) {
				sortedEpisodios.sort(comparator);
			}
		}

		return sortedEpisodios;
	}

	private String quitarAcentosYCaracteresEspeciales(String input) {
		// Normalizar la cadena utilizando la forma NFD (Descomposición canónica)
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

		// Usar una expresión regular para eliminar los caracteres no deseados
		Pattern pattern = Pattern.compile("[^\\p{ASCII}]");
		return pattern.matcher(normalized).replaceAll("");
	}

	private void actualizarAccordion(Div content, EpisodiosOrd episodiosOrd) {
		content.removeAll();
		String tipo = this.selectedTab.getLabel().toLowerCase();
		tipo = quitarAcentosYCaracteresEspeciales(tipo);
		Accordion accordion = new Accordion();

		List<Episodio> sortedEpisodios = ordenarEpisodios(episodiosOrd.getValue());

		accordion = createAccordionForTab(tipo, sortedEpisodios);

		// El Accordion está vacío
		if (accordion.getChildren().findFirst().isEmpty()) {
			Div centeredTextDiv = new Div(new Text("No hay episodios de " + tipo + " asociados."));
			centeredTextDiv.getStyle().set("display", "flex");
			centeredTextDiv.getStyle().set("margin-top", "15px");
			centeredTextDiv.getStyle().set("justify-content", "center");
			centeredTextDiv.getStyle().set("align-items", "center");
			content.add(centeredTextDiv);
		} else {
			content.add(accordion);
		}
	}

}
