package EAuctionSystem;

public class Item {
    private String description;

    public Item(String desc){
        setDescription(desc);
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
