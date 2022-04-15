import javax.swing.JFrame;

//This is a test to see if my git connected correctly 
//Committing this should change something in main?
//3rd comment appearing on Josh branch first, then merging to main to see if it updates.
//Testing github push
//Push test from Justin branch
public class Main {

	public static void main(String[] args) {
		initialize();
	}

	//Starts up program user interface
	public static void initialize() {
		UserInterface cardLayout = new UserInterface();
		cardLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardLayout.setVisible(true);
	}
	
	//login method for registered users
	public static void login() 
	{
		
	}
	
	public static void logoff() 
	{
		
	}
	
	//method to register new users
	public static void createUserAccount()
	{
		
	}
	
	//should return the user that is currently logged into this instance of the program
	//use for user specific operations
	//!!is this bad practice? Anyone with account managing experience know how to do this better?
	//temporarily commented out to prevent compilation error
	/*
	public static User currentUser()
	{
		
		return(); 	
	}
	*/
	
	//meant to allow the viewing/searching of available stocks for trade
	public static void displayStockListings()
	{
		
	}
	
	//retrieve a stock and return relevant information (bid/ask/PE Ratio, EPS...)
	public static void displayStockDetails(String stockID)
	{
		
	}

}
