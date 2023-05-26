package com.example.localshopecommerceapplication;

public class Item {
    // Declare variables
    private int id;
    private String name;
    private String category;
    private int price;
    private String version;
    private String set;
    private String description;

    public Item(int id, String name, String category, int price, String version, String set, String description)
    {
        // Initialise variables
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.version = version;
        this.set = set;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
