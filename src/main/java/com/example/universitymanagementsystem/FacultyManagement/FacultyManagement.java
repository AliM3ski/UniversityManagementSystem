package com.example.universitymanagementsystem.FacultyManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FacultyManagement {
    // Properties matching FXML structure
    private final StringProperty facultyId;
    private final StringProperty name;
    private final StringProperty degree;
    private final StringProperty researchInterests;
    private final StringProperty email;
    private final StringProperty officeLocation;
    private final StringProperty coursesOffered;
    private final StringProperty password;
    private final StringProperty photoPath;

    // Constructor matching the simplified structure
    public FacultyManagement(String facultyId, String name, String degree, String researchInterests,
                             String email, String officeLocation, String password) {
        this.facultyId = new SimpleStringProperty(facultyId);
        this.name = new SimpleStringProperty(name);
        this.degree = new SimpleStringProperty(degree);
        this.researchInterests = new SimpleStringProperty(researchInterests);
        this.email = new SimpleStringProperty(email);
        this.officeLocation = new SimpleStringProperty(officeLocation);
        this.coursesOffered = new SimpleStringProperty(""); // Initialize empty
        this.password = new SimpleStringProperty(password);
        this.photoPath = new SimpleStringProperty(""); // Initialize empty photo path
    }

    // Getters, Setters, and Property methods
    public String getFacultyId() { return facultyId.get(); }
    public void setFacultyId(String facultyId) { this.facultyId.set(facultyId); }
    public StringProperty facultyIdProperty() { return facultyId; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public String getDegree() { return degree.get(); }
    public void setDegree(String degree) { this.degree.set(degree); }
    public StringProperty degreeProperty() { return degree; }

    public String getResearchInterests() { return researchInterests.get(); }
    public void setResearchInterests(String researchInterests) { this.researchInterests.set(researchInterests); }
    public StringProperty researchInterestsProperty() { return researchInterests; }

    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
    public StringProperty emailProperty() { return email; }

    public String getOfficeLocation() { return officeLocation.get(); }
    public void setOfficeLocation(String officeLocation) { this.officeLocation.set(officeLocation); }
    public StringProperty officeLocationProperty() { return officeLocation; }

    public String getCoursesOffered() { return coursesOffered.get(); }
    public void setCoursesOffered(String coursesOffered) { this.coursesOffered.set(coursesOffered); }
    public StringProperty coursesOfferedProperty() { return coursesOffered; }

    public String getPassword() { return password.get(); }
    public void setPassword(String password) { this.password.set(password); }
    public StringProperty passwordProperty() { return password; }

    public String getPhotoPath() { return photoPath.get(); }
    public void setPhotoPath(String path) { this.photoPath.set(path); }
    public StringProperty photoPathProperty() { return photoPath; }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId='" + facultyId.get() + '\'' +
                ", name='" + name.get() + '\'' +
                ", degree='" + degree.get() + '\'' +
                ", researchInterests='" + researchInterests.get() + '\'' +
                ", email='" + email.get() + '\'' +
                ", officeLocation='" + officeLocation.get() + '\'' +
                ", password='" + password.get() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyManagement that = (FacultyManagement) o;
        return facultyId.get().equals(that.facultyId.get()) &&
                email.get().equals(that.email.get());
    }

    @Override
    public int hashCode() {
        return 31 * facultyId.get().hashCode() + email.get().hashCode();
    }
}