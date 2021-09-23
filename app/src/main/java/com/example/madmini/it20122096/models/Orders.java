package com.example.madmini.it20122096.models;

import java.io.Serializable;

public class Orders implements Serializable {
    String id,Address,quotation_id,phone_num,name,date;
    double total;

    public Orders() {
    }

    public Orders(String id, String address, String quotation_id, String phone_num, double total,String name,String date) {
        this.id = id;
        Address = address;
        this.quotation_id = quotation_id;
        this.phone_num = phone_num;
        this.total = total;
        this.name=name;
        this.date=date;
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
