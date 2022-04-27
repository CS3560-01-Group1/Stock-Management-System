import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Order extends Transaction{

	private int orderType; 
	//buy/sell : 0/1 -> refers to stockdb.ordertypetable 
	private String orderTypeDesc;
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
		
		//Verify type order/quantity are valid (outside this method's scope)
			//can't purchase share quantity > user balance
			//can't sell share quantity > total shares owned (of that stock) 
		
		//create new transaction
		this.newTransactionRecord(idOfUser);
		System.out.println(this.getTransactionID());
		//query insert into "stockdb.order" table with the given inputs
			//buy orders -> quantity input = positive
			//sell order -> quantity input = negative
			//orderStatus = 0 default
			//executedPrice = null default
		if (typeOfOrder == 1) { //if sell order type, shares are negative
			quantityInput *= -1;
		}
		String query = "INSERT INTO stockdb.order(transactionID, orderType, stockSymbol, quantity)"
				+ "VALUES (" + this.getTransactionID() + "," + typeOfOrder + ",\"" + stockSymbol + "\"," + quantityInput + ")";
		try
		{

			//establishing connection to database
			Connection connection = Main.getConnection();
			PreparedStatement insertQuery = connection.prepareStatement(query);
			insertQuery.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			System.out.println("Query Complete: recorded a new Order. Transaction ID: " + this.getTransactionID());
		}
			
		//if successful, update this object's attributes accordingly
		retrieveOrder(this.getTransactionID());
		
		//As soon as the order goes through, the order is completed
//		completeOrder(0); do this outside this method's scope 
	}
	
	//retrieve details of existing an order based on matching orderID
	public void retrieveOrder(int transactionIDInput)
	{
		//query select on "stockdb.order" table where order.transactionID = transactionIDInput
		//update attributes of this object with data of matching row
	}
	
	//occurs when the order is "completed" and the shares have been sold/bought
	public String completeOrder(int transactionIDInput) 
	{ 
		try
		{
			//Get all order information
			String queryOrderSelect = "SELECT * FROM stockdb.order\r\n"
					+ "JOIN stockdb.ordertypetable ON `order`.orderType = ordertypetable.orderType\r\n"
					+ "JOIN stockdb.orderstatuses ON `order`.orderStatus = orderstatuses.orderStatus\r\n"
					+ "JOIN stockdb.transaction ON `order`.transactionID = transaction.transactionID\r\n"
					+ "WHERE `order`.transactionID = " + transactionIDInput;
			Connection connection = Main.getConnection();
			ResultSet rs = connection.createStatement().executeQuery(queryOrderSelect);
			rs.next();
			
			this.orderTypeDesc = rs.getString("typeOfOrder");
			this.quantity = rs.getDouble("quantity");
			
			//Also get current stock price for late user
			String queryStockPrices = "SELECT * FROM stockdb.stock WHERE stockSymbol = \"" + rs.getString("stockSymbol") + "\"";
			ResultSet rs2 = connection.createStatement().executeQuery(queryStockPrices);
			rs2.next();
			
			String orderType = rs.getString("typeOfOrder");
			
			//IF SELL TYPE: verify user's curr shares if valid to sell
			if (orderType.equals("sell"))
			{
				//current shares owned by user	
				String queryGetShares = "SELECT usersShareTotal.sharesOwned FROM stockdb.usersShareTotal "
						+ "WHERE stockOwner = \"" + rs.getInt("userID") + "_" + rs.getString("stockSymbol") + "\"";
				ResultSet rs3 = connection.createStatement().executeQuery(queryGetShares);
				rs3.next();
				//If not verified -> expireOrder and return out of method
				if (rs3.getDouble("sharesOwned") < -1 * rs.getDouble("quantity")) {
//					System.out.println("Lack of Share's owned");
					expireOrder(transactionIDInput);

					return null;
				}
				
				//verified. complete order and update user balance
				String orderStatusUpdate = "UPDATE `order` SET `order`.orderStatus = 1, "
						+ "`order`.executedPrice = " + rs2.getDouble("ask") + " WHERE transactionID = "
						+ transactionIDInput;
				PreparedStatement update = connection.prepareStatement(orderStatusUpdate);
				update.executeUpdate();
				
				this.executedPrice = rs2.getDouble("ask");
				
				double balanceChange = -1 * (rs2.getDouble("ask") * rs.getDouble("quantity"));
				String userBalanceUpdate = "UPDATE `user` SET `user`.balance = `user`.balance + " 
						+ balanceChange + " WHERE userID = " + rs.getInt("userID");		
				update = connection.prepareStatement(userBalanceUpdate);
				update.executeUpdate();
				
			}
			
			//IF BUY TYPE: verify user's curr balance if valid to buy at stock price
			else 
			{
				//current user balance
				String queryGetBalance = "SELECT user.balance FROM stockdb.user WHERE userID = " + rs.getInt("userID");
				ResultSet rs3 = connection.createStatement().executeQuery(queryGetBalance);
				rs3.next();
				
				
				//If not verified -> expireOrder and return out of method
				if (rs3.getDouble("balance") < (rs.getDouble("quantity") * rs2.getDouble("bid"))) {
					expireOrder(transactionIDInput);
//					System.out.println("Over BUDGET!");
					return null;
				}
				
				//verified. complete order, update user balance
				String orderStatusUpdate = "UPDATE `order` SET `order`.orderStatus = 1, "
						+ "`order`.executedPrice = " + rs2.getDouble("bid") + " WHERE transactionID = "
						+ transactionIDInput;
				PreparedStatement update = connection.prepareStatement(orderStatusUpdate);
				update.executeUpdate();
				
				this.executedPrice = rs2.getDouble("bid");
				
				double balanceChange = (rs2.getDouble("bid") * rs.getDouble("quantity"));
				String userBalanceUpdate = "UPDATE `user` SET `user`.balance = `user`.balance - " 
						+ balanceChange + " WHERE userID = " + rs.getInt("userID");		;
				update = connection.prepareStatement(userBalanceUpdate);
				update.executeUpdate();
			}	
			//status change after completed
			this.orderStatus = 1;
			
			//transaction date timestamp renew
			String transactionUpdate = "UPDATE `stockdb`.transaction SET transactionDate = CURRENT_TIMESTAMP "
					+ "WHERE transactionID = " + transactionIDInput;
			PreparedStatement update = connection.prepareStatement(transactionUpdate);
			update.executeUpdate();
			
			
			return (rs.getString("typeOfOrder"));
		
	      
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}
	
	//expire an order entry 
	private void expireOrder(int transactionIDToExpire)
	{
		//find matching order with transaction id = transactionIDToExpire
		//mark orderStatus = 2 (expired) 
		try {
		String updateStatusQuery = "UPDATE `order` SET `order`.orderStatus = 2 WHERE transactionID = "
				+ transactionIDToExpire;
		Connection connection = Main.getConnection();
		PreparedStatement update = connection.prepareStatement(updateStatusQuery);
		update.executeUpdate();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void deleteAllExpiredOrders(int userIDOfOrders)
	{
		try
		{
			Connection connection = Main.getConnection();
			//select appropriate order list
			String querySelectExpired = "SELECT * FROM stockdb.order\r\n"
					+ "JOIN stockdb.transaction ON `order`.transactionID = `transaction`.transactionID\r\n"
					+ "WHERE `transaction`.userID = " + userIDOfOrders + " AND `order`.orderStatus = 2";
			ResultSet rs = connection.createStatement().executeQuery(querySelectExpired);
			
			//delete child (order) then parent (transaction) of matching transactionID
			while (rs.next())
			{
				int tranIDToDelete = rs.getInt("transactionID");
				//delete order
				String queryDeleteOrder = "DELETE FROM stockdb.order WHERE transactionID = " 
						+ tranIDToDelete;
				PreparedStatement delete = connection.prepareStatement(queryDeleteOrder);
				delete.executeUpdate();
				
				//delete transaction
				this.archiveTransaction(tranIDToDelete);
				
				System.out.println("Deleted transactionID = " + tranIDToDelete);
			}
			

			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	//only use when deleting user account
	private void deleteAllUserOrders(int idOfUser)
	{
		try {
			//get orders from userIDInput
			String selectOrdersQuery = "SELECT `order`.transactionID, transaction.userID FROM stockdb.order"
					+ "JOIN stockdb.transaction ON `order`.transactionID = transaction.transactionID"
					+ "WHERE userID = " + idOfUser;
			Connection connection = Main.getConnection();
			ResultSet ordersToDelete = connection.createStatement().executeQuery(selectOrdersQuery);
			
			//delete each order from each result
			while (ordersToDelete.next())
			{
				//save transaction id
				int tempTransactionID = ordersToDelete.getInt("transactionID");
				//delete order child first
				String deleteOrderQuery = "DELETE FROM stockdb.order WHERE `order`.transactionID = "
						+ tempTransactionID;
				PreparedStatement deleteQuery = connection.prepareStatement(deleteOrderQuery);
				deleteQuery.executeUpdate();
				
				//then delete transaction parent based on transaction id
				archiveTransaction(tempTransactionID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
	
	public String getOrderTypeDetail() {
		return this.orderTypeDesc;
	}
	
}
