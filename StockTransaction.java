
public class StockTransaction extends Transaction {

	private Order order; //foreign key
	
	//Constructor
	public StockTransaction(String userIDNum, String transactionIDNum, String date, int type, Order order1) {
		super(userIDNum, transactionIDNum, date, type);
		order = order1;
	}

	//Function to increase/decrease account balance depending on purchase/sale amount.
	public void updateBalance(double amount, int type) {

	}
}
