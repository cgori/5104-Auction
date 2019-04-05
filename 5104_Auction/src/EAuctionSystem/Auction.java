package EAuctionSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction implements Blockable{
    private static double upperInc, lowerInc;
    private List<Bid> listOfBidders = new ArrayList<Bid>();
    private Item itemForSale;
    private Seller who;
    private double startPirce, reservePrice;
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

    public void placeBid() {

    }

    public void verify() {

    }

    public void close() {

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

    public static double getUpperInc() {
        return upperInc;
    }
    public static double getLowerInc() {
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

	
	


}




