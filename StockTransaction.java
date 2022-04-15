
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
	private boolean latestTransaction = false;
	//if the transaction is created, it is obviously the latest one
	/*need update previous latest transaction to be "false" before we can make the new one true
	 * in case of weird conflict where two transactions are both true
	*/
	
	//Constructor
	public StockTransaction(String userIDNum, String transactionIDNum, String date, Order orderKey) {
		super(userIDNum, transactionIDNum, date, type);
		order = orderKey;
		this.updateShares();
		//update shares and then change previous transaction to not be the latest transaction
		this.latestTransaction = true; //this is the new latest transaction
		
	}

	//Function to increase/decrease account balance depending on purchase/sale amount.
	public void updateBalance(double shareQuantity, int type) {

	}
	
	//sums the previous total shares with the new shares bought/sold
	public void updateShares()
	{
		if (order.getOrderType() == 0) //order type 0 indicates buy order -> shares increase
		{
			shareTotal = order.getQuantity() + this.getPrevTransactionShareTotal();
		}
		else //order is type 1 indicates sell order -> shares decreased
		{
			shareTotal = (-1 * order.getQuantity()) + this.getPrevTransactionShareTotal();
		}
		
	}
	
	//Finds the previous transaction created by an order with the same stockSymbol and gets the shareTotal
	public int getPrevTransactionShareTotal()
	{
//		Search transactions in database that match filters order.userIDNum, order.getStockSymbol(), latestTransaction = true
//      retrieve matching transaction's shareTotal
//		before returning, change the latestTransaction status of the previous transaction from true -> false
		return 0; //temporarily set to 0 to prevent compile error
	}
	
	//Getter Methods
	public int getShareTotal()
	{
		return(shareTotal);
	}
}
