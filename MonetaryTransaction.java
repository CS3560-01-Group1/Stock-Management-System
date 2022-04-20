import java.sql.ResultSet;

public class MonetaryTransaction extends Transaction {

	private int transactionID;
	private int userID;
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
		//Link ID of transaction to this subclass of transaction
		transactionID = newTransactionRecord();
		
		//query insert into monetary transaction with given input (including transactionID)
		//if insert successful, populate attributes of this object
		//remember to update user's balance (outside scope of this method)
	}
	
	//retrieve the details of an existing monetaryTransaction based on a given ID
	public void retrieveMonetaryTransaction(int transactionIDInput)
	{
		//query select on the monetarytransaction (and transacaction super class) that match the given transactionID
		retrieveTransaction(transactionIDInput); //populates attributes of superclass
		//if successful, populate attributes of subclass (this one) 
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
	
	public int getUserIDOf() {
		return this.userID;
	}

}
