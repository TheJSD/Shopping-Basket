package models;

public class ShoppingItem {
    private String name;
    private double price;

    public ShoppingItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
