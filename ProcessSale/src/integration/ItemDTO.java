package integration;

import java.util.Objects;

public class ItemDTO {
    private String itemName;
    private double price;
    private int barcode;

    public ItemDTO(String itemName, double price, int barcode){
        this.itemName = itemName;
        this.price = price;
        this.barcode = barcode;
    }

    public ItemDTO(int barcode){
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public String getName(){
        return itemName;
    }

    public int getBarcode(){
        return barcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return barcode == itemDTO.barcode;
    }

    @Override
    public int hashCode() {

        return Objects.hash(barcode);
    }

    public String toString(){
        StringBuilder itemInfo = new StringBuilder();
        itemInfo.append("Item:" + itemName + ", Price:" + Double.toString(price) + ", Barcode:" + barcode);
        return itemInfo.toString();
    }
}