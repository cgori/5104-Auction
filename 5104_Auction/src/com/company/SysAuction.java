package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

import EAuctionSystem.Auction;
import EAuctionSystem.Buyer;
import EAuctionSystem.Item;
import EAuctionSystem.Seller;
import EAuctionSystem.User;
import EAuctionSystem.condition;
import EAuctionSystem.statusType;

public class SysAuction {

//	private static List<SysAuction> listOfAuctions. = new ArrayList<SysAuction>();
	private List<Auction> listOfAuctions = Collections.synchronizedList(new ArrayList<Auction>());
	private List<User> listOfUsers = new ArrayList<User>();
	private Buyer loggedInBuyer;
	private Seller loggedInSeller;
	private User userLog;
	private Scanner r = new Scanner(System.in);
	private int userInterval = 500;
	private int auctionInterval = 200;
	private UserUpdates updates = new UserUpdates();
	private ValidateAuction w = new ValidateAuction();
//TODO add Active auctions all pending due to not being verified
	public SysAuction() {
		listOfUsers.add(new Seller("Callum", "Test"));
		listOfUsers.add(new Seller("Robert", "Harrison"));
		listOfUsers.add(new Seller("Callum", "Goring"));
		listOfUsers.add(new Seller("Feels", "Weird"));
		listOfUsers.add(new Buyer("LOL", "xd"));
		listOfUsers.add(new Buyer("temp", "xd"));

		listOfAuctions.add(new Auction(7.50, 25.00, new Date(System.currentTimeMillis() + 123L),
				(Seller) listOfUsers.get(0), new Item("Car", condition.NEW, 123123123), 23132));
		listOfAuctions.add(new Auction(7.50, 25.00, new Date(System.currentTimeMillis() + 2500L),
				(Seller) listOfUsers.get(0), new Item("Car", condition.NEW, 123123123), 23132));
		listOfAuctions.add(new Auction(7.50, 25.00, new Date(System.currentTimeMillis() + 10000L),
				(Seller) listOfUsers.get(0), new Item("Car", condition.NEW, 123123123), 23132));
		listOfAuctions.add(new Auction(7.50, 25.00, new Date(System.currentTimeMillis() + 19000L),
				(Seller) listOfUsers.get(0), new Item("Car", condition.NEW, 123123123), 23132));
		listOfAuctions.add(new Auction(7.50, 25.00, new Date(System.currentTimeMillis() + 35000L),
				(Seller) listOfUsers.get(0), new Item("Car", condition.NEW, 123123123), 23132));

		listOfAuctions.get(2).placeBid(0.30, (Buyer) listOfUsers.get(4), new Date(System.currentTimeMillis()));
		listOfAuctions.get(1).placeBid(0.10, (Buyer) listOfUsers.get(4), new Date(System.currentTimeMillis()));

		
		w.start();
		String select;

		do {
			/*
			 * Date e = new Date(); Date x = new Date(2009 - 12 - 31);
			 * System.out.print(e.compareTo(x));
			 */
		
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
				
				switch(select) {
				
				case "1":
					System.out.print("Interval (milliseconds):");
					auctionInterval = r.nextInt();
					r.nextLine();
					System.err.println("Auction Validator interval is now " + auctionInterval);
					break;
				case "2":
					break;
				}
			
				
			

			}

		} while (!select.equalsIgnoreCase("5"));
		System.exit(0);
	}

	private void login() {
		System.out.println("Please Enter Username: ");
		String userName = r.nextLine();
		System.out.println("Please Enter Password: ");
		String password = r.nextLine();
		verifyLogin(userName, password);
	}
	
	private void changeInterval() {
		System.out.println("Select the thread you wish to change the update interval on");
		System.out.println("1. User Updates");
		System.out.println("2. Auction Validator (requires user to be logged in)");
		String select = r.nextLine();
		
		switch(select) {
		case "1":
			System.out.print("Interval (milliseconds):");
			auctionInterval = r.nextInt();
			r.nextLine();
			System.err.println("Auction Validator interval is now " + auctionInterval);
			break;
		
		
		case "2":
			System.out.print("Interval (milliseconds):");
			userInterval = r.nextInt();
			r.nextLine();
			System.err.println("User Update interval is now " + userInterval);
			break;
		
		}
	}
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

	public void verifyLogin(String userName, String password) {
				for (User user : listOfUsers) {
			if (user.checkusername(userName) == true && user.checkPassword(password) == true) {
				try {
					this.loggedInSeller = (Seller) user;
					this.userLog = (Seller) user;
					this.userLog.setLoggedIn(true);
					updates.start();
					sellerMenu();
				} catch (Exception e) {
					this.loggedInBuyer = (Buyer) user;
					this.userLog = (Buyer) user;
					this.userLog.setLoggedIn(true);
					updates.start();
					buyerMenu();
				}
			}
		}

	}

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

	private void verifyAuction() {
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

	private void startNewAuction() {
		if(loggedInSeller.getItemsForSale().size()>=1) {
		System.out.println("Please Select An Item For Sale: ");
		for (Item item : loggedInSeller.getItemsForSale()) {
			System.out.println(item.getID() + "| " + item.getDescription() + " |" + item.getitemCondition());
		}
		String choice = r.nextLine();
		
		Item newItemAuction = loggedInSeller.pickItem(Integer.parseInt(choice));
		
		System.out.println("dawondwadoiwandwaniowadiondwainodwaion");
		boolean beginAuction = true;
		for (Auction auction : listOfAuctions) {
			if (auction.getItemForSale().equals(newItemAuction)) {
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
			// TODO Auto-generated catch block
			System.err.println("Not a valid date format.");
		}
		listOfAuctions.add(new Auction(startPrice, reservePrice, closingDate, loggedInSeller, newItemAuction,listOfAuctions.size()));
		System.err.println("Auction is now pending.");
	}

	private void newSellerItem() {
		System.out.println("Please enter Item Desc: ");
		String itemDesc = r.nextLine();
		System.out.println("Please enter Item Condition: ");
		System.out.println("1. New:");
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

//TODO fix this doesnt work when takes params on start you use start which it doesnt let me
	// This will tell the user about updates on the account e.g. if they won an
	// auction or lost one :P
	

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
				browseAuctions();
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
				break;
			}

		} while (!select.equalsIgnoreCase("5"));
		loggedInBuyer.setLoggedIn(false);
		loggedInBuyer = null;

	}

	public void browseAuctions() {
		for (Auction auction : listOfAuctions) {
			if (auction.getStatus().equals(statusType.ACTIVE)) {
				System.out.println(auction.getID() + " |" + auction.toString());

			}
		}
	}

	public void browseAuctions(List<Auction> listOfAuctions) {
		System.out.println("===============================================");
		System.out.println("                         Winnings");
		System.out.println("===============================================");
		for (Auction auction : listOfAuctions) {
			System.out.println(auction.toString1());
		}
	}

	public void browseAuctions(Buyer buyer) {
		System.out.println("Please select Auction:");
		browseAuctions();
		String choice = r.nextLine();
		Auction bidchoice = listOfAuctions.get(Integer.parseInt(choice));
		System.out.println("Please select amount: ");
		double amount = r.nextDouble();
		bidchoice.placeBid(amount, loggedInBuyer, new Date(System.currentTimeMillis()));
	}
	
	
	//threads
	
	class UserUpdates extends Thread {
		public void run() {
			while (userLog.isLoggedIn()) {
				if (userLog.checkUpdates() == true) {
					ArrayList<String> temp = userLog.browseUpdates();
					int i = 1;
					for (String update : temp) {
						System.err.println("Update: " + i + ": " + update);
						i++;
					}
				}
				try {
					Thread.sleep(userInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	class ValidateAuction extends Thread {
		public void run() {
			while (true) {
				listOfAuctions.stream().filter(y -> y.getClosingDate().before(new Date()))
						.filter(y -> y.getStatus() != statusType.EXPIRED).forEach(y -> y.close());
				try {
					Thread.sleep(auctionInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
