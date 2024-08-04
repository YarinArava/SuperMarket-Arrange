package supermarket;
import java.util.*;

class InventorySystem 
{
	private Queue<Products> inventory;
	private int size;
	// Method to initialize Inventory System.
	public InventorySystem() {
		size = 0;
		this.inventory = new LinkedList<>();
	}

	// Method to add a product to the inventory
	public void addProduct(Products product) {
		size++;
		inventory.add(product);
	}

	// Method to remove a product from the inventory
	public Products removeProduct() {
		size--;
		Products removedProduct = inventory.poll();
		if (removedProduct != null) {
			//System.out.println("Removed: " + removedProduct);
		} else {
			System.out.println("No product to remove");
		}
		return removedProduct;
	}

	// Method to display the inventory
	public void displayInventory() {
		if (inventory.isEmpty()) {
			System.out.println("Inventory is empty");
		} else {
			System.out.println("Current inventory:");
			for (Products product : inventory) {
				if (product instanceof NonPerishableProducts) {
					NonPerishableProducts npProduct = (NonPerishableProducts) product;
					System.out.println("NonPerishableProducts{" +
							"name='" + npProduct.getName() + '\'' +
							", quantity=" + npProduct.getQuantity() +
							", customerPrice=" + npProduct.getCustomerPrice() +
							", type='" + npProduct.getType() + '\'' +
							", plus18=" + npProduct.isPlus18() +
							'}');
				} else {
					System.out.println(product);
				}
			}
		}
	}

	// getting The size of The Inventory
	public int getSize() {
		return size;
	}
}
