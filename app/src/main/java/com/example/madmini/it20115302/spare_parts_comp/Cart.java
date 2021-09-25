package com.example.madmini.it20115302.spare_parts_comp;

import com.google.firebase.database.Exclude;


public class Cart {

    // attributes
    @Exclude
    private String key;
    private String userName;
    private String itemId;
    private String itemType;
    private double price;

    // default constructor
    public Cart() {
    }

    // overload constructor
    public Cart(String userName, String itemId, String itemType, double price) {
        this.userName = userName;
        this.itemId = itemId;
        this.itemType = itemType;
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
}
