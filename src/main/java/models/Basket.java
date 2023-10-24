package models;

import java.util.HashMap;

public class Basket {
    private HashMap<ShoppingItem, Integer> items;

    public Basket() {
        this.items = new HashMap<ShoppingItem, Integer>();
    }

    public HashMap<ShoppingItem, Integer> getItems() {
        return items;
    }
    public void setItems(HashMap<ShoppingItem, Integer> items) {
        this.items = items;
    }

    public void addItems(ShoppingItem item, int amount) {
        int oldTotal;
        if (this.items.get(item) == null) {
            oldTotal = 0;
        } else {
            oldTotal = this.items.get(item);
        }
        this.items.put(item, (oldTotal += amount));
    }
}
