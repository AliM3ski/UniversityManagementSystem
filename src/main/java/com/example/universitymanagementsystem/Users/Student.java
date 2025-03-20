package com.example.universitymanagementsystem.Users;

public class Student extends User {
    public Student(String username, String password) {
        super(username, password, "Student");
    }

    // Student-specific methods
    public void viewGrades() {
        System.out.println("Viewing grades...");
    }
}