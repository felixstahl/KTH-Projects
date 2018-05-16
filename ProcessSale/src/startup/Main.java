package startup;

import controller.Controller;
import integration.ExternalAccSys;
import view.TotalRevenueView;
import view.View;

/**
 * Contains the main method. Creates the necessary object before starting the 'real' program of performing a sale.
 */
public class Main {

    /**
     * This is the main method, creating and starting the program.
     * @param args the program does not take any command line arguments.
     */
    public static void main(String[] args) {
        ExternalAccSys externalAccSys = new ExternalAccSys();
        Controller contr = new Controller(externalAccSys);
        View view = new View(contr);
        contr.addPriceObserver(new TotalRevenueView());

        view.sampleExecution();
    }
}