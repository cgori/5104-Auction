package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import EAuctionSystem.Buyer;
import EAuctionSystem.Seller;
import EAuctionSystem.User;

public class SysAuction  {
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



    // write your code here
}

public void placeAuction() {

}

public void browseAuction() {

}

public void setUpAccount() {

}
}
