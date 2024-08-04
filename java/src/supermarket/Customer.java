package supermarket;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User {

    private Map<Products, Integer> cart; // Map to store products and their quantities
    private Date date;
    
    // Default c'tor Customer
    public Customer() {
        this.ID = "123456789";
        this.name = "Customer";
        this.Utype = 0;
        this.date = new Date(1, 1, 1991);
        this.cart = new HashMap<>();
    }
    
    // None default c'tor Customer
    public Customer(String ID, String name, Date date) {
        this.ID = ID;
        this.name = name;
        this.Utype = 0;
        this.date = date;
        this.cart = new HashMap<>();
    }

    // Removing product from cart consider the quantity he wants to remove.
    public void removeFromCart(Products item, int quantity) {
        int currentQuantity = cart.getOrDefault(item, 0);
        if (currentQuantity <= quantity) {
            cart.remove(item);
            
        } else {
            cart.put(item, currentQuantity - quantity);
        }
    }
    
    //Getters
    public Map<Products, Integer> getCart() {
        return cart;
    }

    public Date getAge() {
        return date;
    }

    // getting all Customer's information
    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", cart=" + cart +
                '}';
    }
}
