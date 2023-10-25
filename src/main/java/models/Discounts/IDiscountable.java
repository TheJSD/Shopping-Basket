package models.Discounts;

import models.ShoppingItem;

import java.util.HashMap;

public interface IDiscountable {
    int getPriority();
    double calculateDiscount(HashMap<ShoppingItem, Integer> items);
}
