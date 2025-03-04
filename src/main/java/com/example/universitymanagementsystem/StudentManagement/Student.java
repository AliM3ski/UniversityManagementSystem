package com.example.universitymanagementsystem.StudentManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Student {
    private final StringProperty studentId;
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty academicLevel;

    public Student(String studentId, String name, String email, String academicLevel){
        this.studentId = new SimpleStringProperty(studentId);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.academicLevel = new SimpleStringProperty(academicLevel);
    }

    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

    public String getStudentId() {
        return studentId.get();
    }

    public StringProperty studentIdProperty() {
        return studentId;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel.set(academicLevel);
    }

    public String getAcademicLevel() {
        return academicLevel.get();
    }

    public StringProperty academicLevelProperty() {
        return academicLevel;
    }
}
