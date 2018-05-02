package model;

public class Quantity {
    private int quantity;

    public Quantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(Quantity quantity){
        this.quantity = quantity.getQuantity();
    }

    public void increaseQuantity(Quantity quantity){
        this.quantity += quantity.getQuantity();
    }
}
