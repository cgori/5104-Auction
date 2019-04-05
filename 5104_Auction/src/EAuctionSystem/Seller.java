package EAuctionSystem;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {

    private List<Item> itemsForSale = new ArrayList<Item>();


    public Seller(String userName,String password){
        super(userName,password);

    }

    public boolean isBlocked() {
        return false;
    }

    public void setBlocked(){

    }

    public void sysout(){

    }
}
