package model;

import integration.ItemDTO;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class Receipt {
    private Sale finishedSale;
    private LocalDateTime saleTime;

    public Receipt(Sale currentSale){
        this.finishedSale = currentSale;
        this.saleTime = currentSale.getSaleTime();
    }

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