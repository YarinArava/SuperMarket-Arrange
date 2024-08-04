package supermarket;

public class NonPerishableProducts extends Products {
	private boolean plus18;
	//Default C'tor for non perishable products.
	public NonPerishableProducts() {
		this.name = "temporary";
		this.quantity = 0;
		this.customerPrice = 0;
		this.type = "Non_Perishable";
		this.plus18 = false;
	}

	//C'tor for non perishable products.
	public NonPerishableProducts(String name, int quantity, double customerPrice, boolean plus18) {
		this.name = name;
		this.quantity = quantity;
		this.customerPrice = customerPrice;
		this.type = "Non_Perishable";
		this.plus18 = plus18;
	}

	
	public boolean isPlus18() {
		return plus18;
	}
}
