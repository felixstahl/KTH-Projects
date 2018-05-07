package model;

import integration.ItemDTO;

/**
 * This class represents information about the currentSale and the recently scanned item.
 */
public class SaleDTO {
    private Sale sale;
    private ItemDTO lastScannedItem;

    /**
     * This is the constructor of the object called <code>SaleDTO</code>
     * @param currentSale This is the object of the current sale, which contains information about the sale
     * @param lastScannedItem This is the object of the most recently scanned item,
     *                        which contains information about the item.
     */
    public SaleDTO(Sale currentSale, ItemDTO lastScannedItem){
        this.sale = currentSale;
        this.lastScannedItem = lastScannedItem;
    }

    /**
     * @return This method returns the total price at the moment.
     */
    public double getTotal(){
        return sale.getRunningTotal();
    }

    /**
     * This method calculates how much of the tax by calling the calculate tax method.
     * @return It returns how much of the total price the tax is.
     */
    public double getTax(){
        return sale.calculateTax();
    }

    /**
     * @return This method returns the name of the item.
     */
    public String getItemName(){
        return lastScannedItem.getName();
    }

    /**
     * @return This method returns the price of the item.
     */
    public double getItemPrice() {
        return lastScannedItem.getPrice();
    }

    /**
     *
     * @return This method returns the barcode of the item.
     */
    public int getItemBarcode(){
        return lastScannedItem.getBarcode();
    }
}
