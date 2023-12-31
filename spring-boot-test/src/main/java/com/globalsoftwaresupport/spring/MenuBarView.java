package com.globalsoftwaresupport.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("enlaces")
@PageTitle("Enlaces")
@CssImport("styles/enlaces-styles.css")
public class MenuBarView extends Div {

    private static final long serialVersionUID = 1L;
    
    private transient List<EnlaceDTO> enlaces;
    
    private Div content = new Div();
    
    private Dialog dialog = new Dialog();
    
    private TextField searchField = new TextField();
    
    private transient Map<String, List<EnlaceDTO>> enlacesMap = new HashMap<>();
   

    public MenuBarView() {

        this.enlaces = this.getAllEnlaces();
        
        this.createMenuBar();
        
        this.configureDialog();

    }
    
    private VerticalLayout createDialogLayout() {

        configureSearchField();
        updateContent();

        VerticalLayout dialogLayout = new VerticalLayout(searchField, content);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        
        return dialogLayout;
    }
    
    private void configureSearchField() {
        searchField.getElement().getStyle().set("padding", "0px");
        searchField.setClassName("custom-text-field");

        Icon searchIcon = new Icon(VaadinIcon.SEARCH);
        searchIcon.addClassName("pointer-icon");
        searchField.setPrefixComponent(searchIcon);

        searchField.setPlaceholder("Buscar enlace");
        
        searchField.addValueChangeListener(event -> updateContent());

    }
    
    private void createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        menuBar.addThemeVariants(MenuBarVariant.LUMO_LARGE);

        for (EnlaceDTO enlace : this.enlaces) {
            MenuItem menuItem = menuBar.addItem(enlace.getNombre());
            
            menuItem.getElement().getStyle().set("color", "var(--lumo-primary-text-color)");
            menuItem.getElement().getStyle().set("font-weight", "500");
            menuItem.getElement().getStyle().set("cursor", "pointer");
            menuItem.getElement().getStyle().set("font-size", "13px");
            
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(enlace.getNombre());
            
            buildSubMenu(menuItem, enlace, strBuilder);
        }
        
        Button searchButton = createAndConfigureSearchButton();
        
        MenuItem searchButtonItem = menuBar.addItem(searchButton);
        searchButtonItem.getElement().getStyle().set("background-color", "transparent !important");

        add(menuBar);
    }
    
    private void configureDialog() {
        dialog.addThemeName("custom-dialog");
        VerticalLayout dialogLayout = createDialogLayout();
        dialog.add(dialogLayout);
    }
    
    private void buildSubMenu(MenuItem menuItem, EnlaceDTO link, StringBuilder strBuilder) {
        List<EnlaceDTO> subLinks = getChildLinks(link);
        
        if (subLinks != null && !subLinks.isEmpty()) {
            buildSubMenuItems(menuItem.getSubMenu(), subLinks, strBuilder);
        } else {
            addClickListener(menuItem, link);

            String part = " / " + link.getNombre();
            int index = strBuilder.indexOf(part);
            if (index != -1) {
                strBuilder.delete(index, index + part.length());
            }
            
            String key = strBuilder.toString();
            this.enlacesMap.merge(key, new ArrayList<>(List.of(link)), (existingList, newList) -> {
                existingList.addAll(newList);
                return existingList;
            });

        }
    }

    private void buildSubMenuItems(SubMenu subMenu, List<EnlaceDTO> subLinks, StringBuilder strBuilder) {
        for (EnlaceDTO subLink : subLinks) {
            StringBuilder subStringBuilder = new StringBuilder(strBuilder.toString()); // Crear una copia del StringBuilder
            MenuItem subMenuItem = subMenu.addItem(subLink.getNombre());
            subStringBuilder.append(" / ");
            subStringBuilder.append(subLink.getNombre());
            subMenuItem.getElement().getStyle().set("cursor", "pointer");
            subMenuItem.getElement().getStyle().set("font-size", "13px");
            buildSubMenu(subMenuItem, subLink, subStringBuilder); // Llamada recursiva
        }
    }

    private void addClickListener(MenuItem menuItem, EnlaceDTO enlace) {
        menuItem.addClickListener(event -> {
            String url = enlace.getUrl();

            if (url != null) {
                openPopupWindow(url);
            } else {
                showLinkNotAvailableError();
            }
        });
    }

    private void openPopupWindow(String url) {
        String script = "window.open('" + url + "', '_blank', 'height=500,width=800,resizable=yes,scrollbars=yes')";
        VaadinSession.getCurrent().getUIs().forEach(ui -> ui.getPage().executeJs(script));
    }

    private void showLinkNotAvailableError() {
        Div text = new Div(new Text("El enlace no está disponible"));
        text.addClassName("small-notification-div");

        Notification notification = new Notification();
        notification.setDuration(4000);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.TOP_END);

        Icon icon = VaadinIcon.WARNING.create();
        icon.addClassName("icon-style");

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.getElement().getStyle().set("padding-right", "0");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().getStyle().set("cursor", "pointer");
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(e -> notification.close());

        HorizontalLayout layout = new HorizontalLayout(icon, text, closeButton);
        layout.setSpacing(false);
        layout.setPadding(false);
        
        // Alinea los elementos verticalmente al centro
        layout.setAlignItems(Alignment.CENTER);

        notification.add(layout);
        notification.open();
    }

    
    private Notification getNotificationInstance() {
        // Puedes personalizar la creación de la instancia de Notification según tus necesidades
        Notification notification = new Notification("Mensaje de notificación");
        notification.setPosition(Notification.Position.MIDDLE);
        return notification;
    }
    
    private List<EnlaceDTO> getEnlaces(String url) {
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<EnlaceDTO[]> responseEntity = restTemplate.getForEntity(url, EnlaceDTO[].class);
        
        return Arrays.asList(responseEntity.getBody());
    }

    private List<EnlaceDTO> getChildLinks(EnlaceDTO enlaceDTO) {
        String url = "http://localhost:8090/api/enlaces/padre/" + enlaceDTO.getId();
        return getEnlaces(url);
    }
    
    private List<EnlaceDTO> getAllEnlaces() {
        String url = "http://localhost:8090/api/enlaces";
        return getEnlaces(url);
    }
    
    private static HorizontalLayout createRowLayout(EnlaceDTO enlace) {
        HorizontalLayout row = new HorizontalLayout();
        row.setAlignItems(FlexComponent.Alignment.CENTER);

        Span name = createSpan(enlace.getNombre(), "30%");
        Span profession = createSpan(enlace.getDescripcion(), "70%");

        row.add(name, profession);
        row.getStyle().set("line-height", "var(--lumo-line-height-m)");

        return row;
    }

    private static Span createSpan(String text, String width) {
        Span span = new Span(text);
        span.getStyle().set("font-size", "var(--lumo-font-size-xs)");
        span.setWidth(width);
        return span;
    }
    
    private void updateContent() {
        
        String searchValue = searchField.getValue();
        
        this.enlacesMap.keySet().forEach(header -> {
            List<EnlaceDTO> list = this.enlacesMap.get(header);

                MultiSelectListBox<EnlaceDTO> listBox = new MultiSelectListBox<>();
                listBox.setItems(list);

                listBox.setRenderer(new ComponentRenderer<>(MenuBarView::createRowLayout));

                listBox.addSelectionListener(event -> {

                   Set<EnlaceDTO> selectedSet = event.getValue();
                   
                   if(selectedSet!=null && !selectedSet.isEmpty()) {
                       EnlaceDTO selected = selectedSet.iterator().next();
                       if(selected.getUrl()!=null && selected.getUrl()!="") {
                           this.openPopupWindow(selected.getUrl());
                       } else {
                           this.showLinkNotAvailableError();
                       }
                   }
                   listBox.deselectAll();
                   
                });

                Span span = new Span(header);
                span.addClassName("custom-span-header");

                content.add(span, listBox);
        });
    }
    
    private Button createAndConfigureSearchButton() {
        Button searchButton = new Button("Buscar", VaadinIcon.SEARCH.create());
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        searchButton.getElement().getStyle().set("cursor", "pointer");
        searchButton.getElement().getStyle().set("font-size", "13px");
        searchButton.getElement().getStyle().set("border-radius", "5px");

        searchButton.addClassName("search-button");

        searchButton.addClickListener(event -> dialog.open());

        return searchButton;
    }
}
