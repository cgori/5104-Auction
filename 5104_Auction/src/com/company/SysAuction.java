package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import EAuctionSystem.Auction;
import EAuctionSystem.Buyer;
import EAuctionSystem.Item;
import EAuctionSystem.Seller;
import EAuctionSystem.User;
import EAuctionSystem.condition;
import EAuctionSystem.statusType;

//TODO USER VALIDATION ON INPUTS AND CONDITIONALS

public class SysAuction {

	private List<Auction> listOfAuctions = Collections.synchronizedList(new ArrayList<Auction>());
	private List<User> listOfUsers = new ArrayList<User>();
	private Buyer loggedInBuyer;
	private Seller loggedInSeller;
	private Auction auctionloop;
	private User userLog;
	private Scanner r = new Scanner(System.in);
	private int userInterval = 500;
	private int auctionInterval = 200;
	private ValidateAuction w = new ValidateAuction();
	private Item itemloop;

//TODO CHANGE SET METHOD TO StatusType.PENDING IN CONSTRUCT AUCTION FOR LIVE SYSTEM

	public SysAuction() {
		deserialize();

		// LIST OF ALL USERS TESTING

		/*
		 * listOfUsers.add(new Seller("Callum", "Test")); listOfUsers.add(new
		 * Seller("Robert", "Harrison")); listOfUsers.add(new Seller("Callum",
		 * "Goring")); listOfUsers.add(new Seller("Tim", "Bob")); listOfUsers.add(new
		 * Buyer("Dave", "Smite")); listOfUsers.add(new Buyer("Van", "Man"));
		 */

		// LIST OF ALL AUCTIONS TESTING
		listOfAuctions.add(new Auction(2500.75, 2600.00, new Date(System.currentTimeMillis() + 35000L),
				(Seller) listOfUsers.get(0), new Item("Diamonds", condition.NEW, 20), listOfAuctions.size()));
		listOfAuctions.add(new Auction(15.00, 20.00, new Date(System.currentTimeMillis() + 20000L),
				(Seller) listOfUsers.get(2), new Item("Boat", condition.NEW, 21), listOfAuctions.size()));
		listOfAuctions.add(new Auction(3.00, 3.10, new Date(System.currentTimeMillis() + 20000L),
				(Seller) listOfUsers.get(2), new Item("Bike", condition.USED, 22), listOfAuctions.size()));
		listOfAuctions.add(new Auction(1.00, 5.00, new Date(System.currentTimeMillis() + 12000L),
				(Seller) listOfUsers.get(3), new Item("coal", condition.NEW, 23), listOfAuctions.size()));
		listOfAuctions.add(new Auction(0.99, 1.00, new Date(System.currentTimeMillis() + 10000L),
				(Seller) listOfUsers.get(1), new Item("Iron", condition.NEW, 24), listOfAuctions.size()));

		// ADDING BIDS TO AUCTIONS TESTING
		listOfAuctions.get(2).placeBid(0.35, (Buyer) listOfUsers.get(4), new Date(System.currentTimeMillis()));

		// STARTS AUCTION THREADS TO CHECK ACTIVE AUCTIONS
		w.start();
		String select;
		do {
			System.out.println("=============================================================================");
			System.out.println("                          Guest Menu");
			System.out.println("=============================================================================");
			System.out.println("1. Browse Auctions");
			System.out.println("2. Login");
			System.out.println("3. Create Account");
			System.out.println("4. Change update interval");
			System.out.println("5. Quit");
			select = r.nextLine();
			switch (select) {
			case "1":
				browseAuctions();
				break;
			case "2":
				login();
				break;
			case "3":
				createAccount();
				break;
			case "4":
				System.out.println("Select the thread you wish to change the update interval on");
				System.out.println("1. User Updates");
				System.out.println("2. Back");
				select = r.nextLine();

				switch (select) {

				case "1":
					int userInput;
					try {
						System.out.print("Interval (milliseconds):");
						userInput = r.nextInt();

					} catch (Exception e) {
						System.out.println("Input needs to be an integer value.");
						r.nextLine();
						break;
					}
					userInterval = userInput;

					System.err.println("User Update interval is now " + userInterval);
					break;

				case "2":
					break;
				}

			}

		} while (!select.equalsIgnoreCase("5"));
		serialize();
		System.exit(0);
	}

	/**
	 * deserialize is used to read in all users from .ser file and saves into
	 * listOfUsers
	 */
	private void deserialize() {
		try {
			FileInputStream fileIn = new FileInputStream("users.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			listOfUsers = (List<User>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("User class not found");
			c.printStackTrace();
			return;
		}
	}

	/**
	 * Serialize is used to save all data when the program is closed for testing
	 * only the user file will be saved because auction list need current time for
	 * testing
	 */
	private void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream("users.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(listOfUsers);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in user.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	/**
	 * login scans two user inputs username and password which is sent to
	 * verifyLogin()
	 */
	private void login() {
		System.out.println("Please Enter Username: ");
		String userName = r.nextLine();
		System.out.println("Please Enter Password: ");
		String password = r.nextLine();
		verifyLogin(userName, password);
	}

	/**
	 * changes the refresh rate on the threads e.g. some systems might update on an
	 * hourly basis
	 */
	private void changeInterval() {
		System.out.println("Select the thread you wish to change the update interval on");
		System.out.println("1. User Updates");
		System.out.println("2. Auction Validator (requires user to be logged in)");
		String select = r.nextLine();
		int userInput;
		switch (select) {
		case "1":

			try {
				System.out.print("Interval (milliseconds):");
				userInput = r.nextInt();

			} catch (Exception e1) {
				System.out.println("Input needs to be an integer value.");
				r.nextLine();
				break;
			}

			auctionInterval = userInput;
			r.nextLine();
			System.err.println("Auction Validator interval is now " + auctionInterval);
			break;

		case "2":
			try {
				System.out.print("Interval (milliseconds):");
				userInput = r.nextInt();

			} catch (Exception e) {
				System.out.println("Input needs to be an integer value.");
				r.nextLine();
				break;
			}
			userInterval = userInput;
			r.nextLine();
			System.err.println("User Update interval is now " + userInterval);
			break;

		}
	}

	/**
	 * createAccount scans 3 user inputs userName,password and int choice for enum
	 * condition
	 *
	 */
	private void createAccount() {
		System.out.println("Please Enter your username: ");
		String userName = r.nextLine();
		System.out.println("Please Enter your password: ");
		String password = r.nextLine();
		System.out.println("Account Type");
		System.out.println("1. Buyer");
		System.out.println("2. Seller");
		String choice = r.nextLine();
		switch (choice) {
		case "1":
			listOfUsers.add(new Buyer(userName, password));
			break;
		case "2":
			listOfUsers.add(new Seller(userName, password));
			break;
		}
	}

	/**
	 * Loops the list of users and calls check password if true user logs in and
	 * starts a new thread for Userupates
	 * 
	 * @param String userName, password
	 */
	public void verifyLogin(String userName, String password) {
		for (User user : listOfUsers) {
			if (user.checkLogin(userName, password)) {
				UserUpdates updates = new UserUpdates();
				try {
					this.loggedInSeller = (Seller) user;
					this.userLog = this.loggedInSeller;
					this.userLog.setLoggedIn(true);
					updates.start();
					sellerMenu();
				} catch (Exception e) {
					this.loggedInBuyer = (Buyer) user;
					this.userLog = this.loggedInBuyer;
					this.userLog.setLoggedIn(true);
					updates.start();
					buyerMenu();
				}
			}
		}

	}

	/**
	 * When a seller logs in they are allowed to now startAuctions,addItems and
	 * verifyAuctions
	 * 
	 */
	private void sellerMenu() {
		System.out.println("=============================================================================");
		System.out.println("         " + loggedInSeller.getUserName() + " Seller Menu");
		System.out.println("=============================================================================");

		String select;
		do {
			System.out.println("1. Add Item");
			System.out.println("2. Start Auction");
			System.out.println("3. verify Auction");
			System.out.println("4. Change interval");
			System.out.println("5. Logout");
			select = r.nextLine();
			switch (select) {
			case "1":
				newSellerItem();
				break;
			case "2":
				startNewAuction();
				break;
			case "3":
				verifyAuction();
				break;

			case "4":
				changeInterval();
				break;
			}

		} while (!select.equalsIgnoreCase("5"));
		loggedInSeller.setLoggedIn(false);
		loggedInSeller = null;
	}

	/**
	 * verify's a pending auction to allow buyers to bid on the auction
	 */
	private void verifyAuction() {
		if (loggedInSeller.isBlocked()) {
			boolean auctionsAvalibleToVerify = false;

			for (Auction auction : listOfAuctions) {
				if (auction.getWho().equals(loggedInSeller) && auction.getStatus() == statusType.PENDING) {
					System.out.println(auction.getID() + "| " + auction.getItemForSale().getDescription());
					auctionsAvalibleToVerify = true;
				}
			}
			if (auctionsAvalibleToVerify) {
				System.out.println("Please select an auction to verify");
				String choice = r.nextLine();
				if (listOfAuctions.get(Integer.parseInt(choice)).getWho().equals(loggedInSeller)) {
					listOfAuctions.get(Integer.parseInt(choice)).setStatus(statusType.ACTIVE);
					System.err.println("Auction is now listed");
				}
			} else {
				System.err.println("No auctions available");
			}
		}
	}

	/**
	 * Seller picks from a list of his pending items then calls auctionDetails()
	 */
	private void startNewAuction() {
		if (loggedInSeller.getItemsForSale().size() >= 1) {
			System.out.println("Please Select An Item For Sale: ");

			for (Item item : loggedInSeller.getItemsForSale()) {

				System.out.println(item.getID() + "| " + item.getDescription() + " |" + item.getitemCondition());
			}
			String choice = r.nextLine();

			Item newItemAuction = loggedInSeller.pickItem(Integer.parseInt(choice));

			boolean beginAuction = true;
			for (Auction auction : listOfAuctions) {
				if (auction.getItemForSale().equals(newItemAuction) && (auction.getStatus() == statusType.ACTIVE)
						|| auction.getStatus() == statusType.SOLD || auction.isBlocked() == true) {
					beginAuction = false;
				}
			}
			if (beginAuction == true) {
				auctionDetails(newItemAuction);
			} else {
				System.out.println("This item is already up for sale or sold");
			}
		}

	}

	/**
	 * auctionDetails scans the users inputs on startPrice reservePrice and closing
	 * date and then creates a new Auction with the statusType of pending
	 * 
	 * @param newItemAuction
	 */
	private void auctionDetails(Item newItemAuction) {
		System.out.println("Please enter Start Price: ");
		System.out.println("Format 0.00");
		double startPrice = r.nextDouble();
		System.out.println("Please enter Reserve Price: ");
		double reservePrice = r.nextDouble();
		r.nextLine();
		System.out.println("Please enter Closing Date: ");
		System.out.println("Example: 2009-12-31 23:59");
		String stringClosingDate = r.nextLine();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK);
		Date closingDate = null;
		try {
			// Parsing the String
			closingDate = dateFormat.parse(stringClosingDate);
		} catch (ParseException e) {
			System.err.println("Not a valid date format.");
		}
		listOfAuctions.add(new Auction(startPrice, reservePrice, closingDate, loggedInSeller, newItemAuction,
				listOfAuctions.size()));
		System.err.println("Auction is now pending.");
	}

	/**
	 * Scans two inputs String itemDescription and enum condition itemCondition then
	 * creates a new item object and adds to list of user items
	 */
	private void newSellerItem() {
		System.out.println("Please enter Item Desc: ");
		String itemDesc = r.nextLine();
		System.out.println("Please select Item Condition: ");
		System.out.println("1. New");
		System.out.println("2. Used");
		String tempCondition = r.nextLine();
		condition itemCondition = null;
		switch (tempCondition) {
		case "1":
			itemCondition = condition.NEW;
			break;
		case "2":
			itemCondition = condition.USED;
			break;
		}
		loggedInSeller.addItem(itemDesc, itemCondition);
		System.err.println("Item created");
	}

	/**
	 * When a Buyer logs in they are allowed to browseAuctions, placeBid and
	 * BrowseWinnings
	 */
	public void buyerMenu() {
		System.out.println("=============================================================================");
		System.out.println("         " + loggedInBuyer.getUserName() + " Buyer Menu");
		System.out.println("=============================================================================");

		String select;
		do {
			System.out.println("1. Browse Auctions");
			System.out.println("2. Place Bid");
			System.out.println("3. Browse Winnings");
			System.out.println("4. Change interval");
			System.out.println("5. Logout");
			select = r.nextLine();
			switch (select) {
			case "1":
				browseAuctions();
				break;
			case "2":
				browseAuctions(loggedInBuyer);
				break;
			case "3":
				try {
					ArrayList<Auction> listOfWinnings = loggedInBuyer.browseWinnings();
					browseAuctions(listOfWinnings);
				} catch (Exception e) {
					System.out.println(loggedInBuyer.getUserName() + ": Has no winnings");
				}
				break;

			case "4":

				changeInterval();

				System.out.println("Please enter an integer value.");

				break;
			}

		} while (!select.equalsIgnoreCase("5"));
		loggedInBuyer.setLoggedIn(false);
		loggedInBuyer = null;

	}

	/**
	 * Displays all the active Auction's
	 * 
	 */
	public void browseAuctions() {
		boolean check = false;
		System.out.println("==================================================");
		for (Auction auction : listOfAuctions) {
			if (auction.getStatus().equals(statusType.ACTIVE)) {
				check = true;
				System.out.println(auction.getID() + " |" + auction.toString());

			}
		}
		if (!check) {
			System.out.println("No Auction's Available");
			System.out.println("==================================================");
		}
	}

	/**
	 * Used for buyer to print out all the auctions that buyer has won
	 * 
	 * @param List<Auction> listOfWinnings
	 */
	public void browseAuctions(List<Auction> listOfAuctions) {
		System.out.println("===============================================");
		System.out.println("                         Winnings");
		System.out.println("===============================================");
		for (Auction auction : listOfAuctions) {
			System.out.println(auction.toString1());
		}
	}

	/**
	 * Used to loop around all the active Auction's and asks the Buyer to please
	 * select an auction to bid on
	 * 
	 * @param Buyer object
	 */
	public void browseAuctions(Buyer buyer) {
		System.out.println("Please select Auction:");
		browseAuctions();
		String choice = r.nextLine();
		Auction bidchoice = listOfAuctions.get(Integer.parseInt(choice));
		System.out.println("Please select amount: ");
		double amount = r.nextDouble();
		r.nextLine();
		bidchoice.placeBid(amount, loggedInBuyer, new Date(System.currentTimeMillis()));
	}

	/**
	 * This thread will check for updates on the user that is logged in so when an
	 * item is expires,win auction and fail auction they will be informed
	 */
	class UserUpdates extends Thread {
		public void run() {
			while (userLog.isLoggedIn()) {
				if (userLog.checkUpdates() == true) {
					ArrayList<String> temp = userLog.browseUpdates();
					temp.stream().forEach(y -> System.err.println(y));
				}
				try {
					Thread.sleep(userInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * ValidateAuction thread filters the list of auctions by outdated dates and
	 * sets their statustype to expired.
	 */
	class ValidateAuction extends Thread {
		public void run() {
			while (true) {
				listOfAuctions.stream().filter(y -> y.getClosingDate().before(new Date()))
						.filter(y -> y.getStatus() != statusType.EXPIRED).filter(y -> y.getStatus() != statusType.SOLD)
						.forEach(y -> y.close());
				try {
					Thread.sleep(auctionInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
