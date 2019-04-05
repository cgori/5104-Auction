package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import EAuctionSystem.Buyer;
import EAuctionSystem.Seller;
import EAuctionSystem.User;

public class SysAuction implements Runnable  {
//	private static List<SysAuction> listOfAuctions. = new ArrayList<SysAuction>();
	private static List<SysAuction> listOfAuctions = Collections.synchronizedList(new ArrayList<SysAuction>());
    private static List<User> listOfUsers = new ArrayList<User>();
    private static Buyer loggedInBuyer;
    private static Seller loggedInSeller;
    private Scanner r = new Scanner(System.in);
	public SysAuction() {
	
		
		
    listOfUsers.add(new Seller("Robert", "Harrison"));
    listOfUsers.add(new Seller("Callum", "Goring"));
    listOfUsers.add(new Seller("Feels", "Weird"));
    listOfUsers.add(new Buyer("LOL","xd"));
    

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
			break;
		
		}
		
	}while(!select.equalsIgnoreCase("5"));

    // write your code here
}

public void placeAuction() {

}

public void browseAuction() {

}

public void setUpAccount() {

}

@Override
public void run() {
	
	
}
}
