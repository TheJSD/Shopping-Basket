package modeltests;

import models.Basket;
import models.Discounts.BuyOneGetOne;
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
    BuyOneGetOne buyOneGetOneApples;
    @Before
    public void before(){
        biscuits = new ShoppingItem("biscuits", 1.99);
        apples = new ShoppingItem("apples", 1.49);
        basket = new Basket();
        buyOneGetOne = new BuyOneGetOneApples(apples);
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

    @Test
    public void canClearItemsFromBasket(){
        basket.addItems(biscuits, 5);
        basket.addItems(apples, 4);
        basket.clearItems();
        assertEquals(0, basket.getItems().size());
    }
    @Test
    public void canCalculateTotal(){
        basket.addItems(biscuits, 5);
        basket.addItems(apples, 1);
        assertEquals(11.43, basket.caclulateTotal());
    }

    @Test
    public void canAddBuyOneGetOneDiscount(){
        basket.addDiscount(buyOneGetOneApples);
        assertEquals(0, basket.getPriority1Discounts().size());
    }

    @Test
    public void canCalculateTotalWithDiscounts(){
        basket.addItems(apples, 7);
        basket.addItems(biscuits, 3);
        basket.addDiscount(buyOneGetOneApples);
        assertEquals(11.92, basket.calculateTotal());
    }
}
