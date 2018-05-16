/*package model;

import integration.Inventory;
import integration.ItemDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleDTOTest {
    private Sale testSale;
    private ItemDTO lastScannedItem;
    private Inventory inventory;
    private SaleDTO saleDTO;

    @Before
    public void prepareTest(){
        this.inventory = new Inventory();
        this.testSale = new Sale();
        this.lastScannedItem = inventory.getItem(111);
        this.saleDTO = testSale.addItem(lastScannedItem, new Quantity (1));
    }

    @Test
    public void getTotal() {
        assertEquals(10, saleDTO.getTotal(), 0);
    }

    @Test
    public void getTax() {
    assertEquals(1.2,saleDTO.getTax(),0);
    }

    @Test
    public void getItemName() {
        assertEquals("Banana", saleDTO.getItemName());
    }

    @Test
    public void getItemPrice() {
        assertEquals(10, saleDTO.getItemPrice(),0);
    }

    @Test
    public void getItemBarcode() {
        assertEquals(111, saleDTO.getItemBarcode(),0);
    }
}*/