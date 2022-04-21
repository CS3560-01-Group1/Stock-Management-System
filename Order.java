
public class Order extends Transaction{

	private int orderType; 
	//buy/sell : 0/1 -> refers to stockdb.ordertypetable 
	private double quantity;
	private String stockSymbol; 
	//foreign key to the stock the order is placed onto
	private int orderStatus;
	//current status of the order 
	// (0/1/2) = (Open/Completed/Expired)
	private double executedPrice;
	//the price paid upon Order completion (orderStatus should = 1 if this field is non-zero)
	
	//Constructor
	public Order() {
	}

	//called when a user wants to create a new order on a stock
	public void newOrder(int idOfUser, String stockSymbol, int typeOfOrder, double quantityInput)
	{
		//Cleighton will do this one 
		
		//Verify type order/quantity are valid (outside this method's scope)
			//can't purchase share quantity > user balance
			//can't sell share quantity > total shares owned (of that stock) 
		
		//create new transaction
		newTransactionRecord(idOfUser);
		//query insert into "stockdb.order" table with the given inputs
			//orderStatus = 0 default
			//executedPrice = null default
		//if successful, update this object's attributes accordingly
		
		//As soon as the order goes through, the order is completed
		completeOrder(0);
	}
	
	//retrieve details of existing an order based on matching orderID
	public void retrieveOrder(int transactionIDInput)
	{
		//query select on "stockdb.order" table where order.transactionID = transactionIDInput
		//update attributes of this object with data of matching row
	}
	
	//occurs when the order is "completed" and the shares have been sold/bought
	private void completeOrder(int waitTime) 
	{ 
		//Cleighton will do this one
		
		//Allow a given waitTime to pass to simulate the order being processed (maybe do this outside this method)
		//After alloted time VERIFY AGAIN
			//IF SELL TYPE: check user's curr shares if valid to sell
			//IF BUY TYPE: check user's curr balance if valid to buy at stock price
		//If not verified -> revokeOrder and return out of method
		
		//create new stock transaction 
			//price executed depends on the type of order 
				//buy = current bid price of stock
				//sell = current ask price of stock
		
	}
	
	//expire an order entry 
	private void expireOrder()
	{
		//mark orderStatus = 2 (expired) 
		
	}
	
	//only use if an order is still open or is expired
	private void deleteOrder()
	{
		//verify if order status = 2 (expired) = 0 (open)
			//if true, the order is eligible to be deleted
		
		//proceed to remove row entry of order matching this objects transactionID
		
		//then, delete matching row in transaction superclass
		archiveTransaction();
		
		//if all queries successful, remove this object's attributes
		
	}
	
	//Getter functions
	//refer to ordertypetable in database to decipher return input meaning 
	public int getOrderType() {
		return orderType;
	}
	
	//returns the integer identifier for the order's current status
	// 0 = open, 1 = completed, 2 = expired
	public int getOrderStatus() {
		return this.orderStatus;
	}

	public double getQuantity() {
		return quantity;
	}

	public String belongsToStock() {
		return stockSymbol;
	}
	
	public double getExecutedPrice()
	{
		//only if the order was completed will there be an executed price to return
		if (this.orderStatus == 1) { 
			return this.executedPrice;
		}
		
		return 0;
	}
	
}
