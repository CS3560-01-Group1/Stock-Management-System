import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Transaction {

	private int transactionID; //primary key for MonetaryTransaction and Order
	private String transactionDate;
	private int userID;
	
	//Constructor
	public Transaction() {
	}
	

	//Records a transaction in the transactions list for the user
	//Called by MonetaryTransaction or StockTransaction Classes
	public void newTransactionRecord(int idOfUser) {
		//query "INSERT INTO stockdb.transaction (userID) VALUES()" with given userID
		try
		{
			String query = "INSERT INTO stockdb.transaction (userID) VALUES(" + idOfUser + ")";
			Connection connection = Main.getConnection();
			PreparedStatement createQuery = connection.prepareStatement(query);
			createQuery.executeUpdate();
			//this will automatically records unique transactionID and date upon creation 
			//if insert successful, update this objects attributes
			
			//get ID transaction we just created
			ResultSet rs = connection.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			
			this.transactionID = rs.getInt("LAST_INSERT_ID()");
			
			//get attributes of transaction and set this object's attributes
			String query2 = "SELECT * FROM stockdb.transaction WHERE transactionID = " + this.transactionID;
			rs = connection.createStatement().executeQuery(query2);
			rs.next();
			
			this.transactionDate = rs.getString("transactionDate").toString();
			this.userID = idOfUser;
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		//this will automatically records unique transactionID and date upon creation 
		//if insert successful, update this objects attributes 
		retrieveTransaction(this.transactionID);
	}
	
	
	public void retrieveTransaction(int transactionIDInput)
	{
		//search database for transaction matching given ID input
		//if search successful, update this object's attributes
	}
	
	public void archiveTransaction()
	{
		//"delete" entry on "stockdb.transaction" table of matching transaction ID
	}

	//Getter functions
	public int getTransactionID() {
		return transactionID;
	}

	public String getTransactionDate() {
		return transactionDate;
	}
	
	public int belongsToUser() {
		return this.userID;
	}

}
