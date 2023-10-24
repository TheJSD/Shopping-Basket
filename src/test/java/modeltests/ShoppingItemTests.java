package modeltests;

import models.ShoppingItem;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ShoppingItemTests {
    ShoppingItem shoppingItem;
    @Before
    public void before() {
        shoppingItem = new ShoppingItem("Biscuits", 1.99);
    }
    @Test
    public void canGetName(){
        assertEquals("Biscuits", shoppingItem.getName());
    }
    @Test
    public void canGetPrice(){
        assertEquals(1.99, shoppingItem.getPrice());
    }

}
