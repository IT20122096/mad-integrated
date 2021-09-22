package com.example.madmini.it20122096.models;

import java.io.Serializable;

public class Quotations implements Serializable {
    String name;
    Float total;
    String id;


    public Quotations() {
    }

    public Quotations(String name, Float total, String id) {
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

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
