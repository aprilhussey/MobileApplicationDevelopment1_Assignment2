package com.example.localshopecommerceapplication.models;

public class CategoryModel {
    //Declare variables
    private String categoryName;

    public CategoryModel(String categoryName)
    {
        // Initialise variables
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
