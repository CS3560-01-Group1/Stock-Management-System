import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private String phoneNumber; //optional
	
	//Constructor
	public User(int idNum) {
		id = idNum;
		//fill in all the attributes of User using information of row where idNum = user.userID from database
	}
	
	//Alternative constructor where user is not defined yet
	public User() {
		
	}
	
	
	public static Boolean loginConfirmation(String inputUsername, String inputPassword) 
	{
		//Make sure there is actually input for both username and password
		if (inputUsername.equals(""))
			return false;
		if (inputPassword.equals(""))
			return false;

		//Find row matching "inputUsername" in database
		boolean usernameFound = false;
		try
		{
			Connection connection = Main.getConnection();
			// create the java statement
					
			// execute the query, and get a java resultset
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM user");
			
			//Checks if result is empty
			if(!rs.isBeforeFirst())
				return false;
			
			// iterate through the java resultset
			while (rs.next())
			{
				if (inputUsername.equals(rs.getString("username")));
					usernameFound = true;
			}
			
			//Check if inputed password = stored password
			if (usernameFound) {
				rs = connection.createStatement().executeQuery("SELECT * FROM user where username = '" 
																	+ inputUsername + "'");
				if(!rs.isBeforeFirst())
					return false;
				rs.next();
				if (rs.getString("password").equals(inputPassword)) {
					return true;
				}
				else
					return false;
			}
			else
				return false;
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}

		return false;
	}
	
	public void logoff() 
	{
		//erase all attributes from this object (NOT FROM THE DATABASE)
	}
	
	//method to register new users
	public static void createUserAccount(String usernameInput, String passwordInput, String ssnInput,
			String addressInput, String fNameInput, String lNameInput, String emailInput, String phoneNumInput)
	{
		//id = 0; when creating a new row entry, use userID = 0 and it will automatically pick a new unique ID
				//remember to change the ID in this object to the new non-zero ID after insert query
		try
		{
			Connection connection = Main.getConnection();
			// create the java statement
			
			//Generate appropriate query
			String query = "INSERT INTO `user` (`userID`, `username`, `password`, `balance`, `ssn`, `address`, `fName`, `lName`";
			if (!emailInput.equals(""))
				query += ", `email`";
			if (!phoneNumInput.equals("(___)-___-____"))
				query += ", `phone#`";
			query += ") values (0, '" + usernameInput + "', '" + passwordInput + "', 0.00, '" + ssnInput + "', '" 
					+ addressInput + "', '" + fNameInput + "', '" + lNameInput;
			if (!emailInput.equals(""))
				query += "', '" + emailInput;
			if (!phoneNumInput.equals("(___)-___-____"))
				query += "', '" + phoneNumInput;
			query += "')";

			// execute the query
			connection.createStatement().executeUpdate(query);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		
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

	//Allows user to update login information
	public static void updateLoginCredentials(String oldUsername, String newUsername, String newPassword) {

		try{
		Connection connection = Main.getConnection();
		// create the java statement

		//Updates the login credentials information in database
		connection.createStatement().executeUpdate("UPDATE `user` SET `password` = '" 
													+ newPassword + "' WHERE `username` = '" + oldUsername + "'");	
		connection.createStatement().executeUpdate("UPDATE `user` SET `username` = '" 
													+ newUsername + "' WHERE `username` = '" + oldUsername + "'");
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

	//Allows user to update personal information
	public static void updatePersonalInformation(String userName, String newFName, String newLName, String newEmail, 
													String newPhoneNum, String newSSN) {

		try{
		Connection connection = Main.getConnection();
		// create the java statement

		//Updates the personal information attribute in database
		connection.createStatement().executeUpdate("UPDATE `user` SET `fName` = '" 
												+ newFName + "' WHERE `username` = '" + userName + "'");	
		connection.createStatement().executeUpdate("UPDATE `user` SET `lName` = '" 
												+ newLName + "' WHERE `username` = '" + userName + "'");
		connection.createStatement().executeUpdate("UPDATE `user` SET `email` = '" 
												+ newEmail + "' WHERE `username` = '" + userName + "'");
		connection.createStatement().executeUpdate("UPDATE `user` SET `phone#` = '" 
												+ newPhoneNum + "' WHERE `username` = '" + userName + "'");
		connection.createStatement().executeUpdate("UPDATE `user` SET `ssn` = '" 
												+ newSSN + "' WHERE `username` = '" + userName + "'");
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

	//Allows user to update address
	public static void updateaddress(String userName, String newAddress) {

		try{
		Connection connection = Main.getConnection();
		// create the java statement
		
		//Updates the address in database
		connection.createStatement().executeUpdate("UPDATE `user` SET `address` = '" 
														+ newAddress + "' WHERE `username` = '" 
														+ userName + "'");	

		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void updateBalance(int changeInBalance)
	{
		//query change in user's balance
		//happens after a transaction (stock or monetary) is created
		//if successful, update this object's balance attribute
		balance = balance + changeInBalance;
		try
		{
			//establishing connection to database
			Connection connection = Main.getConnection();
			//A SQL query that updates the balance inside of the user table
			String str = "UPDATE stockdb.user set " + balance + " WHERE userID = " + id;
			PreparedStatement query = connection.prepareStatement(str);
			query.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			System.out.println("Query Complete: Updated Balance");
		}
	}

	//Alternate version of update balance
	public static float updateBalance1(String username, float changeInBalance) {
		try
		{
			float balance;

			//Establishing connection to database
			Connection connection = Main.getConnection();

			//Execute query
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `user` WHERE `username` = '" 
																	+ username + "'");	
			rs.next();

			//Get balance
			balance = rs.getFloat("balance");

			//Add change to balance
			balance += changeInBalance;

			//Update balance in database
			connection.createStatement().executeUpdate("UPDATE `user` SET `balance` = " 
														+ balance + " WHERE `username` = '" + username + "'");

			return balance;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return 0;
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

	//Get account information
	public static ResultSet getAccountInfo(String usernameInput) {
		try
		{
			Connection connection = Main.getConnection();
			// create the java statement

			// execute the query
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM user WHERE `username` = '" 
																	+ usernameInput + "'");
			return rs;
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		return null;
	}

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

	public String getPhoneNumber() {
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
