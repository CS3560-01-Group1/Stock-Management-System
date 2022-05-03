import java.sql.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MultiThreadStocks extends Thread {
    private String tickerSymbol;
    public MultiThreadStocks(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }
    @Override
    public void run() {

        boolean loop = true;    //keep simulator going forever
        Random rand = new Random();     //rand number initialization
        int upperbound = 11;    //Upper bound of 11 to give 11 options in the potential stockChange
        double ogPrice, askPrice, bidPrice, FiftyTwoWeekHigh;     //Value of stock that will be referenced in stockChange  //new value that will be the current price and actual value of selling
        double todaysChangePercent = 0;     //stat tracker that will keep track of the summation of percentage change
        try{
            //Connect to Database
            Connection connection = Main.getConnection();
        
            String selectString = "SELECT ask, bid, ogPrice, 52_Week FROM stockdb.stock WHERE stockSymbol = ?";   //updateString that we allows us to use variables instead of declaring a query all at once. Replaces ? with appropiate parameter
            PreparedStatement selectStatement = connection.prepareStatement(selectString);       //Prepared statements are used when wanting to Modify, Update, or Insert values from our DB
            selectStatement.setString(1, tickerSymbol);      //First ? will be our askPrice that we created
            ResultSet rs = selectStatement.executeQuery();         

            rs.next();
            ogPrice = rs.getDouble("ogPrice");  //Grab the ogPrice from the database; values will go up or down a certain percentage based off of this value
            askPrice = rs.getDouble("ask"); //constantly grabbing the ask price
            FiftyTwoWeekHigh = rs.getDouble("52_Week");

            System.out.println(tickerSymbol + "'s Original Value: " + ogPrice);
            while(loop){
                int stockChange = rand.nextInt(upperbound); //stockChange will be given a random value from 0 - 10
                //switch case that will be looped and given a new random value each time, based on that value will display the new value of the stock.
                switch(stockChange){
                    //Extreme Loss -20% of original value
                    case 0:
                        askPrice -= ogPrice * 0.2;    //Modifies new value by the original value times a percentage (gain/loss)
                        todaysChangePercent -= 20;          //Tracker that keeps track of the total percent change of new value versus original value
                        if(askPrice < 0) {                  //Logic to prevent a stock from going negative
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Extreme Loss!! %.2f\n", askPrice);     //Debug Log and ne w value, two decimal points from right
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");       //Console log to see the change
                        break;
                    //Large Loss -10% of original value
                    case 1:
                        askPrice -= ogPrice * 0.1;
                        todaysChangePercent -= 10;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Large Loss! %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Medium Loss -5% of original value
                    case 2:
                        askPrice -= ogPrice * 0.05;
                        todaysChangePercent -= 5;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Medium Loss: %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Small Loss -2% of original value
                    case 3:
                        askPrice -= ogPrice * 0.02;
                        todaysChangePercent -= 2;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Small Loss: %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Mini Loss -1% of original value, honetly just padding to prevent Extreme Loss/Gain from happening too often
                    case 4:
                        askPrice -= ogPrice * 0.01;
                        todaysChangePercent -= 1;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Mini Loss: %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //No change +-0% 
                    case 5:
                        System.out.printf(tickerSymbol + " No Change: %.2f\n", askPrice);
                        todaysChangePercent += 0;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Mini Gain +1% of original value, just more padding
                    case 6:
                        askPrice += ogPrice * 0.01;
                        todaysChangePercent += 1;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Mini Gain: %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Small Gain +2% of original value
                    case 7:
                        askPrice += ogPrice * 0.02;
                        todaysChangePercent += 2;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Small Gain: %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Medium Gain +5% of original value
                    case 8:
                        askPrice += ogPrice * 0.05;
                        todaysChangePercent += 5;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Medium Gain: %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Large Gain +10% of original value
                    case 9:
                        askPrice += ogPrice * 0.1;
                        todaysChangePercent += 10;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Large Gain! %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Extreme Gain +20% of original value
                    case 10:
                        askPrice += ogPrice * 0.2;
                        todaysChangePercent += 20;
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " Extreme Gain!! %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                    //Default in case something breaks, just returns the same value ie No Change
                    default:
                        if(askPrice < 0) {
                            askPrice = 0;
                            todaysChangePercent = -100;
                        }
                        System.out.printf(tickerSymbol + " No Change: %.2f\n", askPrice);
                        System.out.println(tickerSymbol + " Daily Change: " + todaysChangePercent + "%");
                        break;
                }
                System.out.println();
                bidPrice = askPrice * 0.9952;   //quick google search as to what the average spread is for stocks (amount between ask and bid) 
                String updateString = "UPDATE stockdb.stock SET ask = ?, bid = ?, 52_Week = ? WHERE stockSymbol = ?";   //updateString that we allows us to use variables instead of declaring a query all at once. Replaces ? with appropiate parameter
                PreparedStatement updateAskPrice = connection.prepareStatement(updateString);       //Prepared statements are used when wanting to Modify, Update, or Insert values from our DB
                updateAskPrice.setDouble(1, askPrice);      //First ? will be our askPrice that we created
                updateAskPrice.setDouble(2, bidPrice);      //Second ? will be our new bid price that is updated
                updateAskPrice.setDouble(3, FiftyTwoWeekHigh); //default 52_Week price assuming nothing changed
                updateAskPrice.setString(4, tickerSymbol);      //Third ? will be the stock name of our choice
                if (FiftyTwoWeekHigh < ((askPrice + bidPrice)/2)) 
                {
                	//if 52_week high is surpassed by average trading price, change it too. 
                	FiftyTwoWeekHigh = (askPrice + bidPrice)/2;
                	updateAskPrice.setDouble(3, ((askPrice+ bidPrice)/2));
            	}
                	
                updateAskPrice.executeUpdate();                           //Giving the greenlight to execute our update query
                TimeUnit.SECONDS.sleep(15);  //stocks will change values every x seconds
            }
        }catch(Exception e){
            System.out.println(e);
        } 
    }
}
