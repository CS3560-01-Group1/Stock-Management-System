import java.sql.ResultSet;

public abstract class Transaction {

	private int transactionID;
	private String transactionDate;
	
	//Constructor
	public Transaction() {
	}
	

	//Records a transaction in the transactions list for the user
	//Called by MonetaryTransaction or StockTransaction Classes
	public void newTransactionRecord() {
		//query "INSERT INTO stockdb.transaction VALUES()" 
		//automatically records unique ID and date upon creation 
		//if insert successful, update this objects attributes 
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

}
