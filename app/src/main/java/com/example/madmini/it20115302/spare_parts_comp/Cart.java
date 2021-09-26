package com.example.madmini.it20115302.spare_parts_comp;

import com.google.firebase.database.Exclude;

import java.io.Serializable;


public class Cart implements Serializable {

    // attributes
    @Exclude
    private String key;
    private String userName;
    private String itemId;
    private String itemType;
    private int quantity;
    private double price;

    // default constructor
    public Cart() {
    }

    // overload constructor
    public Cart(String userName, String itemId, String itemType, int quantity, double price) {
        this.userName = userName;
        this.itemId = itemId;
        this.itemType = itemType;
        this.quantity = quantity;
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
