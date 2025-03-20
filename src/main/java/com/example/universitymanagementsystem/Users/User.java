package com.example.universitymanagementsystem.Users;

public class User {
    private String username;
    private String password;
    private String userType;

    public User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

    // Common methods for all users
    public void viewDashboard() {
        System.out.println("Viewing dashboard...");
    }
}
