package com.example.universitymanagementsystem.CourseManagement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

// Course class definition
class Course {
    private final StringProperty name;
    private final StringProperty subject;
    private final StringProperty schedule;
    private final IntegerProperty capacity;
    private final StringProperty faculty;

    public Course(String name, String subject, String schedule, int capacity, String faculty) {
        this.name = new SimpleStringProperty(name);
        this.subject = new SimpleStringProperty(subject);
        this.schedule = new SimpleStringProperty(schedule);
        this.capacity = new SimpleIntegerProperty(capacity);
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
}
