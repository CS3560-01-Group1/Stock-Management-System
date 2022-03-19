
public abstract class Transaction {

	private String userID; //Foreign Key to transaction owner
	private String transactionID;
	private String transactionDate;
	private int transactionType; //0 for StockTransactions, 1 for Monetary Transactions
	
	//Constructor
	public Transaction(String userIDNum, String transactionIDNum, String date, int type) {
		userID = userIDNum;
		transactionID = transactionIDNum;
		transactionDate = date;
		transactionType = type; //0 for StockTransactions, 1 for Monetary Transactions
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
