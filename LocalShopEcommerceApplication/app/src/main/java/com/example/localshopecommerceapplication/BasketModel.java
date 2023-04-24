package com.example.localshopecommerceapplication;

public class BasketModel {
    int itemImage;
    String itemName;
    String itemPrice;
    String itemVer;

    // Constructor
    public BasketModel(int itemImage, String itemName, String itemPrice, String itemVer) {
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemVer = itemVer;
    }

    // Getters and setters
    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemVer() {
        return itemVer;
    }

    public void setItemVer(String itemVer) {
        this.itemVer = itemVer;
    }
}
