package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import EAuctionSystem.Auction;
import EAuctionSystem.Buyer;
import EAuctionSystem.Seller;
import EAuctionSystem.User;



public class SysAuction extends Thread{
//	private static List<SysAuction> listOfAuctions. = new ArrayList<SysAuction>();
	private List<SysAuction> listOfAuctions = Collections.synchronizedList(new ArrayList<SysAuction>());
    private List<User> listOfUsers = new ArrayList<User>();
    private  Buyer loggedInBuyer;
    private  Seller loggedInSeller;
    private Auction y = new Auction();
    private Scanner r = new Scanner(System.in);
    private User user;

	public SysAuction() {
	
		
	     
		
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
			
			
			
			
			try {
				Thread.sleep(500);
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
