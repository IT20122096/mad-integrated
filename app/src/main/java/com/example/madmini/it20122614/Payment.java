package com.example.madmini.it20122614;

import java.io.Serializable;

public class Payment implements Serializable {
    private String uId;
    ;
    private String imageURL;
    private String itemType;
    private int prices;
    private String itemId;
    private String date;
    private String quotation_id;
//    private String cNumber, address, total;


    public Payment() {
    }

    public Payment(String uId, String imageURL, String itemType, int prices, String itemId, String date, String quotation_id) {
        this.uId = uId;
        this.imageURL = imageURL;
        this.itemType = itemType;
        this.prices = prices;
        this.itemId = itemId;
        this.date = date;
        this.quotation_id = quotation_id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuotation_id() {
        return quotation_id;
    }

    public void setQuotation_id(String quotation_id) {
        this.quotation_id = quotation_id;
    }
}


