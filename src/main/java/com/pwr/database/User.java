package com.pwr.database;

public class User {
    private int id;
    private String userName;
    private String role;

    public User(int id, String username, String role){
        this.id = id;
        this.userName = username;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }
}
