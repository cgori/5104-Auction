package EAuctionSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction implements Blockable{
    private double upperInc, lowerInc;
    private List<Bid> listOfBidders = new ArrayList<Bid>();
    private Item itemForSale;
    private Seller who;
    private double startPirce, reservePrice,currentPrice;
    private Date closingDate;
    private statusType status;
    private boolean blocked;

    public Auction(double startPirce, double reservePrice, Date closingDate, Seller who, Item whichItem) {
        setStatus(statusType.PENDING);
        setStartPirce(startPirce);
        setReservePrice(reservePrice);
        setItemForSale(whichItem);
        setWho(who);
        setClosingDate(closingDate);
        setUpperLowerInc();


    }
	public void placeBid(double amount,Buyer who,Date when) {
		if(amount>=this.lowerInc && amount<=this.upperInc) {
			listOfBidders.add(new Bid(currentPrice+amount,who,when));
		}
		
    }
	
    public void close() {
    	status = statusType.EXPIRED;
    	System.out.println("The item: "+ itemForSale.getDescription() + " has expired");
    	checkWinner();
    }

    private void checkWinner() {
		for (Bid bid : listOfBidders) {
			System.out.println(bid.getAmount() + " "+ reservePrice);
			if(bid.getAmount()>=reservePrice) {
				expired(bid.getWho());
			}else {
				expired(listOfBidders);
			}
		}
	}
	private void expired(List<Bid>listOfBidders) {
		String temp = "Sorry but the item you bidded for has expired: " +this.itemForSale.getDescription();
		for (Bid bid : listOfBidders) {
			Buyer lost = bid.getWho();
			lost.addUpdate(temp);
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


    private final void setUpperLowerInc(){
        lowerInc = getStartPirce()*0.1;
        upperInc = getStartPirce()*0.2;
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
    	String temp = "|Expires: "+this.closingDate + "|Desc: " + this.itemForSale.getDescription() +"|Condition: "+ this.itemForSale.getitemCondition();
    	return temp;
    }
}




