
public class Order {

	private int orderID;
	private int userID; 
	//foreign key to order owner (user who placed the order)
	private int orderType; 
	//buy/sell : 0/1 -> refers to stockdb.ordertypetable 
	private double quantity;
	private String stockSymbol; 
	//foreign key to the stock the order is placed onto
	
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
		//query insert into "stockdb.order" table with the given inputs
		//if successful, update this object's attributes accordingly
		
		//create new transaction as soon as the order goes through
		executeOrder(0);
	}
	
	//retrieve details of existing an order based on matching orderID
	public void retrieveOrder(int orderIDInput)
	{
		//query select on "stockdb.order" table where orderIDInput = order.orderID
		//update attributes of this object with data of matching row
	}
	
	//Makes a new StockTransaction entry once an order has been executed
	private void executeOrder(int waitTime) 
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
		
//		StockTransaction executedOrder = new StockTransaction();
//		executedOrder.newStockTransaction(orderID, userID, priceExecutedAtTrade);
	}
	
	//delete an order entry 
	private void revokeOrder()
	{
		//query delete on row matching this objects orderID to "stockdb.order.orderID" 
		//Maybe archive it instead
		
	}
	
	//Getter functions
	public int getOrderID() {
		return orderID;
	}
	
	public int belongsToUser() {
		return this.userID;
	}

	//refer to ordertypetable in database to decipher return input meaning 
	public int getOrderType() {
		return orderType;
	}

	public double getQuantity() {
		return quantity;
	}

	public String belongsToStock() {
		return stockSymbol;
	}
	
}
