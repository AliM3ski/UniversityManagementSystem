package com.example.universitymanagementsystem.FacultyManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ObjectProperty;
import java.time.LocalDate;

public class FacultyManagement {
    //Properties
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty degree;
    private final StringProperty researchInterests;
    private final StringProperty coursesOffered;
    private final StringProperty officeLocation;
    private final StringProperty phoneNumber;
    private final StringProperty department;
    private final ObjectProperty<LocalDate> joinDate;

    // Constructor
    public FacultyManagement(String name, String email, String degree, String researchInterests,
                             String coursesOffered, String officeLocation, String phoneNumber,
                             String department, LocalDate joinDate) {
        this.name = new SimpleStringProperty(name); //intialize properties with provided values e.g. name  "John' Doe"
        this.email = new SimpleStringProperty(email);
        this.degree = new SimpleStringProperty(degree);
        this.researchInterests = new SimpleStringProperty(researchInterests);
        this.coursesOffered = new SimpleStringProperty(coursesOffered);
        this.officeLocation = new SimpleStringProperty(officeLocation);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.department = new SimpleStringProperty(department);
        this.joinDate = new SimpleObjectProperty<>(joinDate != null ? joinDate : LocalDate.now());  // Default to today's date if null
    }

    // Getters, Setters, and Property methods
    public String getName() { return name.get(); }  //Retrieves the value name
    public void setName(String name) { this.name.set(name); } //Updates the value of name
    public StringProperty nameProperty() { return name; } //Returns the object itself,

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

    // toString method for easier debugging and display, provides a string representation of the faculty management object for debugging/logging
    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name.get() + '\'' +
                ", email='" + email.get() + '\'' +
                ", degree='" + degree.get() + '\'' +
                ", researchInterests='" + researchInterests.get() + '\'' +
                ", coursesOffered='" + coursesOffered.get() + '\'' +
                ", officeLocation='" + officeLocation.get() + '\'' +
                ", phoneNumber='" + phoneNumber.get() + '\'' +
                ", department='" + department.get() + '\'' +
                ", joinDate=" + joinDate.get() +
                '}';
    }

    // equals and hashCode methods based on name and email for proper comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyManagement that = (FacultyManagement) o;
        return name.get().equals(that.name.get()) &&
                email.get().equals(that.email.get());
    }

    @Override
    public int hashCode() {
        return 31 * name.get().hashCode() + email.get().hashCode();
    }
}
