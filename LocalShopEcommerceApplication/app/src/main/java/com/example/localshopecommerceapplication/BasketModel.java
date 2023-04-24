package com.example.localshopecommerceapplication;

import android.media.Image;

public class BasketModel {
    Image itemImage;
    String itemName;
    String itemPrice;
    String[] itemVersions;

    // Constructor
    public BasketModel(Image itemImage, String itemName, String itemPrice, String[] itemVersions) {
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemVersions = itemVersions;
    }

    // Getters and setters
    public Image getItemImage() {
        return itemImage;
    }

    public void setItemImage(Image itemImage) {
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

    public String[] getItemVersions() {
        return itemVersions;
    }

    public void setItemVersions(String[] itemVersions) {
        this.itemVersions = itemVersions;
    }
}
