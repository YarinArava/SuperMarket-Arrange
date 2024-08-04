package supermarket;
import java.util.*;
import java.util.Stack;

@SuppressWarnings("unused")
public class CartSystem
{
    private Stack<Products> cart;
    private  int size;
    
    // Method to initialize new stack.
    public CartSystem()
    {
    	size = 0;
        this.cart = new Stack<>();
    }

    // Method to add a product to the cart (push).
    public void addProductToCart(Products product)
    {
    	size++;
        cart.push(product);
        System.out.println("Added to cart: " + product);
    }

    // Method to remove a product from the cart (pop).
    public Products removeProduct()
    {
    	size--;
    	if (cart.isEmpty()) {
    		System.out.println("Cart is empty");
    		return null;
    	}
    	Products removedProduct = cart.pop();
    	//System.out.println("Removed from cart: " + removedProduct);
    	return removedProduct;
    }
    
    // Method to getting size of stack.
    public int getSize() {
    	return size;
    }

    // Method to display the cart
    public void displayCart()
    {
    	double total = 0, current = 0;
    	if (cart.isEmpty()) {
    		System.out.println("Cart is empty");
    	} else {
    		System.out.println("Current cart:");
    		for (Products product : cart) {
    			current = product.getCustomerPrice()*product.getQuantity();
    			total+=current;   			
    			System.out.print(product);
    			System.out.println(" - " + current + "$");
    		}
    		System.out.println("The total price is: " + total + "$");
    	}
    }

}
