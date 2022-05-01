import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import javax.swing.JFrame;


//This is a test to see if my git connected correctly 
//Committing this should change something in main?
//3rd comment appearing on Josh branch first, then merging to main to see if it updates.
//4th comment to not make my branch disappear in the first place?
//Testing github push
//Push test from Justin branch

public class Main {
	
	public static Timer marketDelay = new Timer();
	
	public static void main(String[] args) throws Exception {
		//Creation of thread objects, needs to happen before initialize() 
		MultiThreadStocks abcThread = new MultiThreadStocks("ABC");
		MultiThreadStocks defThread = new MultiThreadStocks("DEF");
		MultiThreadStocks ghiThread = new MultiThreadStocks("GHI");
		MultiThreadStocks jklThread = new MultiThreadStocks("JKL");
		MultiThreadStocks memThread = new MultiThreadStocks("MEM");
		MultiThreadStocks mnoThread = new MultiThreadStocks("MNO");
		MultiThreadStocks pqrThread = new MultiThreadStocks("PQR");
		MultiThreadStocks qqqThread = new MultiThreadStocks("QQQ");
		MultiThreadStocks stuThread = new MultiThreadStocks("STU");
		MultiThreadStocks vwxThread = new MultiThreadStocks("VWX");
		MultiThreadStocks yzThread = new MultiThreadStocks("YZ");
		//Start all the threads, these will run forever until the program is closed (See MultiThreadStocks for details on how this happens)
		abcThread.start(); defThread.start(); ghiThread.start(); jklThread.start();
		memThread.start(); mnoThread.start(); pqrThread.start(); qqqThread.start();
		stuThread.start(); vwxThread.start(); yzThread.start();

		initialize();
	}

	//Starts up program user interface
	public static void initialize() {
		UserInterface cardLayout = new UserInterface();
		cardLayout.setLocationRelativeTo(null);
		cardLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardLayout.setVisible(true);
	}
	
	//Establish SQL database connection
	public static Connection getConnection() throws Exception
	{
		
		String url = "jdbc:mysql://127.0.0.1:3306/stockdb";
		// if using local instance: url = "jdbc:mysql://localhost:3306/stockdb";
		String username = "root";
		String password = ""; 

		
		try 
		{   
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
	
		    return connection;	    
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		    throw new IllegalStateException("Cannot connect the database!", e);

		}
	}
}
