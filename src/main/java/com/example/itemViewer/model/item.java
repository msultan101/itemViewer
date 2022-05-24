package com.example.itemViewer.model;

import org.springframework.stereotype.Component;

@Component
public class item {

    private int id ;
    private String name;
    private String cls;
    private String country;

    public item (){}

    public item(String name, String cls, String country) {
        super();
        this.name = name;
        this.cls = cls;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCls() {
        return cls;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", cls=" + cls + ", country=" + country + "]";
    }
}