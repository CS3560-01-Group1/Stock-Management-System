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
//Testing github push
//Push test from Justin branch
public class Main {
	
	public static Timer marketDelay = new Timer();
	
	public static void main(String[] args) throws Exception {

		initialize();
//		exampleSQLQuery();
		


	}

	//Starts up program user interface
	public static void initialize() {
		UserInterface cardLayout = new UserInterface();
		cardLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardLayout.setVisible(true);
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
	
	
	//Establish SQL database connection
	public static Connection getConnection() throws Exception
	{
		
		String url = "jdbc:mysql://127.0.0.1:3306/stockdb";
		// if using local instance: url = "jdbc:mysql://localhost:3306/stockdb";
		String username = "root";
		String password = "Soccer44!"; 
		
		try 
		{   
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
	
		    return connection;	    
		} 
		catch (SQLException e) 
		{
		    throw new IllegalStateException("Cannot connect the database!", e);
		}


	}
	
	//example for how to execute database queries from application
	public static void exampleSQLQuery() throws Exception
	{
		try
		{
			//establishing connection to database
			Connection connection = getConnection();
			//A SQL query that creates a new table called 'new_table' within database 'stockdb'
			PreparedStatement query = connection.prepareStatement("CREATE TABLE `stockdb`.`new_table` (`testAtt` INT NOT NULL)");
			query.executeUpdate();
			

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			System.out.println("Query Complete: created 'new table'");
		}
		
		
		try
		{
			Connection connection = getConnection();
		 // create the java statement
	      
	      // execute the query, and get a java resultset
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM stockdb.stock");
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	//select "stocksymbol" attribute column  
	    	System.out.println(rs.getString("stockSymbol"));
	    	System.out.println("People are buying this stock at: $" + rs.getDouble("bid"));
	    	System.out.println("People are selling this stock at: $" + rs.getDouble("ask"));
	      }
	      
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			System.out.println("Query Complete: Selected all stock symbols");
		}
	}

}
