package com.example.universitymanagementsystem.StudentManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty studentId;
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty address;
    private final StringProperty phone;
    private final StringProperty academicLevel;
    private final StringProperty currentSemester;

    // Constructor with the required fields
    public Student(String studentId, String name, String email, String address, String phone,
                   String academicLevel, String currentSemester) {
        this.studentId = new SimpleStringProperty(studentId);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.academicLevel = new SimpleStringProperty(academicLevel);
        this.currentSemester = new SimpleStringProperty(currentSemester);
    }

    // Getter and Setter methods

    public String getStudentId() {
        return studentId.get();
    }

    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

    public StringProperty studentIdProperty() {
        return studentId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getAcademicLevel() {
        return academicLevel.get();
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel.set(academicLevel);
    }

    public StringProperty academicLevelProperty() {
        return academicLevel;
    }

    public String getCurrentSemester() {
        return currentSemester.get();
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester.set(currentSemester);
    }

    public StringProperty currentSemesterProperty() {
        return currentSemester;
    }
}
