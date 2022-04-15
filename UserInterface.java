import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInterface extends JFrame{
    
    private CardLayout c1;
	
	public UserInterface() {
		//Setting title and frame size
		setTitle("Stock Management System");
		setSize(600, 600);
		
		//Creating CardLayout
		JPanel cards = new JPanel();
		c1 = new CardLayout();
		cards.setLayout(c1);
				
		//*****************************************************************************************
		//Creating a menu at the top of the window
		//*****************************************************************************************
		
		//Creating menu bar
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		//Creating menus
		JMenu homeMenu = new JMenu("Home");
		menuBar.add(homeMenu);
		JMenuItem goHome = new JMenuItem("Go To Home Page");
		homeMenu.add(goHome);
		
		JMenu accountMenu = new JMenu("Account Information");
		menuBar.add(accountMenu);
		JMenuItem viewAccount = new JMenuItem("View Account Information");
		accountMenu.add(viewAccount);
		JMenuItem editAccount = new JMenuItem("Edit Account Information");
		accountMenu.add(editAccount);
		
		JMenu stocksMenu = new JMenu("Stock List");
		menuBar.add(stocksMenu);
		JMenuItem searchStocks = new JMenuItem("Search Stocks");
		stocksMenu.add(searchStocks);
		
		JMenu fundsMenu = new JMenu("Manage Funds");
		menuBar.add(fundsMenu);
		JMenuItem myFunds = new JMenuItem("My Funds");
		fundsMenu.add(myFunds);
		JMenuItem withdraw = new JMenuItem("Withdraw Funds");
		fundsMenu.add(withdraw);
		JMenuItem deposit = new JMenuItem("Deposit Funds");
		fundsMenu.add(deposit);
		
		JMenu transactionsMenu = new JMenu("Transactions");
		menuBar.add(transactionsMenu);
		JMenuItem myTransactions = new JMenuItem("My Transactions");
		transactionsMenu.add(myTransactions);
		
		JMenu logoffMenu = new JMenu("Log Off");
		menuBar.add(logoffMenu);
		JMenuItem signOut = new JMenuItem("Sign Out");
		logoffMenu.add(signOut);
		
		//*****************************************************************************************
		//Creating panels for each screen and setting the layout
		//*****************************************************************************************
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		JPanel signUpPanel = new JPanel();
		signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
		JPanel homePanel = new JPanel();
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
		JPanel accountInfoPanel = new JPanel();
		accountInfoPanel.setLayout(new BoxLayout(accountInfoPanel, BoxLayout.Y_AXIS));
		JPanel editAccountInfoPanel = new JPanel();
		editAccountInfoPanel.setLayout(new BoxLayout(editAccountInfoPanel, BoxLayout.Y_AXIS));
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		JPanel stockInfoPanel = new JPanel();
		stockInfoPanel.setLayout(new BoxLayout(stockInfoPanel, BoxLayout.Y_AXIS));
		JPanel buyStockPanel = new JPanel();
		buyStockPanel.setLayout(new BoxLayout(buyStockPanel, BoxLayout.Y_AXIS));
		JPanel sellStockPanel = new JPanel();
		sellStockPanel.setLayout(new BoxLayout(sellStockPanel, BoxLayout.Y_AXIS));
		JPanel fundsPanel = new JPanel();
		fundsPanel.setLayout(new BoxLayout(fundsPanel, BoxLayout.Y_AXIS));
		JPanel withdrawPanel = new JPanel();
		withdrawPanel.setLayout(new BoxLayout(withdrawPanel, BoxLayout.Y_AXIS));
		JPanel depositPanel = new JPanel();
		depositPanel.setLayout(new BoxLayout(depositPanel, BoxLayout.Y_AXIS));
		JPanel transactionsPanel = new JPanel();
		transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
		
		//*****************************************************************************************
		//Creating and centering the components
		//*****************************************************************************************
		
		//Creating labels
		JLabel login = new JLabel("Login");
		JLabel username = new JLabel("Username");
		JLabel password = new JLabel("Password");
		JLabel signUp = new JLabel("Sign Up");
		JLabel home = new JLabel("Home");
		JLabel accountInfo = new JLabel("Account Information");
		JLabel editAccountInfo = new JLabel("Edit Account Information");
		JLabel search = new JLabel("Stock List");
		JLabel stockInfo = new JLabel("Stock Information");
		JLabel buyStock = new JLabel("Buy Stocks");
		JLabel sellStock = new JLabel("Sell Stocks");
		JLabel manageFunds = new JLabel("Manage Funds");
		JLabel withdrawFunds = new JLabel("Withdraw Funds");
		JLabel depositFunds = new JLabel("Deposit Funds");
		JLabel transactions = new JLabel("Past Transactions");
		
		//Centering labels
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		password.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUp.setAlignmentX(Component.CENTER_ALIGNMENT);
		home.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		editAccountInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		search.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyStock.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellStock.setAlignmentX(Component.CENTER_ALIGNMENT);
		manageFunds.setAlignmentX(Component.CENTER_ALIGNMENT);
		withdrawFunds.setAlignmentX(Component.CENTER_ALIGNMENT);
		depositFunds.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Creating buttons for functions and navigation
		JButton signInButton = new JButton("Sign In");
		JButton signUpButton = new JButton("Sign Up");
		JButton createAccountButton = new JButton("Create Account");
		JButton signUpBackButton = new JButton("Back");
		JButton logoutButton = new JButton("Log Out");
		JButton accountInfoButton = new JButton("Account Information");
		JButton searchStocksButton = new JButton("Search Stocks");
		JButton fundsButton = new JButton("Manage Funds");
		JButton fundsBackButton = new JButton("Back");
		JButton transactionsButton = new JButton("Past Transactions");
		JButton accountInfoBackButton = new JButton("Back");
		JButton searchStocksBackButton = new JButton("Back");
		JButton accountInfoEditButton = new JButton("Edit Information");
		JButton editAccountInfoConfirmButton = new JButton("Confirm");
		JButton editAccountInfoBackButton = new JButton("Back");
		JButton searchButton = new JButton("Search");
		JButton stockInfoButton = new JButton("Stock Information");
		JButton stockInfoBackButton = new JButton("Back");
		JButton buyStockButton = new JButton("Buy Stock");
		JButton buyStockConfirmButton = new JButton("Confirm Purchase");
		JButton buyStockBackButton = new JButton("Back");
		JButton sellStockButton = new JButton("Sell Stock");
		JButton sellStockConfirmButton = new JButton("Confirm Sale");
		JButton sellStockBackButton = new JButton("Back");
		JButton withdrawButton = new JButton("Withdraw Funds");
		JButton withdrawConfirmButton = new JButton("Confirm Withdrawal");
		JButton withdrawBackButton = new JButton("Back");
		JButton depositButton = new JButton("Deposit Funds");
		JButton depositConfirmButton = new JButton("Confirm Deposit");
		JButton depositBackButton = new JButton("Back");
		JButton transactionsBackButton = new JButton("Back");
		
		//Centering buttons
		signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUpBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchStocksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		fundsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		fundsBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountInfoBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchStocksBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountInfoEditButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		editAccountInfoConfirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		editAccountInfoBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyStockButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyStockConfirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyStockBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellStockButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellStockConfirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellStockBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		withdrawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		withdrawConfirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		withdrawBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		depositButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		depositConfirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		depositBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Creating text fields
		JTextField usernameField = new JTextField(15);
		usernameField.setMaximumSize(usernameField.getPreferredSize());
		JTextField passwordField = new JTextField(15);
		passwordField.setMaximumSize(passwordField.getPreferredSize());
		JTextField searchBarField = new JTextField(15);
		searchBarField.setMaximumSize(searchBarField.getPreferredSize());
		
		//Centering text fields
		usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchBarField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//*****************************************************************************************
		//Adding labels, buttons, and text fields to panels with proper spacing
		//*****************************************************************************************
		
		loginPanel.add(Box.createRigidArea(new Dimension(0, 150)));
		loginPanel.add(login);
		loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		loginPanel.add(username);
		loginPanel.add(usernameField);
		loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		loginPanel.add(password);
		loginPanel.add(passwordField);
		loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		loginPanel.add(signInButton);
		loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		loginPanel.add(signUpButton);
		
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		signUpPanel.add(signUp);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(createAccountButton);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(signUpBackButton);
		
		homePanel.add(Box.createRigidArea(new Dimension(0, 100)));
		homePanel.add(home);
		homePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		homePanel.add(accountInfoButton);
		homePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		homePanel.add(searchStocksButton);
		homePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		homePanel.add(fundsButton);
		homePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		homePanel.add(transactionsButton);
		homePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		homePanel.add(logoutButton);
		
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		accountInfoPanel.add(accountInfo);
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		accountInfoPanel.add(accountInfoEditButton);
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		accountInfoPanel.add(accountInfoBackButton);
		
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		editAccountInfoPanel.add(editAccountInfo);
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		editAccountInfoPanel.add(editAccountInfoConfirmButton);
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		editAccountInfoPanel.add(editAccountInfoBackButton);
		
		searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		searchPanel.add(search);
		searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		searchPanel.add(searchBarField);
		searchPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		searchPanel.add(searchButton);
		searchPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		searchPanel.add(stockInfoButton);
		searchPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		searchPanel.add(searchStocksBackButton);
		
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		stockInfoPanel.add(stockInfo);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		stockInfoPanel.add(buyStockButton);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(sellStockButton);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoBackButton);
		
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		buyStockPanel.add(buyStock);
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		buyStockPanel.add(buyStockConfirmButton);
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buyStockPanel.add(buyStockBackButton);
		
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		sellStockPanel.add(sellStock);
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		sellStockPanel.add(sellStockConfirmButton);
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sellStockPanel.add(sellStockBackButton);
		
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		fundsPanel.add(manageFunds);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		fundsPanel.add(withdrawButton);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(depositButton);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(fundsBackButton);
		
		withdrawPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		withdrawPanel.add(withdrawFunds);
		withdrawPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		withdrawPanel.add(withdrawConfirmButton);
		withdrawPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		withdrawPanel.add(withdrawBackButton);
		
		depositPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		depositPanel.add(depositFunds);
		depositPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		depositPanel.add(depositConfirmButton);
		depositPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		depositPanel.add(depositBackButton);
		
		transactionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		transactionsPanel.add(transactions);
		transactionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		transactionsPanel.add(transactionsBackButton);
		
		//*****************************************************************************************
		//Adding panels to CardLayout
		//*****************************************************************************************
		
		cards.add(loginPanel, "1");
		cards.add(signUpPanel, "2");
		cards.add(homePanel, "3");
		cards.add(accountInfoPanel, "4");
		cards.add(editAccountInfoPanel, "5");
		cards.add(searchPanel, "6");
		cards.add(stockInfoPanel, "7");
		cards.add(buyStockPanel, "8");
		cards.add(sellStockPanel, "9");
		cards.add(fundsPanel, "10");
		cards.add(withdrawPanel, "11");
		cards.add(depositPanel, "12");
		cards.add(transactionsPanel, "13");
        
		//Add CardLayout to ContentPane
		getContentPane().add(cards);
		
		//*****************************************************************************************
		//Assigning functionalities to menu buttons
		//*****************************************************************************************
		
		goHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == goHome) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});
		
		viewAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewAccount) {
					c1.show(cards, "4"); //switch to account info
				}
			}
		});
		
		editAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editAccount) {
					c1.show(cards, "5"); //switch to edit account info
				}
			}
		});
		
		searchStocks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == searchStocks) {
					c1.show(cards, "6"); //switch to search stocks
				}
			}
		});
		
		myFunds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == myFunds) {
					c1.show(cards, "10"); //switch to manage funds
				}
			}
		});
		
		withdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == withdraw) {
					c1.show(cards, "11"); //switch to withdraw
				}
			}
		});
		
		deposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == deposit) {
					c1.show(cards, "12"); //switch to deposit
				}
			}
		});
		
		myTransactions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == myTransactions) {
					c1.show(cards, "13"); //switch to transactions
				}
			}
		});
		
		signOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signOut) {
					c1.show(cards, "1"); //switch to login
					menuBar.setVisible(false); //prevent use of menu bar when not logged in
				}
			}
		});
		
		//*****************************************************************************************
		//Assigning functionalities to buttons
		//*****************************************************************************************
		
		signInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signInButton) {
					c1.show(cards, "3"); //switch to home
					menuBar.setVisible(true); //prevent use of menu bar when not logged in
				}
			}
		});
		
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signUpButton) {
					c1.show(cards, "2"); //switch to signup
				}
			}
		});
		
		signUpBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signUpBackButton) {
					c1.show(cards, "1"); //switch to login
				}
			}
		});
		
		createAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == createAccountButton) {
					c1.show(cards, "1"); //switch to login
				}
			}
		});
		
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == logoutButton) {
					c1.show(cards, "1"); //switch the login
					menuBar.setVisible(false); //prevent the use of menu bar when not logged in
				}
			}
		});
		
		accountInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == accountInfoButton) {
					c1.show(cards, "4"); //switch to account info
				}
			}
		});
		
		accountInfoEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == accountInfoEditButton) {
					c1.show(cards, "5"); //switch to account info edit
				}
			}
		});
		
		editAccountInfoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editAccountInfoBackButton) {
					c1.show(cards, "4"); //switch to account info
				}
			}
		});
		
		editAccountInfoConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editAccountInfoConfirmButton) {
					c1.show(cards, "4"); //switch to account info
				}
			}
		});
		
		accountInfoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == accountInfoBackButton) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});
		
		searchStocksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == searchStocksButton) {
					c1.show(cards, "6"); //switch to search stocks
				}
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == searchButton) {
					//Blank for now
				}
			}
		});
		
		stockInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == stockInfoButton) {
					c1.show(cards, "7"); //switch to stock info
				}
			}
		});
		
		stockInfoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == stockInfoBackButton) {
					c1.show(cards, "6"); //switch to search stocks
				}
			}
		});
		
		searchStocksBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == searchStocksBackButton) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});
		
		fundsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == fundsButton) {
					c1.show(cards, "10"); //switch to funds
				}
			}
		});
		
		fundsBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == fundsBackButton) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});
		
		transactionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == transactionsButton) {
					c1.show(cards, "13"); //switch to transactions
				}
			}
		});
		
		transactionsBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == transactionsBackButton) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});
		
		buyStockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() ==buyStockButton) {
					c1.show(cards, "8"); //switch to buy stock
				}
			}
		});
		
		buyStockConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buyStockConfirmButton) {
					c1.show(cards, "7"); //switch to stock info
				}
			}
		});
		
		buyStockBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buyStockBackButton) {
					c1.show(cards, "7"); //switch to stock info
				}
			}
		});
		
		sellStockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sellStockButton) {
					c1.show(cards, "9"); //switch to sell stock
				}
			}
		});
		
		sellStockConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sellStockConfirmButton) {
					c1.show(cards, "7"); //switch to stock info
				}
			}
		});
		
		sellStockBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sellStockBackButton) {
					c1.show(cards, "7"); //switch to stock info
				}
			}
		});
		
		withdrawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == withdrawButton) {
					c1.show(cards, "11"); //switch to withdraw
				}
			}
		});
		
		withdrawConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == withdrawConfirmButton) {
					c1.show(cards, "10"); //switch to funds
				}
			}
		});
		
		withdrawBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == withdrawBackButton) {
					c1.show(cards, "10"); //switch to funds
				}
			}
		});
		
		depositButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == depositButton) {
					c1.show(cards, "12"); //switch to deposit
				}
			}
		});
		
		depositConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == depositConfirmButton) {
					c1.show(cards, "10"); //switch to funds
				}
			}
		});
		
		depositBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == depositBackButton) {
					c1.show(cards, "10"); //switch to funds
				}
			}
		});
    }
}
