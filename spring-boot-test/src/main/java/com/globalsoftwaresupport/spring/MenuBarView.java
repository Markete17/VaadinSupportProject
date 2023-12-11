package com.globalsoftwaresupport.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("enlaces")
@PageTitle("Enlaces")
@CssImport("styles/styles.css")
public class MenuBarView extends HorizontalLayout {

    private static final long serialVersionUID = 1L;
    
    private List<EnlaceDTO> enlaces;

    public MenuBarView() {
        setJustifyContentMode(JustifyContentMode.CENTER);
        this.enlaces = this.getAllEnlaces();
        MenuBar menuBar = this.createMenuBar();

        Button searchButton = new Button("Buscar", VaadinIcon.SEARCH.create());
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchButton.addThemeVariants(ButtonVariant.LUMO_SMALL);

        searchButton.getElement().getStyle().set("cursor", "pointer");
        searchButton.getElement().getStyle().set("font-size", "13px");
        searchButton.getElement().getStyle().set("border-radius", "5px");
        
        Div menuBarDiv = new Div(menuBar, searchButton);
        menuBarDiv.getStyle().set("display", "flex").set("align-items", "center").set("gap", "10px");

        add(menuBarDiv);
        
        Dialog dialog = new Dialog();
        dialog.addThemeName("custom-dialog");
        searchButton.addClickListener(event -> {
            dialog.open();
        });
        
        VerticalLayout dialogLayout = createDialogLayout(dialog);
        dialog.add(dialogLayout);
    }
    
    private static VerticalLayout createDialogLayout(Dialog dialog) {
//      List<Person> items = new ArrayList<>();
//      items.add(new Person("Enlace1", 23));
//      items.add(new Person("Enlace2", 23233));
//        ComboBox<Person> comboBox = new ComboBox<>();
//        comboBox.setPlaceholder("Buscar enlace");
//        comboBox.setWidth("500px");
//        comboBox.getStyle().set("--vaadin-combo-box-overlay-width", "500px");
//        comboBox.setItems(items);
//        comboBox.setItemLabelGenerator(
//                Person::getName);
//        comboBox.setRenderer(createRenderer());

        // INPUT
        TextField textField = new TextField();
        textField.getElement().getStyle().set("padding", "0px");
        textField.setClassName("custom-text-field");
        // Agrega un icono de búsqueda a la izquierda del TextField
        Icon searchIcon = new Icon(VaadinIcon.SEARCH);
        searchIcon.getStyle().set("cursor", "pointer");
        textField.setPrefixComponent(searchIcon);

        // Establece el marcador de posición
        textField.setPlaceholder("Buscar enlace");
        
        
        // LIST BOX
        List<Person> items = new ArrayList<>();
        items.add(new Person("Enlace1", 23));
        items.add(new Person("Enlace2", 23233));
        MultiSelectListBox<Person> listBox = new MultiSelectListBox<>();
        listBox.setItems(items);
        listBox.setRenderer(new ComponentRenderer<>(person -> {
            HorizontalLayout row = new HorizontalLayout();
            row.setAlignItems(FlexComponent.Alignment.CENTER);

            Avatar avatar = new Avatar();
            avatar.setName(person.getName());

            Span name = new Span(person.getName());
            Span profession = new Span(person.getAge().toString());
            profession.getStyle()
                    .set("color", "var(--lumo-secondary-text-color)")
                    .set("font-size", "var(--lumo-font-size-s)");

            VerticalLayout column = new VerticalLayout(name, profession);
            column.setPadding(false);
            column.setSpacing(false);

            row.add(avatar, column);
            row.getStyle().set("line-height", "var(--lumo-line-height-m)");
            return row;
        }));
        
        listBox.addSelectionListener(event -> {
            Set<Person> selectedItems = event.getAllSelectedItems();
            if (!selectedItems.isEmpty()) {
                Person selectedPerson = selectedItems.iterator().next();
                
                // Abre una nueva ventana del navegador con Google.com
                UI.getCurrent().navigate("angular");
                listBox.deselectAll();
                dialog.close();
            }
        });
 

        VerticalLayout dialogLayout = new VerticalLayout(textField, listBox);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        
        return dialogLayout;
    }
    
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        menuBar.addThemeVariants(MenuBarVariant.LUMO_SMALL);

        for (EnlaceDTO enlace : this.enlaces) {
            MenuItem menuItem = menuBar.addItem(enlace.getNombre());
            menuItem.getElement().getStyle().set("cursor", "pointer");
            menuItem.getElement().getStyle().set("font-size", "13px");
            construirSubMenu(menuItem, enlace);
        }

        return menuBar;
    }

    private void construirSubMenu(MenuItem menuItem, EnlaceDTO enlace) {
        List<EnlaceDTO> subenlaces = obtenerEnlacesHijos(enlace);
        if (subenlaces!=null && !subenlaces.isEmpty()) {
            SubMenu subMenu = menuItem.getSubMenu();
            for (EnlaceDTO subenlace : subenlaces) {
                MenuItem subMenuItem = subMenu.addItem(subenlace.getNombre());
                subMenuItem.getElement().getStyle().set("cursor", "pointer");
                subMenuItem.getElement().getStyle().set("font-size", "13px");
                construirSubMenu(subMenuItem, subenlace); // Llamada recursiva para subenlaces
            }
        }
    }
    
    private List<EnlaceDTO> getEnlaces(String url) {
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<EnlaceDTO[]> responseEntity = restTemplate.getForEntity(url, EnlaceDTO[].class);
        
        return Arrays.asList(responseEntity.getBody());
    }

    private List<EnlaceDTO> obtenerEnlacesHijos(EnlaceDTO enlaceDTO) {
        String url = "http://localhost:8090/api/enlaces/padre/" + enlaceDTO.getId();
        return getEnlaces(url);
    }
    
    private List<EnlaceDTO> getAllEnlaces() {
        String url = "http://localhost:8090/api/enlaces";
        return getEnlaces(url);
    }
    
    private static Renderer<Person> createRenderer() {
        StringBuilder tpl = new StringBuilder();
        tpl.append("<a href='www.google.com'>");
        tpl.append("    ${item.name} - Anos: ${item.age}");
        tpl.append("  </a>");
        tpl.append("  </div>");
        tpl.append("</div>");

        return LitRenderer.<Person> of(tpl.toString())
                .withProperty("name", Person::getName)
                .withProperty("age", Person::getAge);
    }
}
