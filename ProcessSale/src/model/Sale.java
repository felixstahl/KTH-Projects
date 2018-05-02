package model;

import integration.ItemDTO;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Sale {
    private LocalDateTime saleTime;
    private double runningTotal = 0;
    private double tax = 0.12;
    private HashMap<ItemDTO, Quantity> items = new HashMap<ItemDTO, Quantity>();

    public Sale(){
        saleTime = LocalDateTime.now();
    }

    public LocalDateTime getSaleTime(){
        return this.saleTime;
    }

    public HashMap<ItemDTO, Quantity> getItems(){
        return items;
    }

    public void addItem(ItemDTO foundItem, Quantity quantity){
        if(items.containsKey(foundItem)){
            items.put(foundItem, new Quantity(items.get(foundItem).getQuantity() + quantity.getQuantity()));
            this.runningTotal += (foundItem.getPrice() * quantity.getQuantity());
        }else {
            items.put(foundItem, quantity);
            this.runningTotal += (foundItem.getPrice() * quantity.getQuantity());
        }
        System.out.println("\n" + foundItem + " Quantity:" + quantity.getQuantity() + ",    Total:" +
                    Double.toString(getRunningTotal()) + ", Tax:" + Double.toString(calculateTax(runningTotal)));
    }

    public double getRunningTotal(){
        return runningTotal;
    }

    public double calculateTax(double runningTotal){
        return runningTotal * tax;
    }

    public double calculateChange(double amountPaid){
        if(amountPaid - runningTotal == 0){
            return 0;
        }
        else if(amountPaid - runningTotal > 0){
            return amountPaid - runningTotal;
        }
        return 65.12;//felmeddelande
    }

    public String toString(){
        StringBuilder runningTotalInfo = new StringBuilder();
        runningTotalInfo.append("Total:" + Double.toString(runningTotal) +
                ", Tax:" + Double.toString(calculateTax(runningTotal)));
        return runningTotalInfo.toString();
    }
}