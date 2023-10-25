package modeltests;


import models.Discounts.LoyaltyCard;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoyaltyCardTest {
    LoyaltyCard loyaltyCard;
    @Before
    public void before(){
        loyaltyCard = new LoyaltyCard("Billy");
    }
    @Test
    public void hasMemberName(){
        assertEquals("Billy", loyaltyCard.getMemberName());
    }
    @Test
    public void hasPriority(){
        assertEquals(3, loyaltyCard.getPriority());
    }
    @Test
    public void canCalculateDiscount(){
        assertEquals(2.0, loyaltyCard.calculateDiscount(100));
    }
}
