package supermarket;

import java.util.*;

public class Menu 
{

    private InventorySystem productQueue; // Using Queue
    private LinkedList<Admin> adminList; // Using Linked List
    private ArrayList<Customer> customers;
    private User currentUser;
    private CartSystem userCart; // BinaryTree Stack
    private BinaryTree salesRecord; // BinaryTree List
    
    //initializing all classes that we are using  
    public Menu() 
    {
        this.productQueue = new InventorySystem();
        this.adminList = new LinkedList<>();
        this.customers = new ArrayList<>();
        this.userCart = new CartSystem();
        this.salesRecord = new BinaryTree();
    }
    // initialize c'tor for admin and customer 
    // and adding products to inventory (you can use it).
    public void initSystem() 
    {
        Admin admin1 = new Admin("123456789", "admin", "123");
        adminList.add(admin1);

        Customer customer1 = new Customer("789456123", "customer", new Date(12, 7, 1990));
        Customer customer2 = new Customer("789456123", "cus", new Date(12, 7, 2007));
        customers.add(customer1);
        customers.add(customer2);

        Products product1 = new Products("Milk", new Date(12, 7, 2024), 20, 5.7, "Dairy");
        Products product2 = new Products("Bread", new Date(15, 7, 2024), 30, 6.3, "Pastry");
        Products product3 = new Products("Cheese", new Date(20, 6, 2024), 80, 24.32, "Dairy");
        Products product4 = new Products("Butter", new Date(25, 8, 2024), 100, 7.66, "Dairy");
        NonPerishableProducts product5 = new NonPerishableProducts("Marlboro", 100, 7.66, true);

        productQueue.addProduct(product1);
        productQueue.addProduct(product2);
        productQueue.addProduct(product3);
        productQueue.addProduct(product4);
        productQueue.addProduct(product5);
    }
    
    // display for each menu 
    public void displayInitialMenu() {
        System.out.println("Welcome to the Supermarket Management System");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    public void displaySupplierMenu() {
        System.out.println("Supplier Menu");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Display Products");
        System.out.println("4. Back to Main Menu");
    }

    public void displayAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("1. Add Product to Inventory");
        System.out.println("2. Remove Product from Inventory");
        System.out.println("3. Inventory Check");
        System.out.println("4. View Sales Record");
        System.out.println("5. Back to Main Menu");
    }

    public void displayCustomerMenu() {
        System.out.println("Customer Menu");
        System.out.println("1. Add Item to Cart");
        System.out.println("2. Remove Item from Cart");
        System.out.println("3. Purchase Cart");
        System.out.println("4. View Cart");
        System.out.println("5. View Inventory");
        System.out.println("6. Back to Main Menu");
    }

    // function that adding product to inventory (using queue here)
    public void addProductToInventory(String name, Date exp, int quantity, double customerPrice, String type) {
        boolean check = false;
        Products temp = null;
        int size = productQueue.getSize();
        for (int i = 0; i < size; i++) {
            temp = productQueue.removeProduct();
            if (temp.getName().equalsIgnoreCase(name) && temp.getExp().compareTo(exp)) {
                check = true;
                temp.setQuantity(temp.getQuantity() + quantity);
            }
            productQueue.addProduct(temp);
        }
        if (!check) {
            Products product = new Products(name, exp, quantity, customerPrice, type);
            productQueue.addProduct(product);
        }
    }
  //-------------------- Main Menu --------------------//
    // main menu for login/register
    public void mainMenu() 
    {
        initSystem(); // you can use this initialize if you want to 
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayInitialMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
        scanner.close();
    }
  //-------------------- Log in ----------------------//
    //  login menu
    public void login() 
    {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.println("Login Screen");
        System.out.println("Customer or Admin?: ");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (input == 2) 
        {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            for (Admin admin : adminList)
            {
                if (username.equals(admin.getName()) && password.equals(admin.getPassword())) 
                {
                    System.out.println("Logged in as admin!");
                    currentUser = admin;
                    adminMenu();
                    return;
                }
            }
        }
        else if (input == 1) 
        {
            for (Customer customer : customers) 
            {
                if (username.equals(customer.getName())) 
                {
                    System.out.println("Logged in as customer!");
                    currentUser = customer;
                    customerMenu();
                    return;
                }
            }
        }
        System.out.println("Invalid credentials. Please try again.");
        login();
    }
  //-------------------- Registration ----------------//
    // register menu (using here try and catch for id) 
    public void register()
    {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        try 
        {
            System.out.println("Registration Screen");
            System.out.println("1. Register as Admin");
            System.out.println("2. Register as Customer");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
                   
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter ID: ");
            String ID = scanner.nextLine();

            if (ID.length() != 9) 
                throw new InvalidIDLengthException("Invalid ID length. ID must be 9 characters long.");

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    Admin newAdmin = new Admin(ID, username, password);
                    adminList.add(newAdmin);
                    System.out.println("Admin registered: " + username);
                    break;
                case 2:
                    System.out.print("Enter date of birth (DD-MM-YYYY): ");
                    String dateStr = scanner.nextLine();
                    Date dob = Date.valueOf(dateStr);
                    while (!dob.isValidDate())
                    {
                    	System.out.println("Invalid Date!");
                    	System.out.print("Enter date of birth (DD-MM-YYYY): ");
                    	dateStr = scanner.nextLine();
                        dob = Date.valueOf(dateStr);
                    }
                    Customer newCustomer = new Customer(ID, username, dob);
                    customers.add(newCustomer);
                    System.out.println("Customer registered: " + username);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    register();
                    break;
            }
        } 
        catch (InvalidIDLengthException e)
        {
            System.out.println(e.getMessage());
            register();
        }
    }
    //-------------------- Admin --------------------//
    // admin menu (you can add/remove products to/from inventory here  
    // and you can see your best sales 
    private void adminMenu() 
    {
        Scanner scanner = new Scanner(System.in);
        int adminChoice;
        do 
        {
            displayAdminMenu();
            System.out.print("Enter your choice: ");
            adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) 
            {
                case 1:
                    addProductToInventoryFromInput(scanner);
                    break;
                case 2:
                    System.out.print("Enter product name to remove from inventory: ");
                    String nameToRemove = scanner.nextLine();
                    productQueue = removeProductFromInventory(nameToRemove);
                    break;
                case 3:
                    productQueue.displayInventory();
                    break;
                case 4:
                    viewSalesRecord();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (adminChoice != 5);
    }
    
    //---- Functions That Used For Admin's Cases:----
    
    //Function for case 1. (adding product to The inventory by admin).
    private void addProductToInventoryFromInput(Scanner scanner) 
    {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter customer price: ");
        double customerPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter product type: ");
        String type = scanner.nextLine();
        System.out.print("Enter expiration date (DD-MM-YYYY): (Enter 0 for non-perishable) ");
        String expStr = scanner.nextLine();
        
        if (expStr.equals("0")) 
        {
            boolean plus18 = false;
            while (true) 
            {
                System.out.print("Is the product age-limited for 18+? (y/n) ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("y")) 
                {
                    plus18 = true;
                    break;
                } 
                else if (input.equalsIgnoreCase("n")) 
                    break;
                else 
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
            addNonPerishableProductToInventory(name, quantity, customerPrice, plus18);
        } 
        else 
        {
            Date exp = Date.valueOf(expStr);
            while (!exp.isValidDate())
            {
            	System.out.println("Invalid Date!");
            	System.out.print("Enter expiration date (DD-MM-YYYY): ");
            	expStr = scanner.nextLine();
            	exp = Date.valueOf(expStr);
            }
            addProductToInventory(name, exp, quantity, customerPrice, type);
        }
    }
    // Assistant function for case 1. (using for adding non perishable product's 
    // that does not have expire date).
    private void addNonPerishableProductToInventory(String name, int quantity, double customerPrice, boolean plus18) 
    {
        boolean check = false;
        Products temp = null;
        int size = productQueue.getSize();
        for (int i = 0; i < size; i++) 
        {
            temp = productQueue.removeProduct();
            if (temp.getName().equalsIgnoreCase(name)) 
            {
                check = true;
                temp.setQuantity(temp.getQuantity() + quantity);
            }
            productQueue.addProduct(temp);
        }
        if (!check)
        {
            NonPerishableProducts product = new NonPerishableProducts(name, quantity, customerPrice, plus18);
            productQueue.addProduct(product);
        }
    }
    
    //Function for case 2. (removing product from The inventory by admin).
    public InventorySystem removeProductFromInventory(String name) 
    {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        InventorySystem newQueue = new InventorySystem();
        Products temp = null;
        boolean flag = false;
        while (productQueue != null) {
            temp = productQueue.removeProduct();
            if (temp.getName().equalsIgnoreCase(name) && !flag) {
                flag = true;
                System.out.println("There is: " + temp.getQuantity() + " in the inventory, how much do you want to remove?");
                int quantity = scanner.nextInt();
                if (temp.getQuantity() > quantity) {
                    temp.setQuantity(temp.getQuantity() - quantity);
                    newQueue.addProduct(temp);
                }
            } else {
                newQueue.addProduct(temp);
            }
        }
        return newQueue;
    }
    //Function for case 4. (displaying from best to least sales).
    private void viewSalesRecord() 
    {
    	salesRecord.preorderTraversal();
    }
    
  //-------------------- Customer --------------------//
    // customer menu (you can add/remove products to/from your cart here)  
    private void customerMenu() 
    {
        Scanner scanner = new Scanner(System.in);
        int customerChoice;
        do 
        {
            displayCustomerMenu();
            System.out.print("Enter your choice: ");
            customerChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (customerChoice) 
            {
                case 1:
                    addItemToCartFromInventory(scanner);
                    break;
                case 2:
                    userCart.displayCart();
                    System.out.print("Enter product name to remove from cart: ");
                    String nameToRemove = scanner.nextLine();
                    userCart = removeFromCart(nameToRemove);
                    break;
                case 3:
                    double total = purchaseCart();
                    if (total > 0) 
                    {
                        System.out.println("Total price of purchased items: $" + total);
                    }
                    break;
                case 4:
                    userCart.displayCart();
                    break;
                case 5:
                    productQueue.displayInventory();
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (customerChoice != 6);
    }
    
    //---- Functions That Used For Customer's Cases:----
    
    //Function for case 1. (adding product to The cart by customer
    //  and at the same time removing from inventory ).
    private void addItemToCartFromInventory(Scanner scanner) 
    {
        System.out.print("Inventory: ");
        productQueue.displayInventory();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        
        Products newProduct = getProductFromInventory(name);
        if (!newProduct.getName().equals("temporary")) 
            userCart.addProductToCart(newProduct);
        
    }
    
    // Assistant function for case 1. getting product's name and checking all the conditions
    //(such as age , amount in the inventory).
    private Products getProductFromInventory(String name) 
    {
        boolean age = howOldCustomer();
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        Products temp = null, temp2 = new Products();
        boolean flag = false, exist = false;
        int size = productQueue.getSize();

        for (int i = 0; i < size; i++) 
        {
            temp = productQueue.removeProduct();
            if (temp.getName().equalsIgnoreCase(name) && !flag)
            {
                if (temp instanceof NonPerishableProducts)
                {
                    NonPerishableProducts npProduct = (NonPerishableProducts) temp;
                    if (!age && npProduct.isPlus18()) 
                    {
                        exist = true;
                        System.out.println("Product is 18+ restricted and cannot be added to the cart.");
                        productQueue.addProduct(temp); // Skip and put back in queue
                        continue;
                    }
                }
                exist = true;
                flag = true;
                System.out.println("There is: " + temp.getQuantity() + " , Expiry date: " + temp.getExp() + " in the inventory.");
                System.out.println("Do you want to add from this product? y/n ");
                while (true) {
                    try {
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            System.out.println("How much would you like to add?");
                            int quantity = scanner.nextInt();
                            while(quantity <= 0)
                            {
                            	System.out.print("Please enter only positive number: ");
                            	quantity = scanner.nextInt();
                            }
                            scanner.nextLine(); // Consume the newline
                            
                            if (quantity >= temp.getQuantity()) {
                                temp2 = temp;
                            } else {
                                temp2 = new Products(temp.getName(), temp.getExp(), quantity, temp.getCustomerPrice(), temp.getType());
                                temp.setQuantity(temp.getQuantity() - quantity);
                                productQueue.addProduct(temp);
                            }
                            break;
                        } else if (choice.equalsIgnoreCase("n")) {
                            flag = false;
                            productQueue.addProduct(temp);
                            break;
                        } else {
                            throw new InvalidInputException("Invalid input. Please enter 'y' or 'n'.");
                        }
                    } 
                    catch (InvalidInputException e) 
                    {
                        System.out.println(e.getMessage());
                    }
                }
            } 
            else 
                productQueue.addProduct(temp);
        }
        if (!exist) 
            System.out.println("Product does not exist.");
        return temp2;
    }
    // Assistant function for case 1.
    // checking if the age is 18+ 
    // assuming that the date today is : 01/ 01/ 2006
    private boolean howOldCustomer() 
    {
        if (currentUser instanceof Customer)
        {
            Customer customer = (Customer) currentUser;
            Date other = new Date(01, 01, 2006);
            if (customer.getAge().isLaterThan(other))
                return false;
        }
        return true;
    }
    
    //Function for case 2. (removing product from The cart by customer
    //  and at the same time adding to inventory ).
    private CartSystem removeFromCart(String nameToRemove)
    {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        Products temp = null;
        CartSystem newCart = new CartSystem();
        int size = userCart.getSize();
        for (int i = 0; i < size; i++) 
        {
            temp = userCart.removeProduct();
            if (temp.getName().equalsIgnoreCase(nameToRemove)) 
            {
                System.out.println("There is: " + temp.getQuantity() + " in the cart.");
                System.out.print("Enter quantity to remove: ");
                int quantity = scanner.nextInt();
                if (quantity < temp.getQuantity()) 
                {
                    temp.setQuantity(temp.getQuantity() - quantity);
                    newCart.addProductToCart(temp);
                    if (temp instanceof NonPerishableProducts) 
                    {
                        NonPerishableProducts npProduct = (NonPerishableProducts) temp;
                        addNonPerishableProductToInventory(npProduct.getName(), quantity, npProduct.getCustomerPrice(), npProduct.isPlus18());
                    } 
                    else addProductToInventory(temp.getName(), temp.getExp(), quantity, temp.getCustomerPrice(), temp.getType());
                } 
                else 
                {
                    if (temp instanceof NonPerishableProducts) 
                    {
                        NonPerishableProducts npProduct = (NonPerishableProducts) temp;
                        addNonPerishableProductToInventory(npProduct.getName(), npProduct.getQuantity(), npProduct.getCustomerPrice(), npProduct.isPlus18());
                    }
                    else addProductToInventory(temp.getName(), temp.getExp(), temp.getQuantity(), temp.getCustomerPrice(), temp.getType());
                    
                }
            }
            else newCart.addProductToCart(temp);
        }
        return newCart;
    }
    
    //Function for case 3. (total sum of purchase Cart).
    private double purchaseCart() 
    {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        double total = 0;
        Products temp = null;
        System.out.println("Do you want to view your cart before purchasing? y/n");
        while (true) 
        {
            try{
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y"))
                {
                    userCart.displayCart();
                    break;
                } 
                else if (choice.equalsIgnoreCase("n")) 
                    break;
                
                else throw new InvalidInputException("Invalid input. Please enter 'y' or 'n'.");
            	} 
            catch (InvalidInputException e)
            { 
                System.out.println(e.getMessage());
            }
        }
        
        System.out.println("Do you want to purchase it? y/n");
        while (true) {
            try {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y"))
                {
                    int size = userCart.getSize();
                    for (int i = 0; i < size; i++) 
                    {
                        temp = userCart.removeProduct();
                        addToSalesTree(temp.getName(), temp.getQuantity());
                        
                        if (temp != null) 
                            total += temp.getQuantity() * temp.getCustomerPrice();
                    }
                    break;
                } 
                else if (choice.equalsIgnoreCase("n")) 
                    break;
                 else throw new InvalidInputException("Invalid input. Please enter 'y' or 'n'.");
            } catch (InvalidInputException e) 
            {
                System.out.println(e.getMessage());
            }
        }
        return total;
    }
    
    // Assistant function for case 3.
    // If the customer purchased his cart then:
    // immediately adding The quantity of each product to our binary tree (best sales tree).
    private void addToSalesTree(String name, int quantity) 
    {
        salesRecord.add(name, quantity);
    }
    
    //--------- Exception classes -------------------
    // Exception for wrong ID number in registration.
    @SuppressWarnings("serial")
    // Function used for catching the invalid values of the customer
    class InvalidIDLengthException extends Exception
    {
    	public InvalidIDLengthException(String message) 
    	{
        super(message);
    	}
    }
    // Exception for wrong Input number in The menu.
    @SuppressWarnings("serial")
    class InvalidInputException extends Exception 
    {
    	public InvalidInputException(String message)
    		{
    			super(message);
    		}
    }
}