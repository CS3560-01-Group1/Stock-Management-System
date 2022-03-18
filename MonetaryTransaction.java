
public class MonetaryTransaction extends Transaction {

	private double fundsTransferred;
	private String fundDestination;
	
	//Constructor
	public MonetaryTransaction(String userIDNum, String transactionIDNum, String date, int type, double funds, 
									String destination) {
		super(userIDNum, transactionIDNum, date, type);
		fundsTransferred = funds;
		fundDestination = destination;
	}

	//Function to withdraw/deposit money to increase/decrease account balance
	public void updateBalance(double funds, double destination, int type) {

	}

	//Getter functions
	public double getFundsTransferred() {
		return fundsTransferred;
	}

	public String getFundDestination() {
		return fundDestination;
	}

}
