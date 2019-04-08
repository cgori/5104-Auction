package EAuctionSystem;

import java.io.Serializable;
import java.util.Date;

public class Bid implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4249787064268718720L;
	private Double amount;
    private Buyer who;
    private Date when;

    public Bid(double amount, Buyer who, Date when){
        setAmount(amount);
        setWhen(when);
        setWho(who);
    }




    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Buyer getWho() {
        return this.who;
    }

    public void setWho(Buyer who) {
        this.who = who;
    }

    public Date getWhen() {
        return this.when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}
