package EAuctionSystem;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction implements Blockable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6356075445947904532L;
	private double upperInc, lowerInc;
	private List<Bid> listOfBidders = new ArrayList<Bid>();
	private Item itemForSale;
	private Seller who;
	private double startPirce, reservePrice, currentPrice = 0;
	private Date closingDate;
	private statusType status;
	private boolean blocked;
	private int uniqueID;

	public Auction(double startPirce, double reservePrice, Date closingDate, Seller who, Item whichItem, int id) {
		try {
			if (startPirce >= reservePrice) {
				throw new ArithmeticException("e");
			} else {
				setStatus(statusType.ACTIVE);
				setStartPirce(startPirce);
				setReservePrice(reservePrice);
				setItemForSale(whichItem);
				setWho(who);
				setClosingDate(closingDate);
				setUpperLowerInc();
				setUniqueID(id);
			}
		} catch (ArithmeticException e) {
			System.err.println("Start price > ReservePrice");
		}

	}

	private void setUniqueID(int id) {
		this.uniqueID = id;

	}

	public int getID() {
		return this.uniqueID;
	}

	public void placeBid(double amount, Buyer who, Date when) {
		if (amount >= this.lowerInc && amount <= this.upperInc && this.status == statusType.ACTIVE) {
			this.currentPrice += amount;
			listOfBidders.add(new Bid(currentPrice, who, when));
		} else {
		}

	}

	public void close() {
		if (currentPrice > reservePrice) {
			System.err.println("The item " + itemForSale.getDescription() + " has sold.");
			who.itemExpired(this.itemForSale.getDescription() + " Sold For " + this.currentPrice);
		} else {
			System.err.println("The item " + itemForSale.getDescription() + " has expired.");
			who.itemExpired(this.itemForSale.getDescription() + " has expired.");
		}
		status = statusType.EXPIRED;
		checkWinner();
	}

	private void checkWinner() {
		if (!listOfBidders.isEmpty() && listOfBidders.get(listOfBidders.size() - 1).getAmount() >= reservePrice) {
			expired(listOfBidders.get(listOfBidders.size() - 1).getWho());
		} else {
			expired(listOfBidders);
		}
	}

	private void expired(List<Bid> listOfBidders) {
		String temp = "Sorry but the item you bidded for has expired: " + this.itemForSale.getDescription();
		List<Buyer> tempList = new ArrayList<Buyer>();
		for (Bid bid : listOfBidders) {
			if (!tempList.contains(bid.getWho())) {
				tempList.add(bid.getWho());
				Buyer lost = bid.getWho();
				lost.addUpdate(temp);
			}
		}
	}

	private void expired(Buyer who) {
		who.auctionWon(this);
	}

	public synchronized boolean isBlocked() {
		return blocked;
	}

	@Override
	public synchronized void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	private final void setUpperLowerInc() {
		lowerInc = getStartPirce() * 0.1;
		upperInc = getStartPirce() * 0.2;
	}

	public double getUpperInc() {
		return upperInc;
	}

	public double getLowerInc() {
		return lowerInc;
	}

	public List<Bid> getListOfBidders() {
		return this.listOfBidders;
	}

	public synchronized void setListOfBidders(List<Bid> listOfBidders) {
		this.listOfBidders = listOfBidders;
	}

	public Item getItemForSale() {
		return this.itemForSale;
	}

	public synchronized void setItemForSale(Item itemForSale) {
		this.itemForSale = itemForSale;
	}

	public synchronized Seller getWho() {
		return this.who;
	}

	public synchronized void setWho(Seller who) {
		this.who = who;
	}

	public synchronized double getStartPirce() {
		return this.startPirce;
	}

	public synchronized void setStartPirce(double startPirce) {
		this.startPirce = startPirce;
		this.currentPrice = startPirce;
	}

	public synchronized double getReservePrice() {
		return this.reservePrice;
	}

	public synchronized void setReservePrice(double reservePrice) {
		this.reservePrice = reservePrice;
	}

	public synchronized Date getClosingDate() {
		return this.closingDate;
	}

	public synchronized void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public synchronized statusType getStatus() {
		return this.status;
	}

	public synchronized void setStatus(statusType status) {
		this.status = status;
	}

	public String toString() {
		String temp = "|Expires: " + this.closingDate + "|Desc: " + this.itemForSale.getDescription() + "|Condition: "
				+ this.itemForSale.getitemCondition() + "|Upperinc:" + new DecimalFormat("##.##").format(this.upperInc)
				+ " |Lowerinc:" + new DecimalFormat("##.##").format(this.lowerInc);
		return temp;
	}

	public String toString1() {
		String temp = "|Expired: " + this.closingDate + "|Desc: " + this.itemForSale.getDescription() + "|Condition: "
				+ this.itemForSale.getitemCondition() + "|Price: " + this.currentPrice;
		return temp;
	}

	public double getCurrentPrice() {
		return this.currentPrice;
	}
}
