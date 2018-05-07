package model;

import integration.ItemDTO;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the receipt as an object so that all the information on the receipt is 'packaged' as one unit.
 */
public class Receipt {
    private Sale finishedSale;
    private LocalDateTime saleTime;

    /**
     * This is the constructor of the object Receipt.
     * @param currentSale This is the current sale from the object 'Sale'
     */
    public Receipt(Sale currentSale){
        this.finishedSale = currentSale;
        this.saleTime = currentSale.getSaleTime();
    }

    /**
     *This method calculates the amount of change the cashier is supposed to hand back to the customer.
     * @param amountPayed This is the amount that the customer payed.
     * @return This return value is the amount of change that the customer gets back.
     */
    public double calculateChange(double amountPayed){
        return amountPayed - finishedSale.getRunningTotal();
    }

    /**
     * This is a string that represents the way that the object will be printed if called up on.
     * @return It returns the string that is built in the method.
     */
    public String toString(){
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("\n" + "        Felix Frukt");
        receiptBuilder.append("\n" + saleTime.toString());
        receiptBuilder.append("\n" + finishedSale.toString() + "\n");
        Set<Map.Entry<ItemDTO, Quantity>> itemsRegistered = finishedSale.getItems().entrySet();
        for(Map.Entry<ItemDTO, Quantity> item : itemsRegistered){
            receiptBuilder.append(System.lineSeparator() + item.getKey().getName() +
                    " " + item.getKey().getBarcode() + " " + "(" +item.getValue().getQuantity() + ")"
                    + " styck:" + item.getKey().getPrice() +
                    " : " + Double.toString(item.getKey().getPrice()*item.getValue().getQuantity()));
        }
        return receiptBuilder.toString();
    }
}