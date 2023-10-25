package models.Discounts;

import models.ShoppingItem;

import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;

public class BuyOneGetOne implements IDiscountable {
    private ShoppingItem discountedItem;
    private int priority;

    public BuyOneGetOne(ShoppingItem discountedItem) {
        this.discountedItem = discountedItem;
        this.priority = 1;
    }

    public ShoppingItem getDiscountedItem() {
        return discountedItem;
    }

    public int getPriority() {
        return priority;
    }

    public double calculateDiscount(HashMap<ShoppingItem, Integer> items) {
        if (items.get(discountedItem) == null || items.get(discountedItem) <= 1) {
            return 0;
        }
        int numberOfItems = items.get(discountedItem);
        int freeItems = (int)Math.floor(numberOfItems/2);
        double discount = freeItems * discountedItem.getPrice();
        return discount;
    }
    public double calculateDiscount(double amount){
        return 0;
    }
}
