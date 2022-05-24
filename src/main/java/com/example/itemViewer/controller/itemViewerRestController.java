package com.example.itemViewer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.itemViewer.service.itemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.itemViewer.model.item;


@RestController
public class itemViewerRestController {

    List<item> items = new ArrayList<item>();
    {
        items.add(new item("Sajal", "IV", "India"));
        items.add(new item("Lokesh", "V", "India"));
        items.add(new item("Kajal", "III", "USA"));
        items.add(new item("Sukesh", "VI", "USA"));
    }
    @Autowired
    itemServices instance;

    @RequestMapping(value = "/getItems")
    public List<item> getItems() {
        return items;
    }

    @RequestMapping(value = "/getItem/{name}")
    public item geIitem(@PathVariable(value = "name") String name) {
        return items.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
    }

    @RequestMapping(value = "/getItemByCountry/{country}")
    public List<item> getItemByCountry(@PathVariable(value = "country") String country) {
        System.out.println("Searching item in country : " + country);
        List<item> itemsByCountry = items.stream().filter(x -> x.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        System.out.println(itemsByCountry);
        return itemsByCountry;
    }

    @RequestMapping(value = "/getItemByClass/{cls}")
    public List<item> getItemByClass(@PathVariable(value = "cls") String cls) {
        return items.stream().filter(x -> x.getCls().equalsIgnoreCase(cls)).collect(Collectors.toList());
    }

}
