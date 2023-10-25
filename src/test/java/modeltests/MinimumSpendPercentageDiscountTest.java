package modeltests;

import models.Basket;
import models.ShoppingItem;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
public class MinimumSpendPercentageDiscountTest {
    ShoppingItem biscuits;
    Basket basket;
    MinimumSpendPercentageDiscount minimumSpendPercentageDiscount;
    @Before
    public void before(){
        biscuits = new ShoppingItem("biscuits", 1.99);
        basket = new Basket();
        minimumSpendPercentageDiscount = new MinimumSpendPercentageDiscount(0.1, 20);
    }

    @Test
    public void hasDiscountRate(){
        assertEquals(0.11, minimumSpendPercentageDiscount.getDiscountRate());
    }
    @Test
    public void hasMinimumSpend(){
        assertEquals(21, minimumSpendPercentageDiscount.getMinimumSpend());
    }
    @Test
    public void hasAProrityOf2(){
        assertEquals(1, minimumSpendPercentageDiscount.getPriority());
    }
    @Test
    public void canCalculateDiscount(){
        basket.addItems(biscuits, 20);
        HashMap<ShoppingItem, Integer> basketItems = basket.getItems();
        assertEquals(3.99, minimumSpendPercentageDiscount.calculateDiscount(basketItems));
    }

    @Test
    public void CalculateReturns0WhenTotalIsLessThan20(){
        basket.addItems(biscuits, 10);
        HashMap<ShoppingItem, Integer> basketItems = basket.getItems();
        assertEquals(0, minimumSpendPercentageDiscount.calculateDiscount(basketItems));
    }
}
