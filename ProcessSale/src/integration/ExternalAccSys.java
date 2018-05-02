package integration;

import model.Sale;

public class ExternalAccSys {
    private Sale completedSale;

    public ExternalAccSys(){
    }

    public void logSale(Sale currentSale){
        this.completedSale = currentSale;
    }
}