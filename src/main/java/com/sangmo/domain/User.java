package com.sangmo.domain;

public class User {
    private String userName;
    private String password;
    private int userType;   //Buyer：userType=0  Seller：userType=1 notLogin：-1

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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
