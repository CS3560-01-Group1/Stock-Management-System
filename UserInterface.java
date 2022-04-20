import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

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
		JMenu homeMenu = new JMenu("Stock Portfolio");
		menuBar.add(homeMenu);
		JMenuItem goHome = new JMenuItem("My Stock Portfolio");
		homeMenu.add(goHome);
		
		JMenu accountMenu = new JMenu("Account Information");
		menuBar.add(accountMenu);
		JMenuItem viewAccount = new JMenuItem("View Account Information");
		accountMenu.add(viewAccount);
		JMenuItem editAccount = new JMenuItem("Edit Account Information");
		accountMenu.add(editAccount);
		
		JMenu ordersMenu = new JMenu("Orders");
		menuBar.add(ordersMenu);
		JMenuItem tradeStocks = new JMenuItem("Trade Stocks");
		ordersMenu.add(tradeStocks);
		
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
		JPanel signUp2Panel = new JPanel();
		signUp2Panel.setLayout(new BoxLayout(signUp2Panel, BoxLayout.Y_AXIS));
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
		
		//Creating labels with some having bold font
		Font titleFont = new Font("Verdana", Font.BOLD, 15);
		JLabel login = new JLabel("Login");
		login.setFont(titleFont);
		JLabel username = new JLabel("Username");
		JLabel password = new JLabel("Password");
		JLabel signUp = new JLabel("Sign Up");
		signUp.setFont(titleFont);
		JLabel signUp2 = new JLabel("Sign Up"); //for 2nd page of sign up
		signUp2.setFont(titleFont);
		JLabel home = new JLabel("Stock Portfolio");
		home.setFont(titleFont);
		JLabel homePortfolio = new JLabel("Your portfolio is empty.");
		JLabel accountInfo = new JLabel("Account Information");
		accountInfo.setFont(titleFont);
		JLabel editAccountInfo = new JLabel("Edit Account Information");
		editAccountInfo.setFont(titleFont);
		JLabel search = new JLabel("Trade Stocks");
		search.setFont(titleFont);
		JLabel stockInfo = new JLabel("Stock Information");
		stockInfo.setFont(titleFont);
		JLabel stockInfoName = new JLabel("Stock Name: ");
		JLabel stockInfoDescription = new JLabel("Stock Description: ");
		JLabel stockInfoTrend = new JLabel("Stock Trend: ");
		JLabel stockInfoPrice = new JLabel("Stock Price: ");
		JLabel stockInfoAvailable = new JLabel("Shares Available: ");
		JLabel buyStock = new JLabel("Buy Stocks");
		buyStock.setFont(titleFont);
		JLabel sellStock = new JLabel("Sell Stocks");
		sellStock.setFont(titleFont);
		JLabel manageFunds = new JLabel("Manage Funds");
		manageFunds.setFont(titleFont);
		JLabel manageFundsBalance = new JLabel("Balance: ");
		JLabel withdrawFunds = new JLabel("Withdraw Funds");
		withdrawFunds.setFont(titleFont);
		JLabel depositFunds = new JLabel("Deposit Funds");
		depositFunds.setFont(titleFont);
		JLabel transactions = new JLabel("Past Transactions");
		transactions.setFont(titleFont);
		JLabel transactionsText = new JLabel("No transactions to show at the moment.");
		JLabel creationUsername = new JLabel("Username");
		JLabel creationPassword = new JLabel("Password");
		JLabel creationFullName = new JLabel("Full Name");
		JLabel creationEmail = new JLabel("Email Address");
		JLabel creationPhoneNumber = new JLabel("Phone Number");
		JLabel creationSSN = new JLabel("Social Security Number");
		JLabel creationStreetAddress = new JLabel("Street Address");
		JLabel creationCity = new JLabel("City");
		JLabel creationState = new JLabel("State");
		JLabel creationZipCode = new JLabel("Zip/Postal Code");
		JLabel creationCreditCardInfo = new JLabel("Credit Card Number");
		JLabel accountUsername = new JLabel("Username: ");
		JLabel accountPassword = new JLabel("Password: ");
		JLabel accountFullName = new JLabel("Full Name: ");
		JLabel accountEmail = new JLabel("Email: ");
		JLabel accountPhoneNumber = new JLabel("Phone Number: ");
		JLabel accountSSN = new JLabel("Social Security Number: ");
		JLabel accountStreetAddress = new JLabel("Street Address: ");
		JLabel accountCity = new JLabel("City: ");
		JLabel accountState = new JLabel("State: ");
		JLabel accountZipCode = new JLabel("Zip/Postal Code: ");
		JLabel accountCreditCardInfo = new JLabel("Credit Card Number: ");
		
		//Centering labels
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		password.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUp.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUp2.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		creationUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationFullName.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationPhoneNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationSSN.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationStreetAddress.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationCity.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationState.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationZipCode.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationCreditCardInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountFullName.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountPhoneNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountSSN.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountStreetAddress.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountCity.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountState.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountZipCode.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountCreditCardInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		homePortfolio.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoName.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoTrend.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoAvailable.setAlignmentX(Component.CENTER_ALIGNMENT);
		manageFundsBalance.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsText.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Creating buttons for functions and navigation
		JButton signInButton = new JButton("Sign In");
		JButton signUpButton = new JButton("Sign Up");
		JButton signUpNextButton = new JButton("Next");
		JButton createAccountButton = new JButton("Create Account");
		JButton signUpBackButton = new JButton("Back");
		JButton signUp2BackButton = new JButton("Back"); //For 2nd page of sign up
		JButton fundsBackButton = new JButton("Back");
		JButton accountInfoBackButton = new JButton("Back");
		JButton searchStocksBackButton = new JButton("Back");
		JButton accountInfoEditButton = new JButton("Edit Information");
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
		JButton editLoginCredentialsButton = new JButton("Edit Login Credentials");
		JButton editPersonalInformationButton = new JButton("Edit Personal Information");
		JButton editAddressButton = new JButton("Edit Address");
		JButton editCreditCardInfoButton = new JButton("Edit Credit Card Information");
		
		//Centering buttons
		signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUpNextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUpBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		signUp2BackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		fundsBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountInfoBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchStocksBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountInfoEditButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		editLoginCredentialsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		editPersonalInformationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		editAddressButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		editCreditCardInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Creating text fields, some with specific formats
		JTextField usernameField = new JTextField(15);
		usernameField.setMaximumSize(usernameField.getPreferredSize());
		JTextField passwordField = new JTextField(15);
		passwordField.setMaximumSize(passwordField.getPreferredSize());
		JTextField searchBarField = new JTextField(15);
		searchBarField.setMaximumSize(searchBarField.getPreferredSize());
		JTextField creationUsernameField = new JTextField(15);
		creationUsernameField.setMaximumSize(creationUsernameField.getPreferredSize());
		creationUsernameField.setHorizontalAlignment(JFormattedTextField.CENTER);
		JTextField creationPasswordField = new JTextField(15);
		creationPasswordField.setMaximumSize(creationPasswordField.getPreferredSize());
		creationPasswordField.setHorizontalAlignment(JFormattedTextField.CENTER);
		JTextField creationFullNameField = new JTextField(15);
		creationFullNameField.setMaximumSize(creationFullNameField.getPreferredSize());
		creationFullNameField.setHorizontalAlignment(JFormattedTextField.CENTER);
		JTextField creationEmailField = new JTextField(15);
		creationEmailField.setMaximumSize(creationEmailField.getPreferredSize());
		creationEmailField.setHorizontalAlignment(JFormattedTextField.CENTER);
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("(###)-###-####");
			mask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JFormattedTextField creationPhoneNumberField = new JFormattedTextField(mask);
		creationPhoneNumberField.setColumns(15);
		creationPhoneNumberField.setMaximumSize(creationPhoneNumberField.getPreferredSize());
		creationPhoneNumberField.setHorizontalAlignment(JFormattedTextField.CENTER);
		try {
			mask = new MaskFormatter("###-##-####");
			mask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JFormattedTextField creationSSNField = new JFormattedTextField(mask);
		creationSSNField.setColumns(15);
		creationSSNField.setMaximumSize(creationSSNField.getPreferredSize());
		creationSSNField.setHorizontalAlignment(JFormattedTextField.CENTER);
		JTextField creationStreetAddressField = new JTextField(15);
		creationStreetAddressField.setMaximumSize(creationStreetAddressField.getPreferredSize());
		creationStreetAddressField.setHorizontalAlignment(JFormattedTextField.CENTER);
		JTextField creationCityField = new JTextField(15);
		creationCityField.setMaximumSize(creationCityField.getPreferredSize());
		creationCityField.setHorizontalAlignment(JFormattedTextField.CENTER);
		try {
			mask = new MaskFormatter("UU");
			mask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JFormattedTextField creationStateField = new JFormattedTextField(mask);
		creationStateField.setColumns(15);
		creationStateField.setMaximumSize(creationStateField.getPreferredSize());
		creationStateField.setHorizontalAlignment(JFormattedTextField.CENTER);
		try {
			mask = new MaskFormatter("#####");
			mask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JFormattedTextField creationZipCodeField = new JFormattedTextField(mask);
		creationZipCodeField.setColumns(15);
		creationZipCodeField.setMaximumSize(creationZipCodeField.getPreferredSize());
		creationZipCodeField.setHorizontalAlignment(JFormattedTextField.CENTER);
		try {
			mask = new MaskFormatter("####-####-####-####");
			mask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JFormattedTextField creationCreditCardInfoField = new JFormattedTextField(mask);
		creationCreditCardInfoField.setColumns(15);
		creationCreditCardInfoField.setMaximumSize(creationCreditCardInfoField.getPreferredSize());
		creationCreditCardInfoField.setHorizontalAlignment(JFormattedTextField.CENTER);
		
		//Centering text fields
		usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchBarField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationUsernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationFullNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationEmailField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationPhoneNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationSSNField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationStreetAddressField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationCityField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationStateField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationZipCodeField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationCreditCardInfoField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
		signUpPanel.add(creationUsername);
		signUpPanel.add(creationUsernameField);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(creationPassword);
		signUpPanel.add(creationPasswordField);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		signUpPanel.add(signUpNextButton);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(signUpBackButton);

		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 20)));
		signUp2Panel.add(signUp2);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationFullName);
		signUp2Panel.add(creationFullNameField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationEmail);
		signUp2Panel.add(creationEmailField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationPhoneNumber);
		signUp2Panel.add(creationPhoneNumberField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationSSN);
		signUp2Panel.add(creationSSNField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationStreetAddress);
		signUp2Panel.add(creationStreetAddressField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationCity);
		signUp2Panel.add(creationCityField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationState);
		signUp2Panel.add(creationStateField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationZipCode);
		signUp2Panel.add(creationZipCodeField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationCreditCardInfo);
		signUp2Panel.add(creationCreditCardInfoField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 20)));
		signUp2Panel.add(createAccountButton);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(signUp2BackButton);
		
		homePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		homePanel.add(home);
		homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		homePanel.add(homePortfolio);
		
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		accountInfoPanel.add(accountInfo);
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		accountInfoPanel.add(accountFullName);
		accountInfoPanel.add(accountUsername);
		accountInfoPanel.add(accountPassword);
		accountInfoPanel.add(accountEmail);
		accountInfoPanel.add(accountPhoneNumber);
		accountInfoPanel.add(accountSSN);
		accountInfoPanel.add(accountStreetAddress);
		accountInfoPanel.add(accountCity);
		accountInfoPanel.add(accountState);
		accountInfoPanel.add(accountZipCode);
		accountInfoPanel.add(accountCreditCardInfo);
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		accountInfoPanel.add(accountInfoEditButton);
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		accountInfoPanel.add(accountInfoBackButton);
		
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		editAccountInfoPanel.add(editAccountInfo);
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		editAccountInfoPanel.add(editLoginCredentialsButton);
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		editAccountInfoPanel.add(editPersonalInformationButton);
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		editAccountInfoPanel.add(editAddressButton);
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		editAccountInfoPanel.add(editCreditCardInfoButton);
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
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
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoName);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoDescription);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoTrend);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoPrice);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoAvailable);
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
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(manageFundsBalance);
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
		transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		transactionsPanel.add(transactionsText);
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
		cards.add(signUp2Panel, "14");
        
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
		
		//Switchs to search stocks screen. Loads all stocks symbols from the database into a list for user
		tradeStocks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == tradeStocks) {
					c1.show(cards, "6"); //switch to search stocks
					//Array to store all stockSymbols
					ArrayList<String> stocks = new ArrayList<>();

					//Creating JList
					JList stockList;

					//Creating scroll pane
					JScrollPane scrollStockList;

					try
					{
						Connection connection = Main.getConnection();
						// create the java statement
					
						// execute the query, and get a java resultset
						ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM stockdb.stock");

						// iterate through the java resultset
						while (rs.next())
						{
							stocks.add(rs.getString("stockSymbol"));
						}
						
					}
					catch (Exception ex)
					{
						System.out.println(ex);
					}
					finally
					{
						System.out.println("Query Complete: Selected all stock symbols");
					}

					String[] array = stocks.toArray(new String [0]);
					stockList = new JList(array);
					scrollStockList = new JScrollPane(stockList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					scrollStockList.setPreferredSize(new Dimension(50, 100));
					scrollStockList.setMaximumSize(new Dimension(50, 100));

					searchPanel.removeAll();
					searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));
					searchPanel.add(search);
					searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));
					searchPanel.add(searchBarField);
					searchPanel.add(Box.createRigidArea(new Dimension(0, 5)));
					searchPanel.add(searchButton);
					searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));
					searchPanel.add(scrollStockList);
					searchPanel.add(Box.createRigidArea(new Dimension(0, 30)));
					searchPanel.add(stockInfoButton);
					searchPanel.add(Box.createRigidArea(new Dimension(0, 10)));
					searchPanel.add(searchStocksBackButton);
					searchPanel.revalidate();
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
		//Assigning functionalities to navigation buttons
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

		signUp2BackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signUp2BackButton) {
					c1.show(cards, "1"); //switch to login for 2nd sign up
				}
			}
		});

		signUpNextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signUpNextButton) {
					c1.show(cards, "14"); //switch to 2nd page of sign up
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
		
		accountInfoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == accountInfoBackButton) {
					c1.show(cards, "3"); //switch to home
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
		
		fundsBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == fundsBackButton) {
					c1.show(cards, "3"); //switch to home
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

		//*****************************************************************************************
		//Buttons that update user interface and database depending on user input
		//*****************************************************************************************

		editLoginCredentialsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editLoginCredentialsButton) {
					//Text fields for new username and password
					JTextField field1 = new JTextField(15);
					JTextField field2 = new JTextField(15);

					//An array for the output of the JOptionPane
					Object[] fields = {"Username", field1, "Password", field2};

					JOptionPane.showConfirmDialog(null, fields, "Enter new login credentials.", JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});

		editPersonalInformationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editPersonalInformationButton) {
					//Text fields for new name and email address
					JTextField field1 = new JTextField(15);
					JTextField field2 = new JTextField(15);

					//Formatted textfields for new phone number and ssn
					MaskFormatter mask = null;
					try {
						mask = new MaskFormatter("(###) ###-####");
						mask.setPlaceholderCharacter('_');
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JFormattedTextField field3 = new JFormattedTextField(mask);
					field3.setColumns(15);
					try {
						mask = new MaskFormatter("###-##-####");
						mask.setPlaceholderCharacter('_');
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JFormattedTextField field4 = new JFormattedTextField(mask);
					field4.setColumns(15);
	
					//An array for the output of the JOptionPane
					Object[] fields = {"Full Name", field1, 
									   "Email", field2,
									   "Phone Number", field3,
									   "Social Security Number", field4};
	
					JOptionPane.showConfirmDialog(null, fields, "Enter new personal information.", JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});

		editAddressButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editAddressButton) {
					//Text fields for new streetAddress and city
					JTextField field1 = new JTextField(15);
					JTextField field2 = new JTextField(15);

					//Formatted textfields for new state and zip/postal code
					MaskFormatter mask = null;
					try {
						mask = new MaskFormatter("UU");
						mask.setPlaceholderCharacter('_');
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JFormattedTextField field3 = new JFormattedTextField(mask);
					field3.setColumns(15);
					try {
						mask = new MaskFormatter("#####");
						mask.setPlaceholderCharacter('_');
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JFormattedTextField field4 = new JFormattedTextField(mask);
					field4.setColumns(15);
	
					//An array for the output of the JOptionPane
					Object[] fields = {"Street Address", field1, 
									   "City", field2,
									   "State", field3,
									   "Zip/Postal Code", field4};
	
					JOptionPane.showConfirmDialog(null, fields, "Enter new address.", JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});

		editCreditCardInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editCreditCardInfoButton) {
					//Formatted textfield for new credit card info
					MaskFormatter mask = null;
					try {
						mask = new MaskFormatter("####-####-####-####");
						mask.setPlaceholderCharacter('_');
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JFormattedTextField field1 = new JFormattedTextField(mask);
					field1.setColumns(15);
	
					//An array for the output of the JOptionPane
					Object[] fields = {"Credit Card Number", field1};
	
					JOptionPane.showConfirmDialog(null, fields, "Enter new credit card information.", JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});

		//Filter search results
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == searchButton) {
					
					if(!searchBarField.getText().equals("")) {
						//Array to store all stockSymbols
						ArrayList<String> stocks = new ArrayList<>();

						//Creating JList
						JList stockList;

						//Creating scroll pane
						JScrollPane scrollStockList;

						try
						{
							Connection connection = Main.getConnection();
							// create the java statement
						
							// execute the query, and get a java resultset
							ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM stock WHERE stockSymbol LIKE '%" 
																						+ searchBarField.getText().toUpperCase() + "%';");

							// iterate through the java resultset
							while (rs.next())
							{
								stocks.add(rs.getString("stockSymbol"));
							}
							
						}
						catch (Exception ex)
						{
							System.out.println(ex);
						}

						String[] array = stocks.toArray(new String [0]);
						stockList = new JList(array);
						scrollStockList = new JScrollPane(stockList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scrollStockList.setPreferredSize(new Dimension(50, 100));
						scrollStockList.setMaximumSize(new Dimension(50, 100));

						searchPanel.removeAll();
						searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));
						searchPanel.add(search);
						searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));
						searchPanel.add(searchBarField);
						searchPanel.add(Box.createRigidArea(new Dimension(0, 5)));
						searchPanel.add(searchButton);
						searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));
						searchPanel.add(scrollStockList);
						searchPanel.add(Box.createRigidArea(new Dimension(0, 30)));
						searchPanel.add(stockInfoButton);
						searchPanel.add(Box.createRigidArea(new Dimension(0, 10)));
						searchPanel.add(searchStocksBackButton);
						searchPanel.revalidate();
					}
				}
			}
		});

    }
}
