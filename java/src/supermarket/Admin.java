package supermarket;
import supermarket.User;

@SuppressWarnings("unused")
public class Admin extends User 
{

	private String password;
	
	// Default c'tor.
	public Admin()
	{
		this.ID="987654321";
		this.name="admin";
		this.Utype=1;
		this.password="123456";
	}
	
	// Non Default c'tor.
	public Admin(String ID,String name,String password)
	{
		this.Utype=1;
		this.ID=ID;
		this.name=name;
		this.password=password;
	}
	
	// getting Admin's password
	public String getPassword() {

		return this.password;
	}
	
	// getting all Admin's information
	  @Override
	    public String toString() {
	        return "Admin{" +
	                "ID='" + ID + '\'' +
	                ", name='" + name + '\'' +
	                ", password='" + password + '\'' +
	                '}';
	    }
}
