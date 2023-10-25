package models;

import models.Discounts.IDiscountable;
import models.Discounts.LoyaltyCard;
import models.Discounts.MinimumSpendPercentageDiscount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    private HashMap<ShoppingItem, Integer> items;
    private ArrayList<IDiscountable> priority1Discounts;

    private MinimumSpendPercentageDiscount priority2Discount;
    private LoyaltyCard loyaltyCard;

    public Basket() {
        this.items = new HashMap<ShoppingItem, Integer>();
        this.priority1Discounts = new ArrayList<IDiscountable>();
        this.priority2Discount = null;
        this.loyaltyCard = null;
    }

    public HashMap<ShoppingItem, Integer> getItems() {
        return items;
    }
    public void setItems(HashMap<ShoppingItem, Integer> items) {
        this.items = items;;
    }

    public void addItems(ShoppingItem item, int amount) {
        if (amount <= 0) {
            return;
        }
        int oldTotal;
        if (this.items.get(item) == null) {
            oldTotal = 0;
        } else {
            oldTotal = this.items.get(item);
        }
        this.items.put(item, (oldTotal += amount));
    }

    public void subtractItems(ShoppingItem item, int amount) {
        if (amount <= 0 || this.items.get(item) == null) {
            return;
        }
        int oldTotal = this.items.get(item);
        this.items.put(item, oldTotal -= amount);
        if (this.items.get(item) <= 0) {
            this.items.remove(item);
        }
    }

    public void clearItems() {
        this.items = new HashMap<ShoppingItem, Integer>();
    }

    public IDiscountable getPriority2Discount() {
        return this.priority2Discount;
    }

    public ArrayList<IDiscountable> getPriority1Discounts() {
        return priority1Discounts;
    }

    public LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
    }

    public void addDiscount(IDiscountable discountToAdd) {
        int priority = discountToAdd.getPriority();
        if (priority == 1) {
            if (this.priority1Discounts.size() > 0) {
                for (IDiscountable appliedDiscount : this.priority1Discounts) {
                    if (discountToAdd == appliedDiscount) {
                        return;
                    }
                }
            }
            else this.priority1Discounts.add(discountToAdd);
        } else if (priority == 2) {
            MinimumSpendPercentageDiscount newDiscount = (MinimumSpendPercentageDiscount) discountToAdd;
            MinimumSpendPercentageDiscount existingDiscount = this.priority2Discount;
            if (existingDiscount == null || existingDiscount.getDiscountRate() < newDiscount.getDiscountRate()) {
                this.priority2Discount = newDiscount;
            }
        } else if (priority == 3) {
            this.loyaltyCard = (LoyaltyCard) discountToAdd;
        }
    }

    public double calculateTotal() {
        double runningTotal = 0;
        for (Map.Entry<ShoppingItem, Integer> entry : this.items.entrySet()) {
            ShoppingItem itemEntry = entry.getKey();
            Integer value = entry.getValue();
            runningTotal += itemEntry.getPrice() * value;
        }
        if (this.priority1Discounts.size() >= 1) {
            double priority1DiscountValue = 0;
            for (IDiscountable discount: this.priority1Discounts) {
                priority1DiscountValue += discount.calculateDiscount(this.items);
            }
            runningTotal -= priority1DiscountValue;
        }
        if (this.priority2Discount != null) {
            double discountValue = this.priority2Discount.calculateDiscount(runningTotal);
            runningTotal -= discountValue;
        }
        if (this.loyaltyCard != null) {
            double discountValue = this.loyaltyCard.calculateDiscount(runningTotal);
            runningTotal -= discountValue;
        }
        return (Math.round(runningTotal*100))/100D;
    }
}
