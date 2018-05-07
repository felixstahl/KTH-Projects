package controller;

import integration.ExternalAccSys;
import integration.Inventory;
import integration.ItemDTO;
import model.Quantity;
import model.Receipt;
import model.Sale;
import model.SaleDTO;

/**
 *Represents the class delegating the work to the right class and method.
 */
public class Controller{
    private Inventory inventory;
    private Sale currentSale;
    private ExternalAccSys externalAccSys;

    /**
     * This is the constructor of the object <code>Controller</code>,
     * as it is created it creates the i<code>inventory</code> and the <code>external accounting system</code>
     * @param externalAccSys This is the external accounting system
     */
    public Controller(ExternalAccSys externalAccSys){
        this.inventory = new Inventory();
        this.externalAccSys = externalAccSys;
    }

    /**
     * Cashier creates a new order to scan the customers items to.
     */
    public void startNewSale(){
        currentSale = new Sale();
    }

    /**
     * Cashier scans an item and searches in the system if the item exists and then adds it to the order.
     * @param barcode This is the specific number of the item which you can search for in the system.
     * @param quantity This is the quantity of the searched item
     */
    public SaleDTO scanItem(int barcode, Quantity quantity){
        ItemDTO foundItem = inventory.getItem(barcode);
        return currentSale.addItem(foundItem, quantity);
    }

    /**
     * Cashier enters how much the customer payed and the system then calculates the change.
     * It also creates a new receipt, and sends sale information to an <code>external accounting system</code>.
     * @return It returns a receipt ready for printout.
     */
    public Receipt payment(){
        externalAccSys.logSale(currentSale);
        return new Receipt(currentSale);
    }
}