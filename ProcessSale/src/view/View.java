package view;

import controller.Controller;
import integration.DatabaseFailureException;
import integration.ItemNotFoundException;
import model.Quantity;
import model.Receipt;
import model.SaleDTO;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * This class represents the user interface,
 * it's called view since it contains a printer, screen, keyboard, point of sale, etc.
 * Which we don't use. We also have hard coded calls for how a sale might go.
 */
public class View {
    private Controller contr;
    private SaleDTO currentSale;

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

        Scan(111, new Quantity(4));
        System.out.println("-----Cashier scans item-----");

        Scan(222, new Quantity(4));
        System.out.println("-----Cashier scans another item-----");

        Scan(335, new Quantity(3));
        System.out.println("-----Cashier scans another item-----");

        Scan(555, new Quantity(4));
        System.out.println("-----Cashier scans another item-----");

        allItemsRegistered(currentSale);
        System.out.println("-----All items registered-----");

        payment(500);
        System.out.println("-----Program is complete-----");
    }

    private void Scan(int barcode, Quantity o) {
        try {
            SaleDTO sale = contr.scanItem(barcode, o);
            currentSale = sale == null ? currentSale : sale;
            printScanItem(currentSale);
        }catch (ItemNotFoundException e){
            printItemNotFoundException(e);
        }catch (DatabaseFailureException d){
            printDatabaseFailureException(d);
        }
    }

    private void printItemNotFoundException(ItemNotFoundException e){
        System.out.print("\n" + "[UserLog] " + e.getMessage() + "\n");
    }

    private void printDatabaseFailureException(DatabaseFailureException d){
        System.out.println("\n" + "[UserLog] Could not load database");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        d.printStackTrace(pw);
        System.out.println("[DeveloperLog] " + sw.toString().toUpperCase());
    }

    private void printScanItem(SaleDTO saleDTO){
            System.out.println("\n" + "Item:"+ saleDTO.getItemName() + " " + saleDTO.getItemPrice() + "SEK Barcode:" +
                    saleDTO.getItemBarcode() + "     Total:" + saleDTO.getTotal() + ", Tax:" + saleDTO.getTax() + ", ");
    }

    private void allItemsRegistered(SaleDTO saleDTO){
        if (saleDTO == null)
            return;
        System.out.println("\n" + "Total:" + saleDTO.getTotal() + ", Tax:" + saleDTO.getTax());
    }

    private void payment(double amountPayed){
        Receipt receipt = contr.payment();
        System.out.println("\n" + "Change:" + receipt.calculateChange(amountPayed) + "\n" + receipt);
    }
}