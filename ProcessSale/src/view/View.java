package view;

import controller.Controller;
import model.Quantity;
import model.Receipt;
import model.SaleDTO;

/**
 * This class represents the user interface,
 * it's called view since it contains a printer, screen, keyboard, point of sale, etc.
 * Which we dont use. we also have hard coded calls for how a sale might go.
 */
public class View {
    private Controller contr;
    private SaleDTO saleDTO;

    /**
     *This is the constructor of the view.
     * @param contr It is an instance of a control object.
     */
    public View(Controller contr){
        this.contr = contr;
    }

    /**
     * This method is hard coded calls to illustrate how a sale might be happening,
     * since we don't have scanners, databases or external systems.
     */
    public void sampleExecution(){
        contr.startNewSale();
        System.out.println("-----Cashier starts a new sale-----");

        SaleDTO currentSale = contr.scanItem(111,new Quantity(4));
        printScanItem(currentSale);
        System.out.println("-----Cashier scans item-----");

        currentSale = contr.scanItem(222, new Quantity(2));
        printScanItem(currentSale);
        System.out.println("-----Cashier scans another item-----");

        currentSale = contr.scanItem(333, new Quantity(3));
        printScanItem(currentSale);
        System.out.println("-----Cashier scans another item-----");

        allItemsRegistered(currentSale);
        System.out.println("-----All items registered-----");

        payment(500);
        System.out.println("-----Program is complete-----");
    }

    private void printScanItem(SaleDTO saleDTO){
        System.out.println("\n" + "Item:"+ saleDTO.getItemName() + " " + saleDTO.getItemPrice() + "SEK Barcode:"
                + saleDTO.getItemBarcode() + "     Total:" + saleDTO.getTotal() + ", Tax:" + saleDTO.getTax() + ", ");
    }

    private void allItemsRegistered(SaleDTO currentSale){
        System.out.println("\n" + "Total:" + currentSale.getTotal() + ", Tax:" + currentSale.getTax());
    }

    private void payment(double amountPayed){
        Receipt receipt = contr.payment();
        System.out.println("\n" + "Change:" + receipt.calculateChange(amountPayed) + "\n" + receipt);
    }
}