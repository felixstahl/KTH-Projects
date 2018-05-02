package integration;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<ItemDTO> items = new ArrayList<ItemDTO>();

    public Inventory(){
        items.add(new ItemDTO("Banana", 10, 111));
        items.add(new ItemDTO("Cucumber", 15, 222));
        items.add(new ItemDTO("Orange", 20, 333));
        items.add(new ItemDTO("Lemon", 25, 444));
    }

    public boolean itemExists(int barcode){
        if(items.contains(new ItemDTO(barcode))){
            return true;
        }
        else return false;
    }

    public ItemDTO getItem(int barcode){
        if(itemExists(barcode)){
            return items.get(items.indexOf(new ItemDTO(barcode)));
        }else{
            return null;
        }
    }
}