
public class Order {

	private String userID; //foreign key
	private String orderID;
	private int orderType;
	private int quantity;
	private String stockSymbol;
	
	//Constructor
	public Order(String userIDNum, String orderIDNum, int type, int amount, String stockName) {
		userID = userIDNum;
		orderID = orderIDNum;
		orderType = type;
		quantity = amount;
		stockSymbol = stockName;
	}

	//Gettter functions
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
}
