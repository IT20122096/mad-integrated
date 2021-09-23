package com.example.madmini.it20122096.models;

public class Q_Items {

   String quotation_id;
   String name;
   Float price;
   String part_id;
   int quantity;

   public Q_Items() {
   }

   public Q_Items(String quotation_id, String name, Float price, String part_id, int quantity) {
      this.quotation_id = quotation_id;
      this.name = name;
      this.price = price;
      this.part_id = part_id;
      this.quantity = quantity;
   }

   public String getQuotation_id() {
      return quotation_id;
   }

   public void setQuotation_id(String quotation_id) {
      this.quotation_id = quotation_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Float getPrice() {
      return price;
   }

   public void setPrice(Float price) {
      this.price = price;
   }

   public String getPart_id() {
      return part_id;
   }

   public void setPart_id(String part_id) {
      this.part_id = part_id;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
}
