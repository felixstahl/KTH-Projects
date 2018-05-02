package controller;

import integration.ExternalAccSys;
import integration.Inventory;
import integration.ItemDTO;
import model.Quantity;
import model.Receipt;
import model.Sale;

public class Controller{
    private Inventory inventory;
    private Sale currentSale;
    private ExternalAccSys externalAccSys;

    public Controller(ExternalAccSys externalAccSys){
        this.inventory = new Inventory();
        this.externalAccSys = externalAccSys;
    }

    public void startNewSale(){
        currentSale = new Sale();
    }

    public void scanItem(int barcode, Quantity quantity){
        ItemDTO foundItem = inventory.getItem(barcode);
        currentSale.addItem(foundItem, quantity);
    }

    public void allItemsRegistered(){
        System.out.println("\n" + currentSale.toString());
    }

    public void payment(double amountPaid){
        externalAccSys.logSale(currentSale);
        Receipt receipt = new Receipt(currentSale);
        System.out.println(receipt);
        System.out.println("\n" + "Amount of change back:" + currentSale.calculateChange(amountPaid));
    }
}