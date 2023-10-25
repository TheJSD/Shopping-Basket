package modeltests;

import models.Basket;
import models.Discounts.BuyOneGetOne;
import models.Discounts.LoyaltyCard;
import models.Discounts.MinimumSpendPercentageDiscount;
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
    BuyOneGetOne buyOneGetOneBiscuits;
    MinimumSpendPercentageDiscount minimumSpendPercentageDiscount;
    @Before
    public void before(){
        biscuits = new ShoppingItem("biscuits", 1.99);
        apples = new ShoppingItem("apples", 1.49);
        basket = new Basket();
        buyOneGetOneApples = new BuyOneGetOne(apples);
        buyOneGetOneBiscuits = new BuyOneGetOne(biscuits);
        minimumSpendPercentageDiscount = new MinimumSpendPercentageDiscount(0.1, 20);
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
        assertEquals(11.44, basket.calculateTotal());
    }

    @Test
    public void buyOneGetOneStartsEmpty(){
        assertEquals(0, basket.getPriority1Discounts().size());
    }
    @Test
    public void canAddBuyOneGetOneDiscount(){
        basket.addDiscount(buyOneGetOneApples);
        assertEquals(1, basket.getPriority1Discounts().size());
    }

    @Test
    public void canCalculateTotalWithBuyOneGetOneDiscount(){
        basket.addItems(apples, 7);
        basket.addItems(biscuits, 3);
        basket.addDiscount(buyOneGetOneApples);
        assertEquals(11.93, basket.calculateTotal());
    }
    @Test
    public void canCalculateTotalWithMultipleBuyOneGetOneDiscounts(){
        basket.addItems(apples, 7);
        basket.addItems(biscuits, 3);
        basket.addDiscount(buyOneGetOneApples);
        basket.addDiscount(buyOneGetOneBiscuits);
        assertEquals(11.93, basket.calculateTotal());
    }
    @Test
    public void priority2DiscountStartsNull(){
        assertEquals(null, basket.getPriority2Discount());
    }

    @Test
    public void canAddMinimumSpendPercentageDiscount(){
        basket.addDiscount(minimumSpendPercentageDiscount);
        assertEquals(minimumSpendPercentageDiscount, basket.getPriority2Discount());
    }
    @Test
    public void canCalculateTotalWithMinimumSpendDiscount(){
        basket.addItems(apples, 10);
        basket.addItems(biscuits, 10);
        basket.addDiscount(minimumSpendPercentageDiscount);
        assertEquals(31.32, basket.calculateTotal());
    }
    @Test
    public void canAddLoyaltyCard(){
        LoyaltyCard loyaltyCard = new LoyaltyCard("Bob");
        basket.addDiscount(loyaltyCard);
        assertEquals(loyaltyCard, basket.getLoyaltyCard());
    }
    @Test
    public void canCalculateTotalWithLoyaltyCard(){
        basket.addItems(apples, 100);
        basket.addDiscount(new LoyaltyCard("Bob"));
        assertEquals(146.02, basket.calculateTotal());
    }
}
