package model;

import integration.Inventory;
import integration.ItemDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleTest {
    private Sale testSale;
    private Inventory inventory;
    private SaleDTO saleDTO;

    @Before
    public void prepareTest(){
        this.inventory = new Inventory();
        testSale = new Sale();
        ItemDTO foundItem = inventory.getItem(111);
        this.saleDTO = testSale.addItem(foundItem, new Quantity (1));
    }

    @Test
    public void addItem() {
        assertEquals("Banana", saleDTO.getItemName());
        assertEquals(10, saleDTO.getTotal(), 0);
    }

    @Test
    public void calculateTax() {
        assertEquals(1.2,testSale.calculateTax(),0);
    }
}