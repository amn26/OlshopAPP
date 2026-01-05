package com.example.olshopapp;

public class Product {
    private String name;
    private double price;
    private String description;
    private int imageResource;
    private String category;

    public Product(String name, double price, String description, int imageResource, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResource = imageResource;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getImageResource() { return imageResource; }
    public String getCategory() { return category; }
    
    public String getFormattedPrice() {
        return "Rp " + String.format("%,.0f", price);
    }
}
