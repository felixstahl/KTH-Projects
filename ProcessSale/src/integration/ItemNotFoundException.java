package integration;

/**
 * This exception is thrown when an item is not found in the inventory.
 */
public class ItemNotFoundException extends Exception {

    /**
     * This is the constructor of the exception
     * @param errorMessage This is the message printed when the exception occurs.
     */
    public ItemNotFoundException (String errorMessage){
        super(errorMessage);
    }
}
