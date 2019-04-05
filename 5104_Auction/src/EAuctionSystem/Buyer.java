package EAuctionSystem;

import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {
    public Buyer(String userName,String password){

        super(userName,password);
    }
    private List<Auction> wonAuctions = new ArrayList<Auction>();

    public void victory(){

    }

}
