package view;

import controller.Controller;
import model.Quantity;

public class View {
    private Controller contr;

    public View(Controller contr){
        this.contr = contr;
    }

    public void sampleExecution(){
        contr.startNewSale();
        System.out.println("-----Cashier starts a new sale-----");

        contr.scanItem(111, new Quantity(4));
        System.out.println("-----Cashier scans item-----");

        contr.scanItem(333, new Quantity(2));
        System.out.println("-----Cashier scans another item-----");

        contr.scanItem(333, new Quantity(2));
        System.out.println("-----Cashier scans another item-----");

        contr.allItemsRegistered();
        System.out.println("-----All items registered-----");

        contr.payment(500);
        System.out.println("-----Program is complete-----");
    }
}