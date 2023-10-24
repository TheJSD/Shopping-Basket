package modeltests;

import models.Basket;
import models.ShoppingItem;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class BasketTest {
    Basket basket;
    ShoppingItem biscuits;
    ShoppingItem apples;
    @Before
    public void before(){
        biscuits = new ShoppingItem("biscuits", 1.99);
        apples = new ShoppingItem("apples", 1.49);
        basket = new Basket();
    }
    @Test
    public void basketStartsEmpty(){
        HashMap<ShoppingItem, Integer> emptyHashMap = new HashMap<>();
        assertEquals(emptyHashMap, basket.getItems());
    }
    @Test
    public void canAddItemsToBasket(){
        basket.addItems(biscuits, 1);
        assertEquals(Optional.ofNullable(1), Optional.ofNullable(basket.getItems().get(biscuits)));
    }
    @Test
    public void canAddMultipleOfItemToBasket(){
        basket.addItems(biscuits, 2);
        assertEquals(Optional.ofNullable(2), Optional.ofNullable(basket.getItems().get(biscuits)));
    }
    @Test
    public void canAddItemsCanStack(){
        basket.addItems(biscuits, 1);
        basket.addItems(biscuits, 2);
        assertEquals(Optional.ofNullable(3), Optional.ofNullable(basket.getItems().get(biscuits)));
    }
    @Test
    public void addItemsCannotTakeNegativeNumbers(){
        basket.addItems(biscuits, -2);
        assertEquals(0, basket.getItems().size());
    }

    @Test
    public void canSubtractItemsFromBasket(){
        basket.addItems(biscuits, 2);
        basket.subtractItems(biscuits, 2);
        assertEquals(0, basket.getItems().size());
    }
}
