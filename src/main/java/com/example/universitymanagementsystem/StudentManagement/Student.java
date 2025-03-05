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
    private final StringProperty tuitionStatus;
    private final StringProperty registeredCourses;
    private final StringProperty grades;
    private final StringProperty currentSemester;
    private final StringProperty subjectsRegistered;
    private final StringProperty thesisTitle;
    private final StringProperty progress;
    private final StringProperty profilePhotoPath;

    public Student(String studentId, String name, String email, String address, String phone,
                   String academicLevel, String tuitionStatus, String registeredCourses,
                   String grades, String currentSemester, String subjectsRegistered,
                   String thesisTitle, String progress, String profilePhotoPath) {
        this.studentId = new SimpleStringProperty(studentId);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.academicLevel = new SimpleStringProperty(academicLevel);
        this.tuitionStatus = new SimpleStringProperty(tuitionStatus);
        this.registeredCourses = new SimpleStringProperty(registeredCourses);
        this.grades = new SimpleStringProperty(grades);
        this.currentSemester = new SimpleStringProperty(currentSemester);
        this.subjectsRegistered = new SimpleStringProperty(subjectsRegistered);
        this.thesisTitle = new SimpleStringProperty(thesisTitle);
        this.progress = new SimpleStringProperty(progress);
        this.profilePhotoPath = new SimpleStringProperty(profilePhotoPath);
    }

    // Getter Methods for JavaFX TableView
    public String getStudentId() {
        return studentId.get();
    }
    public StringProperty studentIdProperty() {

        return studentId;
    }

    public String getName() {

        return name.get();
    }
    public StringProperty nameProperty() {

        return name;
    }
    public String getEmail() {

        return email.get();
    }
    public StringProperty emailProperty() {

        return email;
    }

    public String getAddress() {

        return address.get();
    }
    public StringProperty addressProperty() {

        return address;
    }

    public String getPhone() {


        return phone.get();
    }
    public StringProperty phoneProperty() {

        return phone;
    }
    public String getAcademicLevel() {

        return academicLevel.get();
    }
    public StringProperty academicLevelProperty() {

        return academicLevel;
    }

    public String getTuitionStatus() {

        return tuitionStatus.get();
    }
    public StringProperty tuitionStatusProperty() {

        return tuitionStatus;
    }

    public String getRegisteredCourses() {

        return registeredCourses.get();
    }
    public StringProperty registeredCoursesProperty() {

        return registeredCourses;
    }

    public String getGrades() {

        return grades.get();
    }
    public StringProperty gradesProperty() {
        return grades; }

    public String getCurrentSemester() {

        return currentSemester.get();
    }
    public StringProperty currentSemesterProperty() {

        return currentSemester;
    }

    public String getSubjectsRegistered() {

        return subjectsRegistered.get();
    }
    public StringProperty subjectsRegisteredProperty() {
        return subjectsRegistered;
    }

    public String getThesisTitle() {

        return thesisTitle.get();
    }
    public StringProperty thesisTitleProperty() {

        return thesisTitle;
    }

    public String getProgress() {

        return progress.get();
    }
    public StringProperty progressProperty() {

        return progress;
    }

    public String getProfilePhotoPath() {

        return profilePhotoPath.get();
    }
    public StringProperty profilePhotoPathProperty() {

        return profilePhotoPath;
    }
}
