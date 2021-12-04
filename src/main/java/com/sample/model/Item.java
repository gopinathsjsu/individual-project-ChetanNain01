package com.sample.model;

public class Item {
    private String name;
    private double price;
    private int availableQuantity;
    private String category;

    public Item(String name, double price, int availableQuantity, String category) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.category = category;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
