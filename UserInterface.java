import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.MaskFormatter;

public class UserInterface extends JFrame{
    
    private CardLayout c1;
    private User curUser;
    
    private JPanel cards = new JPanel();
    
    //instantiate panels
	private JPanel loginPanel = new JPanel();
	private JPanel signUpPanel = new JPanel();
	private	JPanel signUp2Panel = new JPanel();
	private	JPanel homePanel = new JPanel();
	private JPanel accountInfoPanel = new JPanel();
	private JPanel editAccountInfoPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private JPanel stockInfoPanel = new JPanel();
	private JPanel buyStockPanel = new JPanel();
	private JPanel sellStockPanel = new JPanel();
	private JPanel fundsPanel = new JPanel();
	private	JPanel withdrawPanel = new JPanel();
	private	JPanel depositPanel = new JPanel();
	private JPanel transactionsPanel = new JPanel();
	
	//Creating text fields, some with specific formats
	private JTextField usernameField = new JTextField(15);
	private JTextField passwordField = new JTextField(15);
	private JTextField searchBarField = new JTextField(15);
	private JTextField withdrawAmountField = new JTextField(5);
	private JTextField depositAmountField = new JTextField(5);
	private JTextField buyStockAmountField = new JTextField(5);
	private JTextField sellStockAmountField = new JTextField(5);
	
	//Creating menu bar
	private JMenuBar menuBar = new JMenuBar(); 
	
	//Creating menus
	private JMenu homeMenu = new JMenu("Stock Portfolio");
	private JMenuItem goHome = new JMenuItem("My Stock Portfolio");
	
	private JMenu accountMenu = new JMenu("Account Information");
	private JMenuItem viewAccount = new JMenuItem("View Account Information");
	private JMenuItem editAccount = new JMenuItem("Edit Account Information");
	
	private JMenu ordersMenu = new JMenu("Orders");
	private JMenuItem tradeStocks = new JMenuItem("Trade Stocks");
	
	private JMenu fundsMenu = new JMenu("Manage Funds");
	private JMenuItem myFunds = new JMenuItem("My Funds");
	
	private JMenu transactionsMenu = new JMenu("Transactions");
	private JMenuItem myTransactions = new JMenuItem("My Transactions");
	
	private JMenu logoffMenu = new JMenu("Log Off");
	private JMenuItem signOut = new JMenuItem("Sign Out");

	//Creating text areas for the porfolio and transaction screens
	private JTextArea transactionsText = new JTextArea("No transactions to show at the moment.");
	private JTextArea homePortfolio = new JTextArea("Your portfolio is empty.");

	//Creating scroll panes for the portfolio and transaction screens
	private JScrollPane transactionsPane = new JScrollPane(transactionsText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
															JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JScrollPane portfolioPane = new JScrollPane(homePortfolio, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
															JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	public UserInterface() {
		//Setting title and frame size
		setTitle("Stock Management System");
		setSize(600, 600);
		
		//Creating CardLayout
		c1 = new CardLayout();
		cards.setLayout(c1);
				
		//*****************************************************************************************
		//Creating a menu at the top of the window
		//*****************************************************************************************
		
		//Creating menu bar
		this.setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		//Creating menus
		menuBar.add(homeMenu);
		homeMenu.add(goHome);
		
		menuBar.add(accountMenu);
		accountMenu.add(viewAccount);
		accountMenu.add(editAccount);
		
		menuBar.add(ordersMenu);
		ordersMenu.add(tradeStocks);
		
		menuBar.add(fundsMenu);
		fundsMenu.add(myFunds);
		
		menuBar.add(transactionsMenu);
		transactionsMenu.add(myTransactions);
		
		menuBar.add(logoffMenu);
		logoffMenu.add(signOut);
		
		//*****************************************************************************************
		//Creating panels for each screen and setting the layout
		//*****************************************************************************************
		
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
		signUp2Panel.setLayout(new BoxLayout(signUp2Panel, BoxLayout.Y_AXIS));
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
		accountInfoPanel.setLayout(new BoxLayout(accountInfoPanel, BoxLayout.Y_AXIS));
		editAccountInfoPanel.setLayout(new BoxLayout(editAccountInfoPanel, BoxLayout.Y_AXIS));
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		stockInfoPanel.setLayout(new BoxLayout(stockInfoPanel, BoxLayout.Y_AXIS));
		buyStockPanel.setLayout(new BoxLayout(buyStockPanel, BoxLayout.Y_AXIS));
		sellStockPanel.setLayout(new BoxLayout(sellStockPanel, BoxLayout.Y_AXIS));
		fundsPanel.setLayout(new BoxLayout(fundsPanel, BoxLayout.Y_AXIS));
		withdrawPanel.setLayout(new BoxLayout(withdrawPanel, BoxLayout.Y_AXIS));
		depositPanel.setLayout(new BoxLayout(depositPanel, BoxLayout.Y_AXIS));
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
		JLabel accountInfo = new JLabel("Account Information");
		accountInfo.setFont(titleFont);
		JLabel editAccountInfo = new JLabel("Edit Account Information");
		editAccountInfo.setFont(titleFont);
		JLabel search = new JLabel("Trade Stocks");
		search.setFont(titleFont);
		JLabel stockInfo = new JLabel("Stock Information");
		stockInfo.setFont(titleFont);
		JLabel stockInfoName = new JLabel("Stock Name: ");
		JLabel stockInfoAsk = new JLabel("Ask Price: ");
		JLabel stockInfoBid = new JLabel("Bid Price: ");
		JLabel stockInfoWeek = new JLabel("52-Week: ");
		JLabel stockInfoQuarterly = new JLabel("Quarterly Dividend Percent: ");
		JLabel stockInfoPE = new JLabel("PE Ratio: ");
		JLabel stockInfoTotalShares = new JLabel("Total Shares: ");
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
		JLabel creationUsername = new JLabel("Username");
		JLabel creationPassword = new JLabel("Password");
		JLabel creationFirstName = new JLabel("First Name");
		JLabel creationLastName = new JLabel("Last Name");
		JLabel creationEmail = new JLabel("Email Address (Optional)");
		JLabel creationPhoneNumber = new JLabel("Phone Number (Optional");
		JLabel creationSSN = new JLabel("Social Security Number");
		JLabel creationStreetAddress = new JLabel("Street Address");
		JLabel creationCity = new JLabel("City");
		JLabel creationState = new JLabel("State");
		JLabel creationZipCode = new JLabel("Zip/Postal Code");
		JLabel accountID = new JLabel("User ID: ");
		JLabel accountUsername = new JLabel("Username: ");
		JLabel accountPassword = new JLabel("Password: ");
		JLabel accountFirstName = new JLabel("First Name: ");
		JLabel accountLastName = new JLabel("Last Name: ");
		JLabel accountEmail = new JLabel("Email: ");
		JLabel accountPhoneNumber = new JLabel("Phone Number: ");
		JLabel accountSSN = new JLabel("Social Security Number: ");
		JLabel accountAddress = new JLabel("Address: ");
		JLabel bankName = new JLabel("Bank Name: ");
		JLabel bankAccountNumber = new JLabel("Bank Account Number: ");
		JLabel bankRoutingNumber = new JLabel("Bank Routing Number: ");
		JLabel withdrawAmount = new JLabel("Amount to Withdraw: ");
		JLabel depositAmount = new JLabel("Amount to Deposit: ");
		JLabel buyStockAmountName = new JLabel("Stock Name: ");
		JLabel buyStockAmountAvailable = new JLabel("Total Shares Available: ");
		JLabel buyStockAmountPrice = new JLabel("Current Price Per Share: ");
		JLabel buyStockAmount = new JLabel("Amount of Shares to Purchase:");
		JLabel sellStockAmountName = new JLabel("Stock Name: ");
		JLabel sellStockAmountAvailable = new JLabel("Total Shares Owned: ");
		JLabel sellStockAmountPrice = new JLabel("Current Price Per Share: ");
		JLabel sellStockAmount = new JLabel("Amount of Shares to Sell:");
		
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
		creationFirstName.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationLastName.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationPhoneNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationSSN.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationStreetAddress.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationCity.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationState.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationZipCode.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountID.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountFirstName.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountLastName.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountPhoneNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountSSN.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountAddress.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoName.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoAsk.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoBid.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoWeek.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoQuarterly.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoPE.setAlignmentX(Component.CENTER_ALIGNMENT);
		stockInfoTotalShares.setAlignmentX(Component.CENTER_ALIGNMENT);
		manageFundsBalance.setAlignmentX(Component.CENTER_ALIGNMENT);
		bankName.setAlignmentX(Component.CENTER_ALIGNMENT);
		bankAccountNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
		bankRoutingNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
		withdrawAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
		depositAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyStockAmountName.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyStockAmountAvailable.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyStockAmountPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
		buyStockAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellStockAmountName.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellStockAmountAvailable.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellStockAmountPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
		sellStockAmount.setAlignmentX(Component.CENTER_ALIGNMENT);

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
		JButton accountInfoDeleteButton = new JButton("Delete Account");
		JButton editAccountInfoBackButton = new JButton("Back");
		JButton searchButton = new JButton("Search");
		JButton stockInfoButton = new JButton("View Stock");
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
		JButton deleteExpiredOrdersButton = new JButton("Archive All Expired Orders");
		
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
		accountInfoDeleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		deleteExpiredOrdersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Creating text fields, some with specific formats
		usernameField.setMaximumSize(usernameField.getPreferredSize());
		passwordField.setMaximumSize(passwordField.getPreferredSize());
		searchBarField.setMaximumSize(searchBarField.getPreferredSize());
		JTextField creationUsernameField = new JTextField(15);
		creationUsernameField.setMaximumSize(creationUsernameField.getPreferredSize());
		creationUsernameField.setHorizontalAlignment(JFormattedTextField.CENTER);
		JTextField creationPasswordField = new JTextField(15);
		creationPasswordField.setMaximumSize(creationPasswordField.getPreferredSize());
		creationPasswordField.setHorizontalAlignment(JFormattedTextField.CENTER);
		JTextField creationFirstNameField = new JTextField(15);
		creationFirstNameField.setMaximumSize(creationFirstNameField.getPreferredSize());
		creationFirstNameField.setHorizontalAlignment(JFormattedTextField.CENTER);
		JTextField creationLastNameField = new JTextField(15);
		creationLastNameField.setMaximumSize(creationFirstNameField.getPreferredSize());
		creationLastNameField.setHorizontalAlignment(JFormattedTextField.CENTER);
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
		JTextField bankNameField = new JTextField(15);
		bankNameField.setMaximumSize(bankNameField.getPreferredSize());
		bankNameField.setHorizontalAlignment(JFormattedTextField.CENTER);
		try {
			mask = new MaskFormatter("##########");
			mask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JFormattedTextField bankAccountNumberField = new JFormattedTextField(mask);
		bankAccountNumberField.setColumns(15);
		bankAccountNumberField.setMaximumSize(bankAccountNumberField.getPreferredSize());
		bankAccountNumberField.setHorizontalAlignment(JFormattedTextField.CENTER);
		try {
			mask = new MaskFormatter("#########");
			mask.setPlaceholderCharacter('_');
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JFormattedTextField bankRoutingNumberField = new JFormattedTextField(mask);
		bankRoutingNumberField.setColumns(15);
		bankRoutingNumberField.setMaximumSize(bankRoutingNumberField.getPreferredSize());
		bankRoutingNumberField.setHorizontalAlignment(JFormattedTextField.CENTER);
		withdrawAmountField.setMaximumSize(withdrawAmountField.getPreferredSize());
		depositAmountField.setMaximumSize(depositAmountField.getPreferredSize());
		buyStockAmountField.setMaximumSize(buyStockAmountField.getPreferredSize());
		sellStockAmountField.setMaximumSize(sellStockAmountField.getPreferredSize());
		
		//Centering text fields
		usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchBarField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationUsernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationFirstNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationLastNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationEmailField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationPhoneNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationSSNField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationStreetAddressField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationCityField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationStateField.setAlignmentX(Component.CENTER_ALIGNMENT);
		creationZipCodeField.setAlignmentX(Component.CENTER_ALIGNMENT);
		bankAccountNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
		bankRoutingNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
		withdrawAmountField.setAlignmentX(Component.CENTER_ALIGNMENT);
		depositAmountField.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Creating scroll panes for the portfolio and transaction screens
		transactionsPane.setPreferredSize(new Dimension(250, 300));
		transactionsPane.setMaximumSize(new Dimension(250, 300));
		portfolioPane.setPreferredSize(new Dimension(250, 300));
		portfolioPane.setMaximumSize(new Dimension(250, 300));

		//*****************************************************************************************
		//Creating a default list model for list of stocks
		//*****************************************************************************************
		DefaultListModel<String> stockListModel = new DefaultListModel<>();
		try
		{
			Connection connection = Main.getConnection();
			// create the java statement

			// execute the query, and get a java resultset
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM stockdb.stock");

			// iterate through the java resultset
			while (rs.next())
			{
				stockListModel.addElement(rs.getString("stockSymbol"));
			}
			
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}

		final JList<String> stockList = new JList<>(stockListModel);
		final JScrollPane scrollStockList = new JScrollPane(stockList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollStockList.setPreferredSize(new Dimension(50, 100));
		scrollStockList.setMaximumSize(new Dimension(50, 100));

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
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(creationFirstName);
		signUpPanel.add(creationFirstNameField);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(creationLastName);
		signUpPanel.add(creationLastNameField);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		signUpPanel.add(signUpNextButton);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(signUpBackButton);

		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 20)));
		signUp2Panel.add(signUp2);
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
		signUp2Panel.add(creationEmail);
		signUp2Panel.add(creationEmailField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(creationPhoneNumber);
		signUp2Panel.add(creationPhoneNumberField);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 20)));
		signUp2Panel.add(createAccountButton);
		signUp2Panel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUp2Panel.add(signUp2BackButton);
		
		homePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		homePanel.add(home);
		homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		homePanel.add(portfolioPane);
		
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		accountInfoPanel.add(accountInfo);
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		accountInfoPanel.add(accountID);
		accountInfoPanel.add(accountFirstName);
		accountInfoPanel.add(accountLastName);
		accountInfoPanel.add(accountUsername);
		accountInfoPanel.add(accountPassword);
		accountInfoPanel.add(accountEmail);
		accountInfoPanel.add(accountPhoneNumber);
		accountInfoPanel.add(accountSSN);
		accountInfoPanel.add(accountAddress);
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		accountInfoPanel.add(accountInfoEditButton);
		accountInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		accountInfoPanel.add(accountInfoDeleteButton);
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
		editAccountInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		editAccountInfoPanel.add(editAccountInfoBackButton);
		
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
		
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		stockInfoPanel.add(stockInfo);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoName);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoAsk);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoBid);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoWeek);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoQuarterly);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoPE);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoTotalShares);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		stockInfoPanel.add(buyStockButton);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(sellStockButton);
		stockInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		stockInfoPanel.add(stockInfoBackButton);
		
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		buyStockPanel.add(buyStock);
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		buyStockPanel.add(buyStockAmountName);
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buyStockPanel.add(buyStockAmountAvailable);
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buyStockPanel.add(buyStockAmountPrice);
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		buyStockPanel.add(buyStockAmount);
		buyStockPanel.add(buyStockAmountField);
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		buyStockPanel.add(buyStockConfirmButton);
		buyStockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buyStockPanel.add(buyStockBackButton);
		
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		sellStockPanel.add(sellStock);
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		sellStockPanel.add(sellStockAmountName);
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sellStockPanel.add(sellStockAmountAvailable);
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sellStockPanel.add(sellStockAmountPrice);
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		sellStockPanel.add(sellStockAmount);
		sellStockPanel.add(sellStockAmountField);
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		sellStockPanel.add(sellStockConfirmButton);
		sellStockPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		sellStockPanel.add(sellStockBackButton);
		
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		fundsPanel.add(manageFunds);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(manageFundsBalance);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		fundsPanel.add(bankName);
		fundsPanel.add(bankNameField);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(bankAccountNumber);
		fundsPanel.add(bankAccountNumberField);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(bankRoutingNumber);
		fundsPanel.add(bankRoutingNumberField);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(withdrawButton);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(depositButton);
		fundsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fundsPanel.add(fundsBackButton);
		
		withdrawPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		withdrawPanel.add(withdrawFunds);
		withdrawPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		withdrawPanel.add(withdrawAmount);
		withdrawPanel.add(withdrawAmountField);
		withdrawPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		withdrawPanel.add(withdrawConfirmButton);
		withdrawPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		withdrawPanel.add(withdrawBackButton);
		
		depositPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		depositPanel.add(depositFunds);
		depositPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		depositPanel.add(depositAmount);
		depositPanel.add(depositAmountField);
		depositPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		depositPanel.add(depositConfirmButton);
		depositPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		depositPanel.add(depositBackButton);
		
		transactionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		transactionsPanel.add(transactions);
		transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		transactionsPanel.add(transactionsPane);
		transactionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		transactionsPanel.add(deleteExpiredOrdersButton);
		transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
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
		
		//ActionListener initilization for stockInfoButton, allows this to be called and repeated with a timer
		ActionListener stockInfoRefresher = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
				
                //Retrieves all of the selected stock's information from database
                if (!stockList.isSelectionEmpty()) {
                    //Instantiating variables to hold values
                    String stockName = "";
                    float askPrice = 0;
                    float bidPrice = 0;
                    float week = 0;
                    float quarterlyDividendPercent = 0;
                    float peRatio = 0;
                    int totalShares = 0;
                    
                    try{
                        // create the java statement
                        Connection connection = Main.getConnection();
                        
                        // execute the query, and get a java resultset
                        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM stock where stockSymbol = '" 
                                                                                    + stockList.getSelectedValue() + "'");
                        rs.next();
                        
                        //Storing the values from the database into the variables
                        stockName = rs.getString("stockSymbol");
                        askPrice = rs.getFloat("ask");
                        bidPrice = rs.getFloat("bid");
                        week = rs.getFloat("52_Week");
                        quarterlyDividendPercent = rs.getFloat("quarterlyDividendPerc");
                        peRatio = rs.getFloat("PEratio");
                        totalShares = rs.getInt("totalShares");
                    }
                    catch (Exception ex){
                        System.out.println(ex);
                    }

                    //Updating the stock information panel before loading it
                    stockInfoName.setText("Name: " + stockName);
                    stockInfoAsk.setText("Ask Price: " + askPrice);
                    stockInfoBid.setText("Bid Price: " + bidPrice);
                    stockInfoWeek.setText("52-Week: " + week);
                    stockInfoQuarterly.setText("Quarterly Dividend Percent: " + quarterlyDividendPercent);
                    stockInfoPE.setText("PE Ratio: " + peRatio);
                    stockInfoTotalShares.setText("Total Shares: " + totalShares);
                    stockInfoPanel.revalidate();
                }
            }
        };

		ActionListener buyStockRefresher = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Loads the stock's information that is relevant to buying stocks
				try
				{
					Connection connection = Main.getConnection();
					// create the java statement
					
					// execute the query, and get a java resultset
					ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM stock where stockSymbol = '" 
																					+ stockList.getSelectedValue() + "'");
					rs.next();

					//Updates buy stock panel before loading it
					buyStockAmountName.setText("Stock Name: " + rs.getString("stockSymbol"));
					buyStockAmountAvailable.setText("Total Shares Available: " + rs.getFloat("totalShares"));
					buyStockAmountPrice.setText("Price Per Share: " + rs.getFloat("bid"));
					buyStockPanel.revalidate();
				}
				catch (Exception ex)
				{
					System.out.println(ex);
				}
			}
		};

		ActionListener sellStockRefresher = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				sellStockConfirmButton.setEnabled(true);

				//Gets and loads the information of the stock the user wishes to sell before loading the panel
				try
				{
					Connection connection = Main.getConnection();
					// create the java statement

					// execute the query, and get a java resultset
					ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM stock where stockSymbol = '" 
																					+ stockList.getSelectedValue() + "'");
					rs.next();

					//Instantiating variables to hold the values of the query
					String stockSym = rs.getString("stockSymbol");
					double askPrice = rs.getDouble("ask");
					
					//Attempts to show the stock information and how many shares the user owns
					try
					{
						String query = "SELECT `stockOwner`, sharesOwned FROM usersShareTotal WHERE `stockOwner` = " + "\"" + curUser.getID() + "_" + stockSym + "\""; 			
						rs = connection.createStatement().executeQuery(query);
						rs.next();
						
						sellStockAmountName.setText("Stock Name: " + stockSym);
						sellStockAmountAvailable.setText("Your Shares: " + rs.getString("sharesOwned"));
						sellStockAmountPrice.setText("Value Per Share: " + askPrice);
						sellStockPanel.revalidate();
					}
					//If the user does not have any shares of that stock, then display 0 and disable the sell button
					catch (Exception ex) 
					{
						sellStockAmountName.setText("Stock Name: " + stockSym);
						sellStockAmountAvailable.setText("Your Shares: " + 0);
						sellStockAmountPrice.setText("Value Per Share: " + askPrice);
						sellStockConfirmButton.setEnabled(false); //can't sell if own nothing
						sellStockPanel.revalidate();
					}
				}
				catch (Exception ex)
				{
					System.out.println(ex);
				}
			}
		};
		
		//Creating timers that allow the methods above to be repeated and controlled through other action performers (buttons)
		//Timers needs to be created only once and outside the button action listener, otherwise a timer will be created with 
		//every button press. 
		Timer stockTimer = new Timer(3000, stockInfoRefresher);
		Timer buyTimer = new Timer(3000, buyStockRefresher);
		Timer sellTimer = new Timer(3000, sellStockRefresher);

		//Switches to stock portfolio screen
		goHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == goHome) {
					displayStockPortfolio();
				}
			}
		});
		
		//Switches to view account information screen
		viewAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewAccount) {
					try {
						//Gets user info by using current user's ID
						ResultSet rs = User.getAccountInfo(curUser.getID());
						rs.next();

						//Updates account information labels
						accountFirstName.setText("First Name: " + rs.getString("fName"));
						accountLastName.setText("Last Name: " + rs.getString("lName"));
						accountID.setText("User ID: " + rs.getInt("userID"));
						accountUsername.setText("Username: " + rs.getString("username"));
						accountPassword.setText("Password: " + rs.getString("password"));
						accountEmail.setText("Email Address: " + rs.getString("email"));
						accountPhoneNumber.setText("Phone Number: " + rs.getString("phone#"));
						accountSSN.setText("Social Security Number: " + rs.getString("ssn"));
						accountAddress.setText("Address: " + rs.getString("address"));
						accountInfoPanel.revalidate();
					} catch (SQLException ex) {
						System.out.println(ex);
					}
					c1.show(cards, "4"); //switch to account info
				}
			}
		});
		
		//Switches to edit account information screen
		editAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editAccount) {
					c1.show(cards, "5"); //switch to edit account info
				}
			}
		});
		
		//Switches to search stocks screen. Loads all stocks symbols from the database into a list for user
		tradeStocks.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == tradeStocks) {
					c1.show(cards, "6"); //switch to search stocks
					stockListModel.clear();

					//Attempts to load all stocks from the database's stock table and displays in a list
					try
					{
						Connection connection = Main.getConnection();
						// create the java statement
					
						// execute the query, and get a java resultset
						ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM stockdb.stock");

						// iterate through the java resultset
						while (rs.next())
						{
							//Adds stock to the stock list
							stockListModel.addElement(rs.getString("stockSymbol"));
						}	
					}
					catch (Exception ex)
					{
						System.out.println(ex);
					}

					//Updates the panel
					searchPanel.revalidate();
				}
			}
		});
		
		//Switch to manage funds screen
		myFunds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == myFunds) {
					try {
						// Gets user's account info in order to access the user's balance
						ResultSet rs = User.getAccountInfo(curUser.getID());
						rs.next();

						// Get user balance
						manageFundsBalance.setText("Balance: " + rs.getDouble("balance"));
						
						//Clears the text fields before loading the panel
						bankAccountNumberField.setValue(null);
						bankRoutingNumberField.setValue(null);
						bankNameField.setText(null);
						
						//Updates the panel
						fundsPanel.revalidate();
					}
					catch (Exception ex) {
						System.out.println(ex);
					}
					c1.show(cards, "10"); //switch to manage funds
				}
			}
		});
		
		//Switches to transactions screen
		myTransactions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == myTransactions) {
					displayTransactionsPage();
				}
			}
		});
		
		
		//Switches to login screen removes menu bar
		signOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signOut) {
					c1.show(cards, "1"); //switch to login
					menuBar.setVisible(false); //prevent use of menu bar when not logged in

					//Clears the text fields before loading panel
					usernameField.setText("");
					passwordField.setText("");

					//Clears the curUser object's data
					curUser.logoff();
				}
			}
		});
		
		//*****************************************************************************************
		//Assigning functionalities to navigation buttons
		//*****************************************************************************************
		
		//Login panel's sign in button functionality
		signInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signInButton) {
					//Initializes a new user object using the username and password
					curUser = new User(usernameField.getText(), passwordField.getText());

					//Loads the user's existing stock portfolio information, if any
					displayStockPortfolio();
				}
			}
		});
		
		//Login panel's sign up button functionality
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signUpButton) {
					c1.show(cards, "2"); //switch to signup
				}
			}
		});
		
		//Sign up panel's back button functionality
		signUpBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signUpBackButton) {
					c1.show(cards, "1"); //switch to login
				}
			}
		});

		//Sign up panel's next button functionality
		signUpNextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signUpNextButton) {
					
					boolean usernameValid = true;

					//Check if username is already in use
					try
					{
						Connection connection = Main.getConnection();
						// create the java statement
					
						// execute the query, and get a java resultset
						ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM user");

						// iterate through the java resultset
						while (rs.next())
						{
							//If username is already in use, the username is NOT valid
							if (rs.getString("username").equals(creationUsernameField.getText()))
								usernameValid = false;
						}	
					}
					catch (Exception ex)
					{
						System.out.println(ex);
					}
					
					//Make sure the text fields are not empty
					if (creationUsernameField.getText().equals("") || 
						creationPasswordField.getText().equals("") ||
						creationFirstNameField.getText().equals("") ||
						creationLastNameField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill in all fields.");
					}
					//Make sure username is unique
					else if (usernameValid) {
						c1.show(cards, "14"); //switch to 2nd page of sign up
					}
					//Output error if either of the above is not satisfied
					else
						JOptionPane.showMessageDialog(null, "Username already in use.\n" +
															"Please enter a different one");
				}
			}
		});
		
		//Second page of sign up panel's create account button functionality
		createAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == createAccountButton) {
					//Check if any required fields are empty
					if (creationSSNField.getText().equals("") ||
						creationStreetAddressField.getText().equals("") ||
						creationCityField.getText().equals("") ||
						creationStateField.getText().equals("") ||
						creationZipCodeField.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Please fill in all non-optional fields.");
					//If all required fields are filled out create user account
					else {
						String address = creationStreetAddressField.getText() + ", " 
										+ creationCityField.getText() + ", "
										+ creationStateField.getText() + ", "
										+ creationZipCodeField.getText();
						User.createUserAccount(creationUsernameField.getText(),
												creationPasswordField.getText(),
												creationSSNField.getText(),
												address, 
												creationFirstNameField.getText(), 
												creationLastNameField.getText(), 
												creationEmailField.getText(), 
												creationPhoneNumberField.getText());
						JOptionPane.showMessageDialog(null, "Account created successfully!");
						c1.show(cards, "1"); //switch to login
					}
				}
			}
		});
		
		//Second page of the sign up panel's back button
		signUp2BackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == signUp2BackButton) {
					c1.show(cards, "1"); //switch to login for 2nd sign up
				}
			}
		});

		//Account information panel's edit account button functionality
		accountInfoEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == accountInfoEditButton) {
					c1.show(cards, "5"); //switch to account info edit
				}
			}
		});

		//Account information panel's back button functionality
		accountInfoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == accountInfoBackButton) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});
		
		//Edit account information panel's back button functionality
		editAccountInfoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editAccountInfoBackButton) {
				
					try {
						//Loads account information using the user's ID
						ResultSet rs = User.getAccountInfo(curUser.getID());
						rs.next();

						//Updates the account information panel before loading it
						accountFirstName.setText("First Name: " + rs.getString("fName"));
						accountLastName.setText("Last Name: " + rs.getString("lName"));
						accountID.setText("User ID: " + rs.getInt("userID"));
						accountUsername.setText("Username: " + rs.getString("username"));
						accountPassword.setText("Password: " + rs.getString("password"));
						accountEmail.setText("Email Address: " + rs.getString("email"));
						accountPhoneNumber.setText("Phone Number: " + rs.getString("phone#"));
						accountSSN.setText("Social Security Number: " + rs.getString("ssn"));
						accountAddress.setText("Address: " + rs.getString("address"));
						accountInfoPanel.revalidate();
	
					} catch (SQLException ex) {
						System.out.println(ex);
					}
					c1.show(cards, "4"); //switch to account info
				}
			}
		});
		
		//Trade stocks panel's back button functionality
		searchStocksBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == searchStocksBackButton) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});

		
		
		//Trade stocks panel's view stock information button functionality
		stockInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == stockInfoButton) {
					stockTimer.setRepeats(true);
					stockTimer.setInitialDelay(0);
					stockTimer.start();

					if (stockList.isSelectionEmpty())
                    	JOptionPane.showMessageDialog(null, "Please select a stock.");
					else
						c1.show(cards, "7"); //switch to stock info
				}
			}
		});
		
		//Stock information panel's back button functionality
		stockInfoBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == stockInfoBackButton) {
					stockTimer.stop();
					c1.show(cards, "6"); //switch to search stocks
				}
			}
		});
		
		//Manage funds panel's back button functionality
		fundsBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == fundsBackButton) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});
		
		//Transactions panel's back button functionality
		transactionsBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == transactionsBackButton) {
					c1.show(cards, "3"); //switch to home
				}
			}
		});
		
		//Transactions panel's delete expired orders button functionality
		deleteExpiredOrdersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == deleteExpiredOrdersButton)
				{
					//Deletes expired orders shown from the transaction tab and updates panel
					Order O = new Order();
					O.deleteAllExpiredOrders(curUser.getID());
					displayTransactionsPage();
				}
	
			}
			
		});
		
		//Stock information panel's buy stock button functionality
		buyStockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() ==buyStockButton) {
					buyTimer.setRepeats(true);
					buyTimer.setInitialDelay(0);
					buyTimer.start();

					c1.show(cards, "8"); //switch to buy stock
				}
			}
		});
		
		//Buy stock panel's confirm purchase button functionality
		buyStockConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buyStockConfirmButton) {
					try{
						// create the java statement
						Connection connection = Main.getConnection();
						
						//Gets stock information
						ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `stock` where stockSymbol = '" 
																						+ stockList.getSelectedValue() + "'");
						rs.next();
	
						//Gets user's information to get access to the user's balance
						ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM `user` WHERE `userID` = " + curUser.getID());
						rs1.next();
	
						//Calculate total cost of purchase
						float total = Float.parseFloat(buyStockAmountField.getText()) * rs.getFloat("bid");
						
						//Instantiating variables to make an Order object later on
						String stockName = rs.getString("stockSymbol");
						int orderType = 0; 
						double shareAmnt = Double.parseDouble(buyStockAmountField.getText());
						
						//Making a string to display in the pop-up dialog/window with all the order's information
						String reviewOrder = "Review Order:\n";
						reviewOrder += "Stock Name: " + stockName + "\n";
						reviewOrder += "Order Type: Buy\n";
						reviewOrder += "Shares: " + shareAmnt + "\n";
						reviewOrder += "Price per Share: " + rs.getFloat("bid") + "\n\n";
						reviewOrder += "Current Balance: " + rs1.getFloat("balance") + "\n";
						reviewOrder += "Total Price: " + total;
						
						//Show order review window
						int n = JOptionPane.showConfirmDialog(null, reviewOrder, "Confirm Purchase", JOptionPane.OK_CANCEL_OPTION);
	
						//After confirm, place open order in database
						if (n == JOptionPane.OK_OPTION) { 
							//Create new open order
							Order buyOrder = new Order();
							buyOrder.newOrder(curUser.getID(), stockName, orderType, shareAmnt);
	
							//Market delay timer
							Main.marketDelay.schedule(new MarketDelay(buyStockAmountField.getText(), rs.getString("stockSymbol"), buyOrder.getTransactionID()), 0*1000);
							//After delay, if order is not interrupted, it is completed!
						}
					}
					catch (Exception ex)
					{
						//If invalid amount is entered into amount text field, display error pop up dialog
						JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
						System.out.println(ex);
					}
				}
			}
		});
		
		//Buy stock panel's back button functionality
		buyStockBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buyStockBackButton) {
					c1.show(cards, "7"); //switch to stock info
				}
			}
		});
		
		//Stock information panel's sell stock button
		sellStockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sellStockButton) {
					sellTimer.setRepeats(true);
					sellTimer.setInitialDelay(0);
					sellTimer.start();

					c1.show(cards, "9"); //switch to sell stock
				}
			}
		});
		
		//Sell stock's confirm sale button functionality
		sellStockConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					Connection connection = Main.getConnection();
					// create the java statement
					
					//Gets desired stock's information
					ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `stock` where stockSymbol = '" 
																					+ stockList.getSelectedValue() + "'");
					rs.next();

					//Gets user information to access their balance
					ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM `user` WHERE `userID` = " + curUser.getID());
					rs1.next();

					//Calculate total profit
					float total = Float.parseFloat(sellStockAmountField.getText()) * rs.getFloat("ask");
					
					//Instantiating variables to make an Order object later on
					String stockName = rs.getString("stockSymbol");
					int orderType = 1; 
					double shareAmnt = Double.parseDouble(sellStockAmountField.getText());
					
					//Creates a string for the order details
					String reviewOrder = "Review Order:\n";
					reviewOrder += "Stock Name: " + stockName + "\n";
					reviewOrder += "Order Type: Buy\n";
					reviewOrder += "Shares: " + shareAmnt + "\n";
					reviewOrder += "Value per Share: " + rs.getFloat("ask") + "\n\n";
					reviewOrder += "Current Balance: " + rs1.getFloat("balance") + "\n";
					reviewOrder += "Total Value: " + total;
					
					//Shows order review window
					int n = JOptionPane.showConfirmDialog(null, reviewOrder, "Confirm Sale", JOptionPane.OK_CANCEL_OPTION);

					//After confirm, place open order in database
					if (n == JOptionPane.OK_OPTION) {

						//create new open order
						Order sellOrder = new Order();
						sellOrder.newOrder(curUser.getID(), stockName, orderType, shareAmnt);
						
						//Market delay timer
						Main.marketDelay.schedule(new MarketDelay(sellStockAmountField.getText(), rs.getString("stockSymbol"), sellOrder.getTransactionID()), 0*1000);
						//After delay, if order is not interrupted, it is completed!
					}
				}
				catch (Exception ex)
				{
					//If invalid amount is entered, display error pop up dialog
					JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
					System.out.println(ex);
				}
			}
		});
		
		//Sell stock panel's back button functionality
		sellStockBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sellStockBackButton) {
					c1.show(cards, "7"); //switch to stock info
				}
			}
		});
		
		//Manage funds panel's withdraw button functionality
		withdrawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == withdrawButton) {
					//Make sure the bank information fields are not empty
					if (bankNameField.getText().equals("") || 
							bankAccountNumberField.getText().equals("__________") ||
							bankRoutingNumberField.getText().equals("_________")) {

						//Pop up error if any of the fields are empty
						JOptionPane.showMessageDialog(null, "Please enter bank information.");
					}
					else {
						c1.show(cards, "11"); //switch to withdraw
					}
				}
			}
		});
		
		//withdraw panel's confirm withdraw button functionality
		withdrawConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == withdrawConfirmButton) {
					//Adds to the user's balance
					//Subtracts from the user's balance
					try {
						float newBalance;

						// Gets user information to gain access to their balance
						ResultSet rs = User.getAccountInfo(curUser.getID());
						rs.next();

						// Make sure the user has enough money to deposit
						if (Float.parseFloat(withdrawAmountField.getText()) > rs.getFloat("balance")) {

							//Displays error pop up if balance is insufficient
							JOptionPane.showMessageDialog(null, "You cannot withdraw more than your current balance!");
						}
						else {
							// Calculates the user's new balance and stores it in the database
							newBalance = User.updateBalance(curUser.getID(), -Float.parseFloat(withdrawAmountField.getText()));

							//Records a monetary transaction in the database
							MonetaryTransaction withdrawTransaction = new MonetaryTransaction();
							withdrawTransaction.newMonetaryTransaction(curUser.getID(), bankRoutingNumberField.getText(), 
														bankNameField.getText(), bankAccountNumberField.getText(), 
														"WITHDRAW", Float.parseFloat(withdrawAmountField.getText()));

							//Updates balance label to new balance
							manageFundsBalance.setText("Balance: " + newBalance);
							
							//Clears bank information text fields
							bankAccountNumberField.setValue(null);
							bankRoutingNumberField.setValue(null);
							bankNameField.setText(null);
							
							//Updates manage fund panel before loading it
							fundsPanel.revalidate();
							c1.show(cards, "10"); //switch to funds
						}
					}
					catch (Exception ex) {
						//Diplays error pop up dialog if invalid amount was entered
						JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
						System.out.println(ex);
					}
				}
			}
		});
			
		
		//Withdraw panel's back button functionality
		withdrawBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == withdrawBackButton) {
					
					
					c1.show(cards, "10"); //switch to funds
				}
			}
		});
		
		//Manage funds panel's deposit button functionality
		depositButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == depositButton) {
					//Check if any bank information text fields are empty
					if (bankNameField.getText().equals("") ||
							bankAccountNumberField.getText().equals("__________") ||
							bankRoutingNumberField.getText().equals("_________")) {

						//If any are empty, display error pop up dialog
						JOptionPane.showMessageDialog(null, "Please enter bank information.");
					}
					else {
						c1.show(cards, "12"); //switch to deposit
					}
				}
			}
		});
		
		//deposit panel's confirm deposit button functionality
		depositConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == depositConfirmButton) {

					//Subtracts from the user's balance
					//Adds to the user's balance
					try {
						//Calculates the user's new balance and stores it in the database
						float newBalance;
						newBalance = User.updateBalance(curUser.getID(), Float.parseFloat(depositAmountField.getText()));

						//Records a monetary transaction
						MonetaryTransaction depositTransaction = new MonetaryTransaction();
						depositTransaction.newMonetaryTransaction(curUser.getID(), bankRoutingNumberField.getText(), 
														bankNameField.getText(), bankAccountNumberField.getText(), 
														"DEPOSIT", Float.parseFloat(depositAmountField.getText()));

						//Updates the balance label to display the user's new balance
						manageFundsBalance.setText("Balance: " + newBalance);
						
						//Clears the bank information text fields
						bankAccountNumberField.setValue(null);
						bankRoutingNumberField.setValue(null);
						bankNameField.setText(null);
						
						//Updates the manage funds panel
						fundsPanel.revalidate();

						c1.show(cards, "10"); //switch to funds
					}
					catch (Exception ex) {
						//Displays error dialog when user enters invalid amount
						JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
						System.out.println(ex);
					}
				}
			}
		});
		
		
		//Deposit panel's back button functionality
		depositBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == depositBackButton) {
					c1.show(cards, "10"); //switch to funds
				}
			}
		});

		//*****************************************************************************************
		//Other button functionalities
		//*****************************************************************************************

		//Edit account information panel's edit login credentials button functionality
		editLoginCredentialsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editLoginCredentialsButton) {
					
					//Text fields for new username and password to be displayed in pop up window
					JTextField field1 = new JTextField(15);
					JTextField field2 = new JTextField(15);

					//An array for the output of the pop up window
					Object[] fields = {"Username", field1, "Password", field2};

					//Displays pop up window with the text fields created above
					int n = JOptionPane.showConfirmDialog(null, fields, "Enter new login credentials.", JOptionPane.OK_CANCEL_OPTION);

					//After confirming, updates login credentials if input was valid
					if (n == JOptionPane.OK_OPTION) {
						try
						{
							boolean validUsername = true;
							Connection connection = Main.getConnection();
							// create the java statement
						
							// Gets all user information and current user's information
							ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `user`");
							ResultSet rs1 = connection.createStatement().executeQuery("SELECT * FROM `user` WHERE `userID` = " + curUser.getID());
							rs1.next();

							// Iterate through all users to ensure the usernames do not match
							while (rs.next())
							{
								if (rs.getString("username").equals(field1.getText())) {
									validUsername = false;
								}
							}
							//Check if username is the same as current
							if (rs1.getString("username").equals(field1.getText())) {
								validUsername = true;
							}

							//Check if both fields are filled in
							if (field1.getText().equals("") || field2.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Please fill in all fields.");
							}
							//Check if username is in use
							else if (!validUsername) {
								JOptionPane.showMessageDialog(null, "Username is already in use.\nPlease try another username.");
							}
							//Update password and username
							else {
								User.updateLoginCredentials(curUser.getID(), field1.getText(), field2.getText());
								accountUsername.setText("Username: " + field1.getText());
								accountPassword.setText("Password: " + field2.getText());
								usernameField.setText(field1.getText());
								passwordField.setText(field2.getText());
								accountInfoPanel.revalidate();	
								curUser.login(usernameField.getText(), passwordField.getText());
							}
							
						}
						catch (Exception ex)
						{
							System.out.println(ex);
						}
					}
					
				}
			}
		});

		//Edit account information panel's edit personal information button functionality
		editPersonalInformationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editPersonalInformationButton) {
					
					//Text fields for new first name, last name, and email address to be displayed in pop up dialog
					JTextField field1 = new JTextField(15);
					JTextField field2 = new JTextField(15);
					JTextField field3 = new JTextField(15);

					//Formatted textfields for new phone number and ssn to be displayed in pop up dislog
					MaskFormatter mask = null;
					try {
						mask = new MaskFormatter("(###)-###-####");
						mask.setPlaceholderCharacter('_');
						mask.setValueContainsLiteralCharacters(false);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JFormattedTextField field4 = new JFormattedTextField(mask);
					field3.setColumns(15);
					try {
						mask = new MaskFormatter("###-##-####");
						mask.setPlaceholderCharacter('_');
						mask.setValueContainsLiteralCharacters(false);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JFormattedTextField field5 = new JFormattedTextField(mask);
					field4.setColumns(15);
	
					//An array for the output of the pop up dialog
					Object[] fields = {"First Name", field1, 
									   "Last Name", field2,
									   "Email (Optional)", field3,
									   "Phone Number (Optional)", field4,
									   "Social Security Number", field5};
	
					//Displays the pop up dialog with all the text fields created above
					int n = JOptionPane.showConfirmDialog(null, fields, "Enter new personal information.", JOptionPane.OK_CANCEL_OPTION);

					//After confirm, updates user's personal information if input is valid
					if (n == JOptionPane.OK_OPTION) {
						try
						{
							//Check if required fields are filled in
							if (field1.getText().equals("") || field2.getText().equals("")
									|| field5.getText().equals("")) {

								//Displays error pop up if not all required fields are filled
								JOptionPane.showMessageDialog(null, "Please fill in all fields.");
							}
							//Updates personal information
							else {
								User.updatePersonalInformation(curUser.getID(), field1.getText(), 
															   field2.getText(), field3.getText(), 
															   field4.getText(), field5.getText());	
								accountFirstName.setText("First Name: " + field1.getText());
								accountLastName.setText("Last Name: " + field2.getText());
								accountEmail.setText("Email: " + field3.getText());
								accountPhoneNumber.setText("Phone Number: " + field4.getText());
								accountSSN.setText("Social Security Number: " + field5.getText());
								accountInfoPanel.revalidate();			
								
							}	
						}
						catch (Exception ex)
						{
							System.out.println(ex);
						}
					}
				}
			}
		});

		//Edit account information panel's edit address button functionality
		editAddressButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editAddressButton) {
					
					//Text fields for new streetAddress and city to be displayed in pop up window
					JTextField field1 = new JTextField(15);
					JTextField field2 = new JTextField(15);

					//Formatted textfields for new state and zip/postal code to be displayed in pop up window
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
	
					//An array for the output of the pop up window
					Object[] fields = {"Street Address", field1, 
									   "City", field2,
									   "State", field3,
									   "Zip/Postal Code", field4};
	
					//Displays pop up window with the text fields created above
					int n = JOptionPane.showConfirmDialog(null, fields, "Enter new address.", JOptionPane.OK_CANCEL_OPTION);

					//After confirm, updates user's address if input is valid
					if (n == JOptionPane.OK_OPTION) {
						try
						{
							//Check if required fields are filled in
							if (field1.getText().equals("") || field2.getText().equals("")
									|| field3.getText().equals("") || field4.getText().equals("")) {

								//Displays error pop up window if not all fields are filled
								JOptionPane.showMessageDialog(null, "Please fill in all fields.");
							}
							//Create address string and update user's address
							else {
								String address = "" + field1.getText() + ", " 
													+ field2.getText() + ", " 
													+ field3.getText() + ", " 
													+ field4.getText();
								accountAddress.setText("Address: " + address);
								User.updateAddress(curUser.getID(), address);
								accountInfoPanel.revalidate();			
							}	
						}
						catch (Exception ex)
						{
							System.out.println(ex);
						}
					}
				}
			}
		});

		//Account information panel's delete account button functionality
		accountInfoDeleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == accountInfoDeleteButton) {
				
					//Displays a pop up window to confirm account deletion
					int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", 
																				"Delete Account", JOptionPane.YES_NO_OPTION);

					//After confirm, deletes all orders and monetary transactions, before deleting the account and signing out
					if (n == JOptionPane.YES_OPTION) {
						try
						{
							//Deletes all the user's order transactions
							Order.deleteAllUserOrders(curUser.getID());

							//Deletes all the user's monetary transactions
							MonetaryTransaction.deleteAllUserMonTransactions(curUser.getID());

							//Deletes the user's account
							User.deleteAccount(curUser.getID());

							//Signs out
							curUser.logoff();
							menuBar.setVisible(false);

							//Clears login credential text fields
							usernameField.setText("");
							passwordField.setText("");

							c1.show(cards, "1"); //switch to login
						}
						catch (Exception ex)
						{
							System.out.println(ex);
						}
					}
				}
			}
		});

		//Trade stocks panel's search button functionality
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == searchButton) {
				
					//Filters stocks depending on search bar field input

					//Check that search bar field is not empty
					if(!searchBarField.getText().equals("")) {

						//Clears whatever stocks were already in the list
						stockListModel.clear();

						try
						{
							Connection connection = Main.getConnection();
							// create the java statement
						
							// Searches for matching stocks
							ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `stock` WHERE `stockSymbol` LIKE '%" 
																						+ searchBarField.getText().toUpperCase() + "%';");

							// Iterates through all matches and adds them to the list
							while (rs.next())
							{
								stockListModel.addElement(rs.getString("stockSymbol"));
							}
							
						}
						catch (Exception ex)
						{
							System.out.println(ex);
						}

						//Updates the trade stocks panel
						searchPanel.revalidate();
					}
					//Displays all stocks if search bar field is empty
					else {
						stockListModel.clear();

						try
						{
							Connection connection = Main.getConnection();
							// create the java statement
						
							// Gets all stock information
							ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `stock`");

							// Iterates through and adds all stocks to the list
							while (rs.next())
							{
								stockListModel.addElement(rs.getString("stockSymbol"));
							}
							
						}
						catch (Exception ex)
						{
							System.out.println(ex);
						}
					}
				}
			}
		});
		
}

	//*****************************************************************************************
	//Helper methods
	//*****************************************************************************************

	ActionListener portfolioRefresher = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			
				// Gets the user's portfolio information using their user ID
				ResultSet rs = User.viewPortfolio(curUser.getID());
				
				//Prints the following if the user owns no stocks
				if (!rs.isBeforeFirst()) {
					homePortfolio.setText("Your porfolio is empty.");
				}
				//Prints the user's portfolio
				else {
					String portfolio = "";
					String temp;

					//Establishes connection to database to search stocks ask and bid prices
					Connection connection = Main.getConnection();
					ResultSet rs1;

					//Creates a string that contains portfolio information
					while (rs.next()) {
						temp = rs.getString("stockOwner");
						temp = temp.substring(temp.length() - 3); //Gets the last 3 chars of string, which is the stockSymbol
						portfolio += "Stock Name: " + temp + "\n";
						portfolio += "Shares Owned: " + rs.getFloat("sharesOwned") + "\n";
						rs1 = connection.createStatement().executeQuery("SELECT * FROM `stock` WHERE `stockSymbol` = '" + temp + "'");
						rs1.next();
						portfolio += "Ask Price: " + rs1.getFloat("ask") + "\n";
						portfolio += "Bid Price: " + rs1.getFloat("bid") + "\n\n";
					}

					//Updates the portfolio text
					homePortfolio.setText(portfolio);
				}

				//Updates the portfolio/home panel
				homePanel.revalidate();
				
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
		}	
	};

	Timer portfolioTimer = new Timer(3000, portfolioRefresher);

	//Method to display the user's stock portfolio
	public void displayStockPortfolio()
	{
		//Confirms the user's login credentials
		if (User.loginConfirmation(usernameField.getText(), passwordField.getText())) {
			portfolioTimer.setRepeats(true);
			portfolioTimer.setInitialDelay(0);
			portfolioTimer.start();

			c1.show(cards, "3"); //switch to home
			menuBar.setVisible(true); //shows menu bar when user logs in
		}
		else {
			//Displays error pop up window if login credentials are invalid
			JOptionPane.showMessageDialog(null, "Invalid username or password.");
		}
	}

	//Methods to display the user's transactions
	public void displayTransactionsPage()
	{
		try {

			//Executes search query to get user's transactions
			ResultSet rs =  User.getTransactions(curUser.getID());

			// Checks if result is empty and prints the following if so
			if (!rs.isBeforeFirst()) {
				transactionsText.setText("No transactions to show at the moment.");
			}
			// Otherwise display transactions
			else {
				String transactionsList = "";
				while (rs.next()) {

					//Prints basic transaction information
					transactionsList += "Transaction ID: " + rs.getInt("transactionID") + "\n";
					transactionsList += "Date of Transaction: " + rs.getString("transactionDate") + "\n";
					
					//Prints monetary transaction specific information
					if (rs.getString("orderType") == null) {
						transactionsList += "Bank Name: " + rs.getString("externalBankName") + "\n";
						transactionsList += "Bank Account Number: " + rs.getString("externalBankAct#") + "\n";
						transactionsList += "Bank Routing Number: " + rs.getString("externalBankRoute#") + "\n";
						transactionsList += "Activity Type: " + rs.getString("activityType") + "\n";
						transactionsList += "Amount: " + rs.getFloat("amount") + "\n";
						transactionsList += "\n";
					}
					else {
						//Prints buy order transaction specific information
						if (rs.getInt("orderType") == 0) {
							transactionsList += "Order Type: Buy\n";
							transactionsList += "Stock Type: " + rs.getString("stockSymbol") + "\n";
							transactionsList += "Quantity: " + rs.getFloat("quantity") + "\n";
						}
						//Prints sell order transaction specific information
						else {
							transactionsList += "Order Type: Sell\n";
							transactionsList += "Stock Type: " + rs.getString("stockSymbol") + "\n";
							transactionsList += "Quantity: " + -1 * rs.getFloat("quantity") + "\n";
						}
						//Print order transaction specific information
						transactionsList += "Executed Price: " + rs.getFloat("executedPrice") + "\n";
						if (rs.getInt("orderStatus") == 0)
								transactionsList += "Order Status: Open\n";
						else if (rs.getInt("orderStatus") == 1)
								transactionsList += "Order Status: Completed\n";
						else
								transactionsList += "Order Status: Expired\n";
							transactionsList += "\n";
					}
				}

				//Updates transactions text
				transactionsText.setText(transactionsList);
			}
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

		//Updates transactions panel
		transactionsPanel.revalidate();

		c1.show(cards, "13"); //switch to transactions
	}
}