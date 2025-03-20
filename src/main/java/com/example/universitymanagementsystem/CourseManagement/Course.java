package com.example.universitymanagementsystem.CourseManagement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

// Course class definition
public class Course {
    private final StringProperty name;
    private final IntegerProperty courseCode; // New field
    private final StringProperty subject;
    private final StringProperty sectionNumber; // New field
    private final IntegerProperty capacity;
    private final StringProperty schedule;
    private final StringProperty finalExamDateTime; // New field
    private final StringProperty location; // New field
    private final StringProperty faculty;

    public Course(int courseCode,String name, String subject, String sectionNumber, int capacity, String schedule,
                  String finalExamDateTime, String location, String faculty) {
        this.courseCode = new SimpleIntegerProperty(courseCode); // Initialize new field
        this.name = new SimpleStringProperty(name);
        this.subject = new SimpleStringProperty(subject);
        this.sectionNumber = new SimpleStringProperty(sectionNumber); // Initialize new field
        this.capacity = new SimpleIntegerProperty(capacity);
        this.schedule = new SimpleStringProperty(schedule);
        this.finalExamDateTime = new SimpleStringProperty(finalExamDateTime); // Initialize new field
        this.location = new SimpleStringProperty(location); // Initialize new field
        this.faculty = new SimpleStringProperty(faculty);

    }

    // Getters & Property Methods for JavaFX TableView
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public String getSubject() { return subject.get(); }
    public void setSubject(String subject) { this.subject.set(subject); }
    public StringProperty subjectProperty() { return subject; }

    public String getSchedule() { return schedule.get(); }
    public void setSchedule(String schedule) { this.schedule.set(schedule); }
    public StringProperty scheduleProperty() { return schedule; }

    public int getCapacity() { return capacity.get(); }
    public void setCapacity(int capacity) { this.capacity.set(capacity); }
    public IntegerProperty capacityProperty() { return capacity; }

    public String getFaculty() { return faculty.get(); }
    public void setFaculty(String faculty) { this.faculty.set(faculty); }
    public StringProperty facultyProperty() { return faculty; }

    // New getters, setters, and property methods
    public int getCourseCode() { return courseCode.get(); }
    public void setCourseCode(int courseCode) { this.courseCode.set(courseCode); }
    public IntegerProperty courseCodeProperty() { return courseCode; }

    public String getSectionNumber() { return sectionNumber.get(); }
    public void setSectionNumber(String sectionNumber) { this.sectionNumber.set(sectionNumber); }
    public StringProperty sectionNumberProperty() { return sectionNumber; }

    public String getFinalExamDateTime() { return finalExamDateTime.get(); }
    public void setFinalExamDateTime(String finalExamDateTime) { this.finalExamDateTime.set(finalExamDateTime); }
    public StringProperty finalExamDateTimeProperty() { return finalExamDateTime; }

    public String getLocation() { return location.get(); }
    public void setLocation(String location) { this.location.set(location); }
    public StringProperty locationProperty() { return location; }
}