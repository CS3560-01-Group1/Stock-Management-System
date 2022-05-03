
//public class StockTransaction extends Transaction {
//
//	private int orderID; 
//	//foreign key of order that created the transaction
//	private int userID;
//	//foreign key of user related to the orderID above
//	private double priceExecuted;
//	//price of the bid/ask order when it was executed 
//	
//	//Constructor
//	public StockTransaction() {
//	}
//	
//	//creates a new stocktransaction when an order is executed successfully
//	public void newStockTransaction(int idOfOrderExecuted, int idOfUser, double priceExecutedAtTrade)
//	{
//		//create new transaction (superclass) 
//		newTransactionRecord();
//		
//		//query insert into "stockdb.stocktransaction" with given input (including transactionID)
//		//if insert successful, populate attributes of this object
//		//remember to update user's balance when stock is sold or bought (outside scope of this method)
//			//shares * price executed = change in balance
//	}
//	
//	//retrieve the details of an existing monetaryTransaction based on a given ID
//	public void retrieveStockTransaction(int transactionIDInput)
//	{
//		//query select on the stocktransaction table (and transaction super class) that match the given transactionID
//		retrieveTransaction(transactionIDInput); //populates attributes of superclass
//		//if successful, populate attributes of subclass (this one) from attributes matching row 
//	}
//	
//	public void archiveStockTransaction()
//	{
//		//"delete" row on the "stockdb.stocktransaction" table matching this object's transactionID
//		//also do the same to the "stockdb.transaction" table for the same transactionID
//		archiveTransaction();
//		//finally, erase this object's attributes
//	}
//		
//	//Getter Methods
//	public double getExecutedPrice(){
//		return(this.priceExecuted);
//	}
//	
//	//what is the user that created this transaction
//	public int belongsToUser() {
//		return(this.userID);
//	}
//	
//	//what is the order ID that created this transaction
//	public int belongsToOrder() {
//		return(this.orderID);
//	}
//}
