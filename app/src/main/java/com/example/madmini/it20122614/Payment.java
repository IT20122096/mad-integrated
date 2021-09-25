package com.example.madmini.it20122614;

public class Payment {
    private String name;;
    private String imageURL;
//    private String cNumber, address, total;


    public Payment(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
