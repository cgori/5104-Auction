package EAuctionSystem;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
	private static final long serialVersionUID = 6267452441441564177L;
	protected String userName, password;
	protected boolean loggedIn = false;
	protected ArrayList<String> updates = new ArrayList<String>();

	public User(String userName, String password) {
		setUserName(userName);
		setPassword(password);
	}

	public boolean checkPassword(String password) {
		if (this.password.equals(password)) {
			return true;
		}
		return false;
	}

	public boolean checkusername(String userName) {
		if (this.userName.equals(userName)) {
			return true;
		}
		return false;
	}

	public boolean isLoggedIn() {
		if (this.loggedIn == true) {
			return true;
		}
		return false;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public void setLoggedIn(boolean temp) {
		this.loggedIn = temp;
	}

	/**
	 * Runs in UserUpdates thread to check if the user has any updates
	 */
	public boolean checkUpdates() {
		if (this.updates.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * When updates is not null returns list of updates to the current User logged
	 * in
	 * 
	 * @return ArrayList<String> String updates
	 */
	public ArrayList<String> browseUpdates() {
		ArrayList<String> temp = new ArrayList<String>(this.updates);
		this.updates.clear();
		return temp;
	}

	/**
	 * Adds updates to the user which when logged in will be displayed
	 * 
	 * @param String update information
	 */
	public void addUpdate(String update) {
		updates.add(update);
	}

	/**
	 * checks this object if the paramaters are correct used to login
	 * 
	 * @param String userName, password
	 */
	public boolean checkLogin(String userName, String password) {
		if (checkusername(userName) == true && checkPassword(password) == true) {
			return true;
		} else {
			return false;
		}
	}
}
