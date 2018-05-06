package model;

import integration.ItemDTO;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * This class represent the order of items that the customer wants to buy.
 */
public class Sale {
    private LocalDateTime saleTime;
    private double runningTotal = 0;
    private double tax = 0.12;
    private HashMap<ItemDTO, Quantity> items = new HashMap<ItemDTO, Quantity>();

    /**
     * This is the constructor of the object. When the object is created it also remembers the time the sale began.
     */
    public Sale(){
        saleTime = LocalDateTime.now();
    }

    /**
     * This allows other classes to get the time the sale began.
     * @return The method returns the time the sale began.
     */
    public LocalDateTime getSaleTime(){
        return this.saleTime;
    }

    /**
     * This method allows other classes to get the hashmap called 'items'.
     * @return It returns the hashmap called 'items'.
     */
    public HashMap<ItemDTO, Quantity> getItems(){
        return items;
    }

    /**
     * This method adds a scanned item to the hashmap with the following quantity.
     * It also increases the quantity for items already existing in the hashmap.
     * @param foundItem This is the scanned item of the type ItemDTO which includes information about the item.
     * @param quantity This is the quantity of the item.
     * @return The return value is of object SaleDTO,
     *      which includes information about the current sale and the last scanned item
     */
    public SaleDTO addItem(ItemDTO foundItem, Quantity quantity){
        if(items.containsKey(foundItem)){
            items.put(foundItem, new Quantity(items.get(foundItem).getQuantity() +
                                              quantity.getQuantity()));
            this.runningTotal += (foundItem.getPrice() * quantity.getQuantity());
        }
        else {
            items.put(foundItem, quantity);
            this.runningTotal += (foundItem.getPrice() * quantity.getQuantity());
        }
        return new SaleDTO(this, foundItem);
    }

    /**
     * This allows other classes to get access to the total price at the moment.
     * @return It returns the total price at the moment.
     */
    public double getRunningTotal(){
        return runningTotal;
    }

    /**
     * This method calculates how much of the tax by multiplying the total price at the moment with the 12% tax.
     * @return It returns how much of the total price the tax is.
     */
    public double calculateTax(){
        return runningTotal * tax;
    }

    /**
     * This is a string that represents the way that the object will be printed if called up on.
     * @return It returns the string that is built in the method.
     */
    public String toString(){
        StringBuilder runningTotalInfo = new StringBuilder();
        runningTotalInfo.append("Total:" + Double.toString(runningTotal) +
                ", Tax:" + Double.toString(calculateTax()));
        return runningTotalInfo.toString();
    }
}