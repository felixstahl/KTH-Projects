package model;

/**
 * This class represents the quantity of an item as an object.
 * The quantity is an object so that it's able to be in a hashmap.
 */
public class Quantity {
    private int quantity;

    /**
     * This is the constructor of the object <code>Quantity</code>.
     * @param quantity This is the quantity of an item.
     */
    public Quantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * This allows other classes to get access to the quantity.
     * @return It returns the quantity as an int.
     */
    public int getQuantity(){
        return this.quantity;
    }
}
