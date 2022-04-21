import java.sql.ResultSet;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String ssn;
	private String address;
	private String firstName;
	private String lastName;
	private double balance; //optional (starts as 0 upon account creation)
	private String email; //optional
	private int phoneNumber; //optional
	
	//Constructor
	public User(int idNum) {
		id = idNum;
		//fill in all the attributes of User using information of row where idNum = user.userID from database
	}
	
	//Alternative constructor where user is not defined yet
	public User() {
		
	}
	
	
	public Boolean loginConfirmation(String inputUsername, String inputPassword) 
	{
		//Find row matching "inputUsername" in database
		//Check if inputed password = stored password
		//if true
			//populate all the attributes of this objects with the matching user's attributes stored in database
			//return confirmation of login 
		return true;
	}
	
	public void logoff() 
	{
		//erase all attributes from this object (NOT FROM THE DATABASE)
	}
	
	//method to register new users
	public void createUserAccount(String usernameInput, String passwordInput, String ssnInput,
			String addressInput, String fNameInput, String lNameInput, String emailInput, int phoneNumInput)
	{
		id = 0; //when creating a new row entry, use userID = 0 and it will automatically pick a new unique ID
				//remember to change the ID in this object to the new non-zero ID after insert query
		username = usernameInput;
		password = passwordInput;
		ssn = ssnInput;
		address = addressInput;
		firstName = fNameInput;
		lastName = lNameInput;
		balance = 0;
		email = emailInput; //optional 
		phoneNumber = phoneNumInput; //optional
	}

	//Brings up a list of past transactions under this User
	public ResultSet viewAllTransactions() {
		//query join with transaction table and monetarytransaction and order tables from database
		//grab row elements matching the same ID of THIS user only
		//return the set of results (for each row)
		return null;
	}
	
	

	//Returns the total amount of shares of each stock owned by this user
	public ResultSet viewPortfolio()
	{
		//Cleighton will do this one
		//(do not include expired orders)
		return null;
	}
	
	//Makes a MonetaryTransaction
	public void placeTransaction(double amount, String date, String targetBankAcct, boolean deposit) {
		//create new monetary transaction object 
		//use constructor method to insert new row into "stockdb.monetarytransaction" table 
		//update this object's balance if transaction was inserted successfully
	}
	
	//User places an order for a specified stock and quantity.
	//type (1/0) = (buy/sell)
	public void placeOrder(String stockSymbol, int quantity, int type) {
		//create new order object
		//user constructor method to insert new row into "stockdb.order" table
	}

	//Allows user to update some aspects of their account information
	public void updateAccountInfo(String emailAddress, String phoneNum, String physicalAddress, String creditInfo, 
										String name) {
		//select row in "stock.user" table matching this object's id = user.userID
		//update each column's information accordingly 
		//(make sure non-null attributes aren't left empty or null)
			//if any input was left empty for non-null fields just don't make any change then.
		//if update is successful, update the user attributes in this object

	}
	
	public void updateBalance(int changeInBalance)
	{
		//query change in user's balance
		//happens after a transaction (stock or monetary) is created
		//if successful, update this object's balance attribute
	}
	
	//Deletes user account and all information regarding this user
	public void deleteAccount()
	{
		//Initiate query to "delete" all data regarding this user
		//Select query across all tables (transaction -> monetarytransaction, transaction -> order, user)
		//delete all matching content
		//finally, remove the attributes of this object
		logoff();
		
	}

	//Getter functions
	public int getID() {
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getSSN() {
		return ssn;
	}

	public String getAddress() {
		return address;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getlastName() {
		return lastName;
	}

	public double getBalance() {
		return balance;
	}
	
	

}
