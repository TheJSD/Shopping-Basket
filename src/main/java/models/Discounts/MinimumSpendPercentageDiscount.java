package models.Discounts;

import models.ShoppingItem;

import java.util.HashMap;

public class MinimumSpendPercentageDiscount implements IDiscountable {
    private int priority;
    private double discountRate;
    private double minimumSpend;

    public MinimumSpendPercentageDiscount(double discountRate, double minimumSpend) {
        this.discountRate = discountRate;
        this.minimumSpend = minimumSpend;
        this.priority = 2;
    }

    public int getPriority() {
        return this.priority;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getMinimumSpend() {
        return minimumSpend;
    }
    public double calculateDiscount(double total) {
        if (total >= this.minimumSpend) {
            double discountValue = total*discountRate;
            double roundedValue = Math.round(discountValue*100)/100D;
            return roundedValue;
        }
        else return 0;
    }
    public double calculateDiscount(HashMap<ShoppingItem, Integer> items) {
        return 0;
    }
}
