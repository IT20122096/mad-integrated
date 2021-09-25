package com.example.madmini.it20122614;

public class Order {

    private String name;
    private int price;
    private int total;
    private int quantity;
    private String part_id;
    private String itemType;


    public Order() {
    }

    public Order(String name, int price, int total, int quantity, String part_id, String itemType) {
        this.name = name;
        this.price = price;
        this.total = total;
        this.quantity = quantity;
        this.part_id = part_id;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPart_id() {
        return part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
