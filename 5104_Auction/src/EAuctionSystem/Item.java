package EAuctionSystem;

import java.io.Serializable;

public final class Item implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6059053727748120002L;
	private String description;
    private condition itempCondition;
    private int itemID;

    /**
     * Sellers can Add items to the account which they can later sell by creating an auction
     * 
     * @param String description of item, condition item condition, unique id generated
     */
    public Item(String desc, condition itemCondition, int id){
        setDescription(desc);
        setCondition(itemCondition);
        setID(id);
    }
    /**
     * Sets the id of the item
     * 
     * @param int uniqueID
     *          
     * @return the created foo
     */
    private void setID(int id) {
		this.itemID = id;		
	}
    /**
     * Gets the uniqueID
     *            
     * @return int uniqueID
     */
    public int getID() {
    	return this.itemID;
    }
    /**
     * Used to set the description of an item
     * 
     * @param String description
     */
	public void setDescription(String description) {
        this.description = description;
    }
	  /**
     * Gets item desciption
     * @return String description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the enum item condition
     * 
     * @param enum item condition
     */
    public void setCondition(condition itemCondition){
    	this.itempCondition=itemCondition; 
    }
    /**
     * Gets enum item condition
     *            
     * @return enum item condition
     */
    public condition getitemCondition() {
    	return this.itempCondition;
    }
}
