package com.example.madmini.it20122614;

import java.io.Serializable;

public class ItemOrder implements Serializable {
    private String itemId;
    private String uId;
    private int price;
    private String itemType;


    public ItemOrder() {
    }

    public ItemOrder(String itemId, String uId, int price, String itemType) {
        this.itemId = itemId;
        this.uId = uId;
        this.price = price;
        this.itemType = itemType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}