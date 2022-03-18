
public abstract class Transaction {

	private String userID; //foreign key
	private String transactionID;
	private String transactionDate;
	private int transactionType;
	
	//Constructor
	public Transaction(String userIDNum, String transactionIDNum, String date, int type) {
		userID = userIDNum;
		transactionID = transactionIDNum;
		transactionDate = date;
		transactionType = type;
	}

	//Records a transaction in the transactions list for the user
	public void recordTransaction(String transID, String date, int type) {

	}

	//Getter functions
	public String getTransactionID() {
		return transactionID;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public int getTransactionType() {
		return transactionType;
	}
}
