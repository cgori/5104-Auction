package EAuctionSystem;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {
	private ArrayList<Auction> listOfWinnings = new ArrayList<Auction>();

	public Buyer(String userName, String password) {

		super(userName, password);
	}

	public ArrayList<Auction> browseWinnings() {
		return this.listOfWinnings;
	}
	
	public void auctionWon(Auction won) {
		listOfWinnings.add(won);
	}

}
