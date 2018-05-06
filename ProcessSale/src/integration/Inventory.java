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
     * This method searches and creates a new ItemDTO for the found item.
     * @param barcode This is the ID of the product.
     * @return This is the ItemDTO that is returning or null if the item is not found.
     */
    public ItemDTO getItem(int barcode){
        if(itemExists(barcode)){
            return items.get(items.indexOf(new ItemDTO(barcode)));
        }else{
            return null;
        }
    }
}