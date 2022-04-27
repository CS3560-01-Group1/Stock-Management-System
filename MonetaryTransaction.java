import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MonetaryTransaction extends Transaction {

	private String externalBankRouteNum; //routing num
	private String externalBankName; //name of bank sending/receiving from
	private String externalBankActNum; //account num 
	private String activityType; //DEPOSIT or WITHDRAWL
	
	private double fundsTransferred;

	
	//Constructor
	public MonetaryTransaction() {
	}

	public void newMonetaryTransaction(int idOfUser, String routingNum, String bankName, String bankAccountNum,
			String transactionActivity)
	{
		//verify input (outside scope of method)
		//create new transaction (superclass) 
		newTransactionRecord(idOfUser);
		
		//query insert into "stockdb.monetarytransaction" with given input (including transactionID)
		//if insert successful, populate attributes of this object
		//remember to update user's balance (outside scope of this method)
	}
	
	//give user chance to change a transaction they just made in case they made a mistake 
	public void rewriteMonetaryTransaction(String routingNum, String bankName, String bankAccountNum,
				String transactionActivity)
		{
			//transaction record already exists
			//update query onto same monetarytransaction record matching this.transactionID
			//check for new data 
				//if new input, update the fields. 
				//update timestamp 
				//"UPDATE `stockdb`.`transaction` SET 
				//transactionDate = CURRENT_TIMESTAMP WHERE (`transactionID` = 'ID')"
					//make sure "ID" is this object's transactionID
		}
	
	//retrieve the details of an existing monetaryTransaction based on a given ID
	public void retrieveMonetaryTransaction(int transactionIDInput)
	{
		//query select on the monetarytransaction table (and transaction super class) that match the given transactionID
		retrieveTransaction(transactionIDInput); //populates attributes of superclass
		//if successful, populate attributes of subclass (this one) from attributes of matching row
	}
	
	//remove a transaction before it has been executed (in our case it can be anytime)
	public void cancelTransaction(int transactionIDInput)
	{
		//find matching row to this objects transactionID
		//remove row and all entries
		//finally, remove transaction super class row
		archiveTransaction(transactionIDInput);
	}
	
	//only use when deleting user account
	private void deleteAllMonTransactionsOfUser(int idOfUser)
	{
		try {
			//get orders from userIDInput
			String selectMonetaryTransQuery = "SELECT monetarytransaction.transactionID, transaction.userID FROM stockdb.order"
					+ "JOIN stockdb.transaction ON monetarytransaction.transactionID = transaction.transactionID"
					+ "WHERE userID = " + idOfUser;
			Connection connection = Main.getConnection();
			ResultSet transactionsToDelete = connection.createStatement().executeQuery(selectMonetaryTransQuery);
			
			//delete each order from each result
			while (transactionsToDelete.next())
			{
				//save transaction id
				int tempTransactionID = transactionsToDelete.getInt("transactionID");
				//delete order child first
				String deleteMonetaryTransactionsQuery = "DELETE FROM stockdb.order WHERE `order`.transactionID = "
						+ tempTransactionID;
				PreparedStatement deleteQuery = connection.prepareStatement(deleteMonetaryTransactionsQuery);
				deleteQuery.executeUpdate();
				
				//then delete transaction parent based on transaction id
				archiveTransaction(tempTransactionID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}	
	

	//Getter functions
	public double getFundsTransferred() {
		return fundsTransferred;
	}

	public String getRoutingNum() {
		return externalBankRouteNum;
	}
	
	public String getBankName() {
		return this.externalBankName;	
	}
	
	public String getBankAcntNum() {
		return this.externalBankRouteNum;
	}
	
	//return if this was a deposit or withdrawl
	public String getActivityType() {
		return this.activityType;
	}

}
