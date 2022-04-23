import java.util.TimerTask;

import javax.swing.JOptionPane;

public class MarketDelay extends TimerTask{
	
	private String orderType;
	private String quantity;
	private String stockSymbol;
	

	public MarketDelay(String orderType, String quantity, String stockSymbol) {
		this.orderType = orderType;
		this.quantity = quantity;
		this.stockSymbol = stockSymbol;
	}

	@Override
	public void run() {
		if (orderType == "Buy") {
			JOptionPane.showMessageDialog(null, "Successfully bought " + quantity + " shares of " + stockSymbol);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Successfully sold " + quantity + " shares of " + stockSymbol);
		}
		
	}

}
