package com.example.universitymanagementsystem.Users;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student extends User {

    public Student(String username, String password) {
        super(username, password, "Student");
    }

    // Student-specific methods
    public void viewGrades() {
        System.out.println("Viewing grades...");
    }

    private StringProperty studentId = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty academicLevel = new SimpleStringProperty();
    private StringProperty currentSemester = new SimpleStringProperty();
    private StringProperty photoPath = new SimpleStringProperty();
    private StringProperty subjectRegistered = new SimpleStringProperty();
    private StringProperty thesisTitle = new SimpleStringProperty();
    private StringProperty progress = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();


    // Constructor with the required fields
    public Student(String studentId, String name, String email, String address, String phone,
                   String academicLevel, String currentSemester,String photoPath, String subjectRegistered, String thesisTitle, String progress, String password) {
        this.studentId = new SimpleStringProperty(studentId);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.academicLevel = new SimpleStringProperty(academicLevel);
        this.currentSemester = new SimpleStringProperty(currentSemester);
        this.photoPath = new SimpleStringProperty(photoPath);
        this.subjectRegistered = new SimpleStringProperty(subjectRegistered);
        this.thesisTitle = new SimpleStringProperty(thesisTitle);
        this.progress = new SimpleStringProperty(progress);
        this.password = new SimpleStringProperty(password);
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
    // Getter and Setter for photoPath
    public String getPhotoPath() {
        return photoPath.get();
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath.set(photoPath);
    }

    public StringProperty photoPathProperty() {
        return photoPath;
    }

    public String getSubjectRegistered() {
        return subjectRegistered.get();
    }
    public void setSubjectRegistered(String subjectRegistered){
        this.subjectRegistered.set(subjectRegistered);
    }
    public StringProperty subjectRegisteredProperty(){
        return subjectRegistered;
    }

    public String getThesisTitle() {
        return thesisTitle.get();
    }
    public void setThesisTitle(String thesisTitle){
        this.thesisTitle.set(thesisTitle);
    }

    public StringProperty thesisTitleProperty() {
        return thesisTitle;
    }

    public String getProgress() {
        return progress.get();
    }
    public void setProgress(String progress){
        this.progress.set(progress);
    }

    public StringProperty progressProperty() {
        return progress;
    }

    public String getPassword() {
        return password.get();
    }
    public void setPassword( String password){
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
