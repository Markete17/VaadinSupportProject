package com.globalsoftwaresupport.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("menu-bar-overflow")
public class MenuBarOverflow extends Div {

    private static final long serialVersionUID = 1L;

    public MenuBarOverflow(List<EnlaceDTO> enlaces, Map<String, List<EnlaceDTO>> enlacesMap) {

        MenuBar menuBar = new MenuBar();
        Text selected = new Text("");
        ComponentEventListener<ClickEvent<MenuItem>> listener = e -> selected
                .setText(e.getSource().getText());
        Div message = new Div(new Text("Clicked item: "), selected);

        menuBar.addItem("View", listener);
        menuBar.addItem("Edit", listener);

        MenuItem share = menuBar.addItem("Share");
        SubMenu shareSubMenu = share.getSubMenu();
        MenuItem onSocialMedia = shareSubMenu.addItem("On social media");
        SubMenu socialMediaSubMenu = onSocialMedia.getSubMenu();
        socialMediaSubMenu.addItem("Facebook", listener);
        socialMediaSubMenu.addItem("Twitter", listener);
        socialMediaSubMenu.addItem("Instagram", listener);
        shareSubMenu.addItem("By email", listener);
        shareSubMenu.addItem("Get Link", listener);

        MenuItem move = menuBar.addItem("Move");
        SubMenu moveSubMenu = move.getSubMenu();
        moveSubMenu.addItem("To folder", listener);
        moveSubMenu.addItem("To trash", listener);

        menuBar.addItem("Duplicate", listener);
        add(menuBar);
    }
    
    private MenuBar createMenuBar(List<EnlaceDTO> enlaces, Map<String, List<EnlaceDTO>> enlacesMap) {
        MenuBar menuBar = new MenuBar();
        menuBar.setOpenOnHover(true);
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        menuBar.addThemeVariants(MenuBarVariant.LUMO_LARGE);

        for (EnlaceDTO enlace : enlaces) {
            MenuItem menuItem = menuBar.addItem(enlace.getNombre());
            
            menuItem.getElement().getStyle().set("cursor", "pointer");
            menuItem.getElement().getStyle().set("font-size", "13px");
            
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(enlace.getNombre());
            
            buildSubMenu(menuItem, enlace, strBuilder, enlacesMap);
        }

        return menuBar;
    }

    private void buildSubMenu(MenuItem menuItem, EnlaceDTO link, StringBuilder strBuilder, Map<String, List<EnlaceDTO>> enlacesMap) {
        List<EnlaceDTO> subLinks = getChildLinks(link);
        
        if (subLinks != null && !subLinks.isEmpty()) {
            buildSubMenuItems(menuItem.getSubMenu(), subLinks, strBuilder, enlacesMap);
        } else {
            addClickListener(menuItem, link);

            String part = " / " + link.getNombre();
            int index = strBuilder.indexOf(part);
            if (index != -1) {
                strBuilder.delete(index, index + part.length());
            }
            
            String key = strBuilder.toString();
            enlacesMap.merge(key, new ArrayList<>(List.of(link)), (existingList, newList) -> {
                existingList.addAll(newList);
                return existingList;
            });

        }
    }

    private void buildSubMenuItems(SubMenu subMenu, List<EnlaceDTO> subLinks, StringBuilder strBuilder, Map<String, List<EnlaceDTO>> enlacesMap) {
        for (EnlaceDTO subLink : subLinks) {
            StringBuilder subStringBuilder = new StringBuilder(strBuilder.toString()); // Crear una copia del StringBuilder
            MenuItem subMenuItem = subMenu.addItem(subLink.getNombre());
            subStringBuilder.append(" / ");
            subStringBuilder.append(subLink.getNombre());
            subMenuItem.getElement().getStyle().set("cursor", "pointer");
            subMenuItem.getElement().getStyle().set("font-size", "13px");
            buildSubMenu(subMenuItem, subLink, subStringBuilder, enlacesMap); // Llamada recursiva
        }
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

        notification.add(layout);
        notification.open();
    }

}
