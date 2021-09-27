package com.example.madmini.it20250638;

import android.os.Parcel;
import android.os.Parcelable;

public class LaptopRVModel implements Parcelable {
    private String name;
    private String price;
    private String processor;
    private String vga;
    private String other ;
    private String ram;
    private String ssd;
    private String hdd;
    private String image;
    private String laptopId;

    public LaptopRVModel() {
    }

    public LaptopRVModel(String name, String price, String processor, String vga, String other, String ram, String ssd, String hdd, String image, String laptopId) {
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.vga = vga;
        this.other = other;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.image = image;
        this.laptopId = laptopId;
    }

    protected LaptopRVModel(Parcel in) {
        name = in.readString();
        price = in.readString();
        processor = in.readString();
        vga = in.readString();
        other = in.readString();
        ram = in.readString();
        ssd = in.readString();
        hdd = in.readString();
        image = in.readString();
        laptopId = in.readString();
    }

    public String getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(String laptopId) {
        this.laptopId = laptopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getVga() {
        return vga;
    }

    public void setVga(String vga) {
        this.vga = vga;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static final Creator<LaptopRVModel> CREATOR = new Creator<LaptopRVModel>() {
        @Override
        public LaptopRVModel createFromParcel(Parcel in) {
            return new LaptopRVModel(in);
        }

        @Override
        public LaptopRVModel[] newArray(int size) {
            return new LaptopRVModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(processor);
        parcel.writeString(vga);
        parcel.writeString(other);
        parcel.writeString(ram);
        parcel.writeString(ssd);
        parcel.writeString(hdd);
        parcel.writeString(image);
    }
}
