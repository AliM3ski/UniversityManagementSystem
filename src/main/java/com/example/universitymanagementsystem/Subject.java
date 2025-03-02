package com.example.universitymanagementsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


// Subject class definition
class Subject {
    private final StringProperty code; // Property for subject code
    private final StringProperty name; // Property for subject name

    public Subject(String code, String name) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
    }

    public String getCode() {
        return code.get(); // Get code value
    }

    public void setCode(String code) {
        this.code.set(code); // Set code value
    }

    public StringProperty codeProperty() {
        return code; // Return code property
    }

    public String getName() {
        return name.get(); // Get name value
    }

    public void setName(String name) {
        this.name.set(name); // Set name value
    }

    public StringProperty nameProperty() {
        return name; // Return name property
    }
}
