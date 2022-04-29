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
	public User(String usernameIn, String passwordIn) 
	{
		try
		{
			Connection connection = Main.getConnection();
			String query = "SELECT * FROM stockdb.user WHERE username = \"" + usernameIn + "\" AND password = \"" + passwordIn + "\"";
			ResultSet rs = connection.createStatement().executeQuery(query);
	      
			rs.next();
			
			this.username = usernameIn;
			this.password = passwordIn;
			this.id = rs.getInt("userID");
			this.ssn = rs.getString("ssn");
			this.address = rs.getString("address");
			this.firstName = rs.getString("fName");
			this.lastName = rs.getString("lName");
			this.balance = rs.getDouble("balance");
			this.email = rs.getString("email");
			this.phoneNumber = rs.getString("phone#");
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
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
		id = 0;
		username = null;
		password = null;
		ssn = null;
		address = null;
		firstName = null;
		lastName = null;
		balance = 0; 
		email = null;
		phoneNumber = null; 
	}
	
	public void login(String usernameIn, String passwordIn) 
	{
		try
		{
			Connection connection = Main.getConnection();
			String query = "SELECT * FROM stockdb.user WHERE username = \"" + usernameIn + "\" AND password = \"" + passwordIn + "\"";
			ResultSet rs = connection.createStatement().executeQuery(query);
	      
			rs.next();
			
			this.username = usernameIn;
			this.password = passwordIn;
			this.id = rs.getInt("userID");
			this.ssn = rs.getString("ssn");
			this.address = rs.getString("address");
			this.firstName = rs.getString("fName");
			this.lastName = rs.getString("lName");
			this.balance = rs.getDouble("balance");
			this.email = rs.getString("email");
			this.phoneNumber = rs.getString("phone#");
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
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

	// Returns set of all transactions corresponding to the user
	public static ResultSet getTransactions(int userID) {
		try {
			//Establishes connection to database
			Connection connection = Main.getConnection();

			//Query to execute in the database
			String query = "SELECT * FROM stockdb.transaction LEFT JOIN stockdb.order "
			+ "ON `order`.transactionID = transaction.transactionID LEFT JOIN monetarytransaction ON " + 
			"monetarytransaction.transactionID = transaction.transactionID WHERE `userID` = " + userID + " ORDER BY transactionDate ASC";

			//Executes query and stores results
			ResultSet rs = connection.createStatement().executeQuery(query);

			//Returns the result set
			return rs;
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
		
	//Returns the total amount of shares of each stock owned by this user
	//(Does not include expired or open orders)
	public static ResultSet viewPortfolio(int userIDInput)
	{
		try
		{
			String query = "SELECT `stockOwner`, sharesOwned FROM usersShareTotal WHERE `stockOwner` = " + userIDInput
					+ " AND sharesOwned != 0"; 
			
			Connection connection = Main.getConnection();
			ResultSet rs = connection.createStatement().executeQuery(query);
			
			//return set of stocks and total shares
			//col1 = stockOwner, col2 = sharesOwned
			return rs;

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		//if query didn't succeed, return nothing
		return null;
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
	public static void updateAddress(int idOfUser, String newAddress) {

		try{
		Connection connection = Main.getConnection();
		// create the java statement
		
		//Updates the address in database
		connection.createStatement().executeUpdate("UPDATE `user` SET `address` = '" 
														+ newAddress + "' WHERE `userID` = " 
														+ idOfUser);	

		}
		catch (Exception ex) {
			System.out.println(ex);
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
	public static void deleteAccount(int idOfUser)
	{
		try {
			//get user from userIDInput
			String selectUserQuery = "DELETE FROM `user` WHERE `userID` = " + idOfUser;
			Connection connection = Main.getConnection();
			PreparedStatement userToDelete = connection.prepareStatement(selectUserQuery);
			userToDelete.executeUpdate();
			System.out.println("Successfully deleted a user.");
		} catch (Exception e) {
			System.out.println(e);
		}	
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
