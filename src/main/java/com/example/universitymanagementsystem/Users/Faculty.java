package com.example.universitymanagementsystem.Users;

public class Faculty extends User {
    public Faculty(String username, String password) {
        super(username, password, "Faculty");
    }

    // Faculty-specific methods
    public void manageCourses() {
        System.out.println("Managing courses...");
    }
}