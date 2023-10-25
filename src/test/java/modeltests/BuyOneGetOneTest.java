package modeltests;

import models.Basket;
import models.ShoppingItem;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

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
    public void canGetDiscount() {
        basket.addItems(apples, 4);
        HashMap<ShoppingItem, Integer>basketItems = basket.getItems();
        assertEquals(2.99, buyOneGetOne.calculateDiscount(basketItems));
    }
}
