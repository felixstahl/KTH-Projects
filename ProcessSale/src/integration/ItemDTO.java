package integration;

/**
 * This class represents the object <code>ItemDTO</code> that contains information about every item. Such as name, price and barcode.
 */
public class ItemDTO {
    private String itemName;
    private double price = 0;
    private int barcode;

    /**
     * This is one of the two constructors of the <code>ItemDTO</code>, it contains information about the product.
     * @param itemName This is the name of the item.
     * @param price This is the price of the item.
     * @param barcode This is the ID of the product.
     */
    public ItemDTO(String itemName, double price, int barcode){
        this.itemName = itemName;
        this.price = price;
        this.barcode = barcode;
    }

    /**
     * This is one of the two constructors of the ItemDTO, it contains information about the product.
     * @param barcode This is the ID of the product.
     */
    public ItemDTO(int barcode){
        this.barcode = barcode;
    }

    /**
     * This allows other classes to get access to the price of the item.
     * @return It returns the price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * This allows other classes to get access to the name of the item.
     * @return It returns the name of the item.
     */
    public String getName(){
        return itemName;
    }

    /**
     * This allows other classes to get access to the barcode of the item.
     * @return This is the ID of the product.
     */
    public int getBarcode(){
        return barcode;
    }

    /**
     * This method compare an ItemDTO with another object.
     * @param o This is another object.
     * @return It returns a true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return barcode == itemDTO.barcode;
    }
}