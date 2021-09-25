package com.example.madmini.it20122614;

public class OrdersTable {
    private String name, cNumber, address, total, imageName;

    public OrdersTable() {
    }

    public OrdersTable(String name, String cNumber, String address, String total, String imageName) {
        this.name = name;
        this.cNumber = cNumber;
        this.address = address;

        this.total = total;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcNumber() {
        return cNumber;
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}

