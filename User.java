
public class User {
	
	private String id;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	private String ssn;
	private String address;
	private String creditCardInfo;
	private String fullName;
	private double balance;
	
	//Constructor
	public User(String idNum, String userName, String passWord, String emailAddress, String ssnum, String physicalAddress, 
					String creditInfo, String name, double money) {
		id = idNum;
		username = userName;
		password = passWord;
		email = emailAddress;
		ssn = ssnum;
		address = physicalAddress;
		creditCardInfo = creditInfo;
		fullName = name;
		balance = money; //balance should start out as 0 until a deposit is made to the account
	}
	
	//Brings up a list of past transactions (both monetary AND stock at the moment?)
	public void viewTransactions() {
		
	}
	

	//Displays the total amount of shares (per unique stock) owned by the user
	public void viewPortfolio()
	{
		/* 
		 * Find the latest StockTransaction for each unique stock the user has traded 
		 * then return/display a list of the "share totals" stored as an attribute for the given stock
		 */
	}
	
	//Makes a MonetaryTransaction
	public void placeTransaction(double amount, String date, String targetBankAcct, boolean deposit) {
		
	}
	
	//User places an order for a specified stock and quantity. Type is used to indicate buying vs selling.
	// type: (buy/sell) = (0/1)
	public void placeOrder(String stockSymbol, int quantity, int type) {
		
	}

	//Allows user to update some aspects of their account information
	public void updateAccountInfo(String emailAddress, String phoneNum, String physicalAddress, String creditInfo, 
										String name) {

	}

	//Getter functions
	public String getID() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSSN() {
		return ssn;
	}

	public String getAddress() {
		return address;
	}

	public String getCreditCardInfo() {
		return creditCardInfo;
	}

	public String getFullName() {
		return fullName;
	}

	public double getBalance() {
		return balance;
	}

}
