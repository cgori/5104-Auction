package EAuctionSystem;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
	private boolean blocked;

	private List<Item> itemsForSale = new ArrayList<Item>();

	public Seller(String userName, String password) {
		super(userName, password);

	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked=blocked;
	}

	public void addItem(String desc, condition itemCondition) {
		itemsForSale.add(new Item(desc, itemCondition,itemsForSale.size()));
	}

	public Item pickItem(int choice) {
		return itemsForSale.get(choice);
	}

	public List<Item> getItemsForSale() {
		return this.itemsForSale;
	}
	public void itemExpired(String update) {
		this.addUpdate(update);
	}
}
