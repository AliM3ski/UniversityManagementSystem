package com.example.universitymanagementsystem.FacultyManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ObjectProperty;
import java.time.LocalDate;

public class FacultyManagement {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty degree;
    private final StringProperty researchInterests;
    private final StringProperty coursesOffered;
    private final StringProperty officeLocation;
    private final StringProperty phoneNumber;
    private final StringProperty department;
    private final ObjectProperty<LocalDate> joinDate;

    public FacultyManagement(String name, String email, String degree, String researchInterests,
                             String coursesOffered, String officeLocation, String phoneNumber,
                             String department, LocalDate joinDate) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.degree = new SimpleStringProperty(degree);
        this.researchInterests = new SimpleStringProperty(researchInterests);
        this.coursesOffered = new SimpleStringProperty(coursesOffered);
        this.officeLocation = new SimpleStringProperty(officeLocation);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.department = new SimpleStringProperty(department);
        this.joinDate = new SimpleObjectProperty<>(joinDate);
    }

    // Getters, Setters, and Property methods
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
    public StringProperty emailProperty() { return email; }

    public String getDegree() { return degree.get(); }
    public void setDegree(String degree) { this.degree.set(degree); }
    public StringProperty degreeProperty() { return degree; }

    public String getResearchInterests() { return researchInterests.get(); }
    public void setResearchInterests(String researchInterests) { this.researchInterests.set(researchInterests); }
    public StringProperty researchInterestsProperty() { return researchInterests; }

    public String getCoursesOffered() { return coursesOffered.get(); }
    public void setCoursesOffered(String coursesOffered) { this.coursesOffered.set(coursesOffered); }
    public StringProperty coursesOfferedProperty() { return coursesOffered; }

    public String getOfficeLocation() { return officeLocation.get(); }
    public void setOfficeLocation(String officeLocation) { this.officeLocation.set(officeLocation); }
    public StringProperty officeLocationProperty() { return officeLocation; }

    public String getPhoneNumber() { return phoneNumber.get(); }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber.set(phoneNumber); }
    public StringProperty phoneNumberProperty() { return phoneNumber; }

    public String getDepartment() { return department.get(); }
    public void setDepartment(String department) { this.department.set(department); }
    public StringProperty departmentProperty() { return department; }

    public LocalDate getJoinDate() { return joinDate.get(); }
    public void setJoinDate(LocalDate joinDate) { this.joinDate.set(joinDate); }
    public ObjectProperty<LocalDate> joinDateProperty() { return joinDate; }
}