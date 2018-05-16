package integration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents all the items that the store has in the stock.
 * This store doesn't mind the quantity of each item in the inventory.
 * (It makes sure there is enough to not run out)
 */
public class Inventory {
    private List<ItemDTO> items = new ArrayList<ItemDTO>();

    /**
     * This is the constructor of the inventory, it includes the different items for sale.
     */
    public Inventory(){
        items.add(new ItemDTO("Banana", 10, 111));
        items.add(new ItemDTO("Cucumber", 15, 222));
        items.add(new ItemDTO("Orange", 20, 333));
        items.add(new ItemDTO("Lemon", 25, 444));
        items.add(new ItemDTO("DatabaseFailure", 30, 555));
    }

    /**
     * This method indicates if the item actually exists in the arraylist.
     * @param barcode This is the ID of the product.
     * @return This is the true or false statement if the item exists or not.
     */
    public boolean itemExists(int barcode){
        if(items.contains(new ItemDTO(barcode))){
            return true;
        }
        else return false;
    }

    /**
     * This method searches and creates a new <code>ItemDTO</code> for the found item.
     * @param barcode This is the ID of the product.
     * @return This is the ItemDTO that is returning.
     * @throws ItemNotFoundException If item is not found an exception will be thrown.
     * @throws DatabaseFailureException If database fails an exception will be thrown.
     */
    public ItemDTO getItem(int barcode) throws ItemNotFoundException, DatabaseFailureException {
        if(!itemExists(barcode)){
            throw new ItemNotFoundException("Item with barcode:" + barcode + " does not exist in inventory");
        }
        if(items.get(items.indexOf(new ItemDTO(barcode))) == items.get(items.indexOf(new ItemDTO(555)))){
            throw new DatabaseFailureException("Database Failure");
        }
        return items.get(items.indexOf(new ItemDTO(barcode)));
    }
}