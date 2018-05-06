package integration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void prepareTest(){
        this.inventory = new Inventory();
    }

    @Test
    public void itemExists() {
        assertTrue("WRONG!", inventory.itemExists(111));
    }

    @Test
    public void getItem() {
        assertEquals("Banana", inventory.getItem(111).getName());
    }
}