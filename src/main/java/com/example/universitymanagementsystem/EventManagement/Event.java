package com.example.universitymanagementsystem.EventManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event {

    private final StringProperty eventCode;
    private final StringProperty eventName;
    private final StringProperty description;
    private final StringProperty location;
    private final StringProperty dateTime;
    private final StringProperty capacity;
    private final StringProperty cost;
    private final StringProperty headerImages;
    private final StringProperty registeredStudents;

    // Constructor to initialize the Event object with all attributes
    public Event(String eventCode, String eventName, String description, String location,
                 String dateTime, String capacity, String cost,
                 String headerImages, String registeredStudents) {
        this.eventCode = new SimpleStringProperty(eventCode);
        this.eventName = new SimpleStringProperty(eventName);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.dateTime = new SimpleStringProperty(dateTime);
        this.capacity = new SimpleStringProperty(capacity);
        this.cost = new SimpleStringProperty(cost);
        this.headerImages = new SimpleStringProperty(headerImages);
        this.registeredStudents = new SimpleStringProperty(registeredStudents);
    }

    // Getter and Setter for eventCode
    public String getEventCode() {
        return eventCode.get();
    }

    public void setEventCode(String eventCode) {
        this.eventCode.set(eventCode);
    }

    public StringProperty eventCodeProperty() {
        return eventCode;
    }

    // Getter and Setter for eventName
    public String getEventName() {
        return eventName.get();
    }

    public void setEventName(String eventName){
        this.eventName.set(eventName);
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public StringProperty locationProperty() {
        return location;
    }

    // Getter and Setter for dateTime
    public String getDateTime() {
        return dateTime.get();
    }

    public void setDateTime(String dateTime) {
        this.dateTime.set(dateTime);
    }

    public StringProperty dateTimeProperty() {
        return dateTime;
    }

    // Getter and Setter for capacity
    public String getCapacity() {
        return capacity.get();
    }

    public void setCapacity(String capacity) {
        this.capacity.set(capacity);
    }

    public StringProperty capacityProperty() {
        return capacity;
    }

    // Getter and Setter for cost
    public String getCost() {
        return cost.get();
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }

    public StringProperty costProperty() {
        return cost;
    }

    // Getter and Setter for headerImages
    public String getHeaderImages() {
        return headerImages.get();
    }

    public void setHeaderImages(String headerImages) {
        this.headerImages.set(headerImages);
    }

    public StringProperty headerImagesProperty() {
        return headerImages;
    }

    // Getter and Setter for registeredStudents
    public String getRegisteredStudents() {
        return registeredStudents.get();
    }

    public void setRegisteredStudents(String registeredStudents) {
        this.registeredStudents.set(registeredStudents);
    }

    public StringProperty registeredStudentsProperty() {
        return registeredStudents;
    }
}
