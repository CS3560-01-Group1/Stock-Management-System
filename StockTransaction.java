
public class StockTransaction extends Transaction {

	private Order order; 
	//foreign key of order that created the transaction
	final private static int type = 0; 
	//0 denotes that this is a StockTransaction subclass of Transaction (should never change)
    //this may be an unnecessary specification?
	private int executedPrice; 
	//price of the bid/ask order when it was executed 
	private int shareTotal;
	//sum of all shares traded between all transactions of the *same* stock
	private boolean latestTransaction = true;
	//if the transaction is created, it is obviously the latest one
	//need update previous latest transaction to be "false"
	
	//Constructor
	public StockTransaction(String userIDNum, String transactionIDNum, String date, Order orderKey) {
		super(userIDNum, transactionIDNum, date, type);
		order = orderKey;
		
	}

	//Function to increase/decrease account balance depending on purchase/sale amount.
	public void updateBalance(double amount, int type) {

	}
}
