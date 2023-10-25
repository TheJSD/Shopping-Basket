package modeltests;

import models.Basket;
import models.Discounts.MinimumSpendPercentageDiscount;
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
        assertEquals(0.1, minimumSpendPercentageDiscount.getDiscountRate());
    }
    @Test
    public void hasMinimumSpend(){
        assertEquals(20D, minimumSpendPercentageDiscount.getMinimumSpend());
    }
    @Test
    public void hasAProrityOf2(){
        assertEquals(2, minimumSpendPercentageDiscount.getPriority());
    }
    @Test
    public void canCalculateDiscount(){
        assertEquals(4.0, minimumSpendPercentageDiscount.calculateDiscount(40));
    }

    @Test
    public void CalculateReturns0WhenTotalIsLessThan20(){
        assertEquals(0D, minimumSpendPercentageDiscount.calculateDiscount(19.99));
    }
}
