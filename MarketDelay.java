import java.util.TimerTask;

import javax.swing.JOptionPane;

public class MarketDelay extends TimerTask{
	

	private String quantity;
	private String stockSymbol;
	private int transactionID;
	

	public MarketDelay(String quantity, String stockSymbol, int transactionID) {
		this.quantity = quantity;
		this.stockSymbol = stockSymbol;
		this.transactionID = transactionID;
	}

	@Override
	public void run() {
		Order openOrder = new Order();
		String orderResult = openOrder.completeOrder(transactionID);
		
		if (orderResult == null) //Order failed to complete for some reason
		{
			//expired order message - to be completed
			JOptionPane.showMessageDialog(null, "Your order of " + quantity + " shares of " + stockSymbol);
		}
		else if (orderResult.equals("buy")) {
			JOptionPane.showMessageDialog(null, "Successfully bought " + quantity + " shares of " + stockSymbol + " at $" + openOrder.getExecutedPrice());
		}
		else if (orderResult.equals("sell"))
		{
			JOptionPane.showMessageDialog(null, "Successfully sold " + quantity + " shares of " + stockSymbol + " at $" + openOrder.getExecutedPrice());
		}

		
		
	}

}
