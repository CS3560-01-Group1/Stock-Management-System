
public class Order {

	private String userID; 
	//foreign key to order owner (user who placed the order)
	private String orderID;
	private int orderType; 
	//buy/sell : 0/1
	private int quantity;
	private String stockSymbol; 
	//foreign key to the stock the order is placed onto (stock symbols are stored in database)
	
	//Constructor
	public Order(String userIDNum, String orderIDNum, int type, int amount, String stockName) {
		userID = userIDNum;
		orderID = orderIDNum;
		orderType = type; 
		orderType = amount;
		stockSymbol = stockName;
	}

	//Getter functions
	public String getOrderID() {
		return orderID;
	}

	public int getOrderType() {
		return orderType;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}
	
	//Makes a new StockTransaction once an order has been executed
	public void newTransaction(String date) { 
		
	}
}
