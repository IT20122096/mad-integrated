package com.example.madmini.it20122096.models;

import java.io.Serializable;

public class Quotations implements Serializable {
    String name;
    double total;
    String id;
    String user_id;


    public Quotations() {
    }

    public Quotations(String name, double total, String id, String user_id) {
        this.name = name;
        this.total = total;
        this.id = id;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
