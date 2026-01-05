package com.example.olshopapp;

public class CartItem {
    private String name;
    private double price;
    private int quantity;
    private boolean selected;
    private int imageResource;

    public CartItem(String name, double price, int quantity, int imageResource) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageResource = imageResource;
        this.selected = false;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isSelected() { return selected; }
    public int getImageResource() { return imageResource; }
    
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSelected(boolean selected) { this.selected = selected; }
    
    public double getTotalPrice() { return price * quantity; }
}
