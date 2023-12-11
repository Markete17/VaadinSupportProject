package com.globalsoftwaresupport.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.vaadin.addons.componentfactory.Autosuggest;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("search")
public class SearchTest extends HorizontalLayout {
    
    private static final String[] FRUIT_NAMES = {"Apple", "Banana", "Orange", "Grapes", "Watermelon"};
    
    public SearchTest() {

        VerticalLayout col2 = new VerticalLayout();
        Autosuggest<Fruit> autosuggest23 = new Autosuggest<>();
        autosuggest23.setItems(generateItemsMap());
        autosuggest23.setOpenDropdownOnClick(true);
        autosuggest23.setLabelGenerator(item -> item.getName().toLowerCase(Locale.ROOT));
        autosuggest23.setSearchStringGenerator(item -> "fwc " + item.getName());
        autosuggest23.setOptionTemplate("<button class=\"aaa\" style=\"color:blue;\">${option.label} ${option.optId}</button>");
        col2.add(new Span("Objects + template provider + customSearch (fwc xxxxx)"), autosuggest23);
        add(col2);
    }

    private Collection<Fruit> generateItemsMap() {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("banana"));
        return fruits;
    }

}
