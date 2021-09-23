package com.example.madmini.it20122096.models;

import java.io.Serializable;

public class Quotations implements Serializable {
    String name;
    double total;
    String id;


    public Quotations() {
    }

    public Quotations(String name, double total, String id) {
        this.name = name;
        this.total = total;
        this.id=id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
