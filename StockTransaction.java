
public class StockTransaction extends Transaction {

	private Order order; //foreign key of order that created the transaction
	final private static int type = 0; //0 denotes that this is a StockTransaction subclass of Transaction (should never change)
							           //this may be an unnecessary specification?
	
	//Constructor
	public StockTransaction(String userIDNum, String transactionIDNum, String date, Order orderKey) {
		super(userIDNum, transactionIDNum, date, type);
		order = orderKey;
		
	}

	//Function to increase/decrease account balance depending on purchase/sale amount.
	public void updateBalance(double amount, int type) {

	}
}
