package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import EAuctionSystem.Auction;
import EAuctionSystem.Buyer;
import EAuctionSystem.Item;
import EAuctionSystem.Seller;
import EAuctionSystem.User;
import EAuctionSystem.statusType;



public class SysAuction{
//	private static List<SysAuction> listOfAuctions. = new ArrayList<SysAuction>();
	private List<Auction> listOfAuctions = Collections.synchronizedList(new ArrayList<Auction>());
    private List<User> listOfUsers = new ArrayList<User>();
    private  Buyer loggedInBuyer;
    private  Seller loggedInSeller;
    private Auction y = new Auction();
    private Scanner r = new Scanner(System.in);
    private User user;
    

	public SysAuction() {
	
		
	     
	listOfAuctions.add(new Auction(2.59,5.00,new Date(2009-12-31),new Seller("Callum", "Test"),new Item("LMAO")));
    listOfUsers.add(new Seller("Robert", "Harrison"));
    listOfUsers.add(new Seller("Callum", "Goring"));
    listOfUsers.add(new Seller("Feels", "Weird"));
    listOfUsers.add(new Buyer("LOL","xd"));
    
  
	ValidateAuction w = new ValidateAuction();
	w.start();

    loggedInSeller=(Seller)listOfUsers.get(0);
    System.out.println(loggedInSeller.getUserName());
    String select;
	do
	{
		Date e = new Date();
		Date x = new Date(2009-12-31);
		System.out.print(e.compareTo(x));
		System.out.println("1. Browse Auctions");
		System.out.println("2. Login");
		System.out.println("3. Create Account");
		System.out.println("4. Change update interval");
		System.out.println("5. Quit");
		select = r.nextLine();
		switch(select) {
		case "1":
			break;
		case "2":
			break;
		case "3":
			break;
		case "4":
			
			System.out.println("2");
			break;
		
		}
		
	}while(!select.equalsIgnoreCase("5"));

    // write your code here
}

	class ValidateAuction extends Thread{
		@Override
		public void run() {
			while(true) {
			
			
					
				listOfAuctions.stream().filter(y->y.getClosingDate().before(new Date())).filter(y->y.getStatus() != statusType.EXPIRED).forEach(y->y.close());
						
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
public void placeAuction() {

}

public void browseAuction() {

}

public void setUpAccount() {
	
}



}
