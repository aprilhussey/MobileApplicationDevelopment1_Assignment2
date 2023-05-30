package com.example.localshopecommerceapplication.models;

public class OrderModel {
    // Declare variables
    private int id;
    private String address;
    private String items;
    private String email;

    public OrderModel(int id, String address, String items, String email)
    {
        // Initialise variables
        this.id = id;
        this.address = address;
        this.items = items;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
