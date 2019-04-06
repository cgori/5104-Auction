package EAuctionSystem;

public class Item {
    private String description;
    private condition itempCondition;

    public Item(String desc, condition itemCondition){
        setDescription(desc);
        setCondition(itemCondition);
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setCondition(condition itemCondition){
    	this.itempCondition=itemCondition; 
    }
    public condition getitemCondition() {
    	return this.itempCondition;
    }
}
