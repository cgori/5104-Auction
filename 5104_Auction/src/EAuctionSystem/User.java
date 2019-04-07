package EAuctionSystem;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
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
    public boolean checkUpdates() {
    	if(this.updates.isEmpty()) {
    		return false;
    	}else {
    		return true;
    	}
    	}
    public ArrayList<String> browseUpdates(){
    	ArrayList<String> temp = new ArrayList<String>(this.updates);
		this.updates.clear();
		return temp;
    }
    public void addUpdate(String update) {
    	updates.add(update);
	}
    public boolean checkLogin(String userName,String password) {
    	if (checkusername(userName) == true && checkPassword(password) == true) {
    		return true;
    	}else {
    	return false;
    	}
    }
}
