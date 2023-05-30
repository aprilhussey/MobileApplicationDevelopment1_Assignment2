package com.example.localshopecommerceapplication.models;

public class ItemModel {
    // Declare variables
    private int id;
    private String name;
    private String category;
    private String price;
    private String version;
    private String set;
    private String imageFilePath;
    private String description;
    private int inStock;

    public ItemModel(int id, String name, String category, String price, String version, String set, String imageFilePath, String description, int inStock)
    {
        // Initialise variables
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.version = version;
        this.set = set;
        this.imageFilePath = imageFilePath;
        this.description = description;
        this.inStock = inStock;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}
