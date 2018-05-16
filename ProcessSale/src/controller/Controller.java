package controller;

import integration.*;
import model.*;

/**
 *Represents the class delegating the work to the right class and method.
 */
public class Controller{
    private Inventory inventory;
    private Sale currentSale;
    private ExternalAccSys externalAccSys;
    private TotalRevenue totalRevenue;

    /**
     * This is the constructor of the object <code>Controller</code>,
     * as it is created it creates the <code>inventory</code> and the <code>external accounting system</code>
     * @param externalAccSys This is the external accounting system
     */
    public Controller(ExternalAccSys externalAccSys){
        this.inventory = new Inventory();
        this.externalAccSys = externalAccSys;
        this.totalRevenue = new TotalRevenue();
    }

    /**
     * This method calls a method in the totalRevenue class to add an observer to a list.
     * @param observer This is the observer, observing the revenue
     */
    public void addPriceObserver(TotalRevenueObserver observer){
        totalRevenue.addPriceObserver(observer);
    }

    /**
     * Cashier creates a new order to scan the customers items to.
     */
    public void startNewSale(){
        currentSale = new Sale();
    }

    /**
     * Cashier scans an item and searches in the system if the item exists and then adds it to the order.
     * If the item doesn't exist, there will be an error(exception is thrown). Also we hardcoded a
     * database failure if the barcode is 555.
     * @param barcode This is the specific number of the item which you can search for in the system.
     * @param quantity This is the quantity of the searched item
     * @return It returns a SaleDTO
     * @throws ItemNotFoundException is thrown when item is not found
     * @throws DatabaseFailureException is thrown when database failure occurs(hardcoded)
     */
    public SaleDTO scanItem(int barcode, Quantity quantity) throws DatabaseFailureException, ItemNotFoundException {
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
        totalRevenue.addRevenue(currentSale.getRunningTotal());
        return new Receipt(currentSale);
    }
}