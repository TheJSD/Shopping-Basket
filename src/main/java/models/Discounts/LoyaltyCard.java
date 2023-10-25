package models.Discounts;

import models.ShoppingItem;

import java.util.HashMap;

public class LoyaltyCard implements IDiscountable{
    private String memberName;
    private int priority;

    public LoyaltyCard(String memberName) {
        this.memberName = memberName;
        this.priority = 3;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getPriority() {
        return priority;
    }

    public double calculateDiscount(double amount) {
        return amount*0.02;
    }

    public double calculateDiscount(HashMap<ShoppingItem, Integer> items) {
        return 0;
    }
}
