package org.junitpractice;

public class UserClass {
    private int userId;
    private String userName;
    private String password;
    private int accountBalance;


    public UserClass(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.accountBalance = 0; // Initial account balance is set to 0
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for accountBalance
    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean rechargeAccount(int rechargeAmount) {
        this.accountBalance += rechargeAmount;
        return true;
    }
}
