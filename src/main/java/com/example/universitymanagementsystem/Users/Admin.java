package com.example.universitymanagementsystem.Users;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    // Admin-specific methods
    public void manageUsers() {
        System.out.println("Managing users...");
    }
}