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
			String transactionActivity, float amount)
	{
		//verify input (outside scope of method)
		//create new transaction (superclass) 
		this.newTransactionRecord(idOfUser);
		
		//query insert into "stockdb.monetarytransaction" with given input (including transactionID)
		//if insert successful, populate attributes of this object
		//remember to update user's balance (outside scope of this method)
		try {
			String query = "INSERT INTO stockdb.monetarytransaction (`transactionID`, `externalBankRoute#`, "
				+ "`externalBankName`, `externalBankAct#`, `activityType`, `amount`) VALUES"
				+ "(" + this.getTransactionID() + ", '" + routingNum + "', '" + bankName + "', '" + bankAccountNum 
				+ "', '" + transactionActivity + "', " + amount +")";
			Connection connection = Main.getConnection();
			PreparedStatement createQuery = connection.prepareStatement(query);
			createQuery.executeUpdate();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	//only use when deleting user account
	public static void deleteAllUserMonTransactions(int idOfUser)
	{
		try {
			//get orders from userIDInput
			String selectMonetaryTransQuery = "SELECT monetarytransaction.transactionID, transaction.userID FROM stockdb.monetarytransaction"
					+ " JOIN stockdb.transaction ON monetarytransaction.transactionID = transaction.transactionID"
					+ " WHERE userID = " + idOfUser;
			Connection connection = Main.getConnection();
			ResultSet transactionsToDelete = connection.createStatement().executeQuery(selectMonetaryTransQuery);
			
			//delete each order from each result
			while (transactionsToDelete.next())
			{
				//save transaction id
				int tempTransactionID = transactionsToDelete.getInt("transactionID");
				//delete order child first
				String deleteMonetaryTransactionsQuery = "DELETE FROM stockdb.monetarytransaction WHERE `monetarytransaction`.transactionID = "
						+ tempTransactionID;
				PreparedStatement deleteQuery = connection.prepareStatement(deleteMonetaryTransactionsQuery);
				deleteQuery.executeUpdate();
				
				//then delete transaction parent based on transaction id
				archiveTransaction(tempTransactionID);
			}
		} catch (Exception e) {
			System.out.println(e);
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
		return this.externalBankActNum;
	}
	
	//return if this was a deposit or withdrawl
	public String getActivityType() {
		return this.activityType;
	}

}
