package EAuctionSystem;

public abstract class User {
    protected String userName, password;

    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }


    public boolean checkPassword() {
        return true;
    }


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

}
