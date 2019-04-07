package EAuctionSystem;

import java.util.ArrayList;

public class Buyer extends User {
	private ArrayList<Auction> listOfWinnings = new ArrayList<Auction>();

	public Buyer(String userName, String password) {

		super(userName, password);
	}

	public ArrayList<Auction> browseWinnings() {
		if(listOfWinnings.isEmpty()) {
			return null;
		}else {
		return this.listOfWinnings;
		}
	}
	
	public void auctionWon(Auction won) {
		this.addUpdate("You Won! ON: "+ won.getItemForSale().getDescription() + " For: " + won.getCurrentPrice() + " When: " + won.getClosingDate());
		listOfWinnings.add(won);
	}

}
