package modeltests;

import models.Basket;
import models.Discounts.BuyOneGetOne;
import models.ShoppingItem;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class BuyOneGetOneTest {
    ShoppingItem biscuits;
    ShoppingItem apples;
    Basket basket;
    BuyOneGetOne buyOneGetOne;
    @Before
    public void before(){
        biscuits = new ShoppingItem("biscuits", 1.99);
        apples = new ShoppingItem("apples", 1.49);
        basket = new Basket();
        buyOneGetOne = new BuyOneGetOne(apples);
    }

    @Test
    public void hasAPriorityOf1() {
        assertEquals(1, buyOneGetOne.getPriority());
    }

    @Test
    public void canGetDiscountedItem(){
        assertEquals(apples, buyOneGetOne.getDiscountedItem());
    }

    @Test
    public void canCalculateDiscount() {
        basket.addItems(apples, 4);
        HashMap<ShoppingItem, Integer>basketItems = basket.getItems();
        assertEquals(2.98, buyOneGetOne.calculateDiscount(basketItems));
    }
}
