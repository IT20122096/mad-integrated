package com.example.madmini.it20122096.models;

import java.io.Serializable;

public class Orders implements Serializable {
    String id,Address,quotation_id,phone_num,name,date,image;
    double total;

    public Orders(String id, String address, String quotation_id, String phone_num, String name, String date, String image, double total) {
        this.id = id;
        Address = address;
        this.quotation_id = quotation_id;
        this.phone_num = phone_num;
        this.name = name;
        this.date = date;
        this.image = image;
        this.total = total;
    }

    public Orders() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getQuotation_id() {
        return quotation_id;
    }

    public void setQuotation_id(String quotation_id) {
        this.quotation_id = quotation_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
