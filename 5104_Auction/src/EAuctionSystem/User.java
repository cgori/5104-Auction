package EAuctionSystem;

import java.util.ArrayList;

public abstract class User {
    protected String userName, password;
    protected boolean loggedIn=false;
    protected ArrayList<String> updates = new ArrayList<String>();
    
    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public boolean checkPassword(String password) {
    	if(this.password.equals(password)) {
    		return true;
    	}
        return false;
    }

    public boolean checkusername(String userName) {
    	if(this.userName.equals(userName)) {
    		return true;
    	}
        return false;
    }
    public boolean isLoggedIn() {
    	if(this.loggedIn==true) {
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
    	this.loggedIn=temp;
    }
    public ArrayList<String> checkUpdates() {
    	if(!this.updates.isEmpty()) {
    		ArrayList<String> temp=this.updates;
    		this.updates.clear();
    		return temp;
    	}
    	return null;
    }
    public void addUpdate(String update) {
    	updates.add(update);
	}
    
    
    
    
}
