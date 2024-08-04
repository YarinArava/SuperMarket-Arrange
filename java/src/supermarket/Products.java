package supermarket;

public class Products {
	protected String name;
	private Date exp;
	protected int quantity;
	protected double customerPrice;
	protected String type;
	
	// C'tor for Products.
    public Products(String name, Date exp, int quantity, double customerPrice, String type)
    {
        this.name = name;
        this.exp = exp;
        this.quantity = quantity;
        this.customerPrice = customerPrice;
        this.type = type;
    }
    //Default C'tor for Products.
    public Products()
    {
        this.name = "temporary";
        this.exp = new Date(0,0,0);
        this.quantity = 0;
        this.customerPrice = 0;
        this.type = "0";
    }
  //Getters
    public String getName() {
        return name;
    }

    public Date getExp() {
        return exp;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getCustomerPrice() 
    {
        return customerPrice;
    }

    public String getType() 
    {
        return type;
    }    
    //Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", exp=" + exp +
                ", quantity=" + quantity +
                ", customerPrice=" + customerPrice +
                ", type='" + type + '\'' +
                '}';
    }
}
