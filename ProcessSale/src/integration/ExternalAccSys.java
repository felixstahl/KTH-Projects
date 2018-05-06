package integration;

import model.Sale;

/**
 * This class represents an external accounting system which needs every sales information.
 * Since this is not a real external system it does not perform anything other than recieving information.
 */
public class ExternalAccSys {
    private Sale completedSale;

    /**
     * This is the constructor of the object.
     */
    public ExternalAccSys(){
    }

    /**
     * This method just the imagination that an external accounting system recieves the sale information,
     * and does something important with it.
     * @param currentSale This is the current sale object with the sale information that is sent to the external system.
     */
    public void logSale(Sale currentSale){
        this.completedSale = currentSale;
    }
}