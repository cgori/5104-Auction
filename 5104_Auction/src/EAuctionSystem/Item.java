package EAuctionSystem;

public final class Item {
    private String description;
    private condition itempCondition;
    private int itemID;

    public Item(String desc, condition itemCondition, int id){
        setDescription(desc);
        setCondition(itemCondition);
        setID(id);
    }
    private void setID(int id) {
		this.itemID = id;		
	}
    public int getID() {
    	return this.itemID;
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
