package com.example.universitymanagementsystem.SubjectManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Subject class definition
// This class represents a subject with a unique code and name.
// Uses JavaFX property binding mechanism to enable automatic updates in the UI.
class Subject {
    // StringProperty allows JavaFX UI elements to listen for changes automatically.
    private final StringProperty code; // Property for subject code
    private final StringProperty name; // Property for subject name

    // Constructor to initialize the Subject object with a code and name
    public Subject(String code, String name) {
        this.code = new SimpleStringProperty(code); // Wrap the code in a property
        this.name = new SimpleStringProperty(name); // Wrap the name in a property
    }

    public String getCode() {
        return code.get(); // Retrieves the current value of the subject code
    }

    public void setCode(String code) {
        this.code.set(code); // Updates the subject code value
    }

    // Returns the code property (used for UI bindings in JavaFX)
    public StringProperty codeProperty() {
        return code;
    }

    // Getter for the subject name
    public String getName() {
        return name.get(); // Retrieves the current value of the subject name
    }

    // Setter for the subject name
    public void setName(String name) {
        this.name.set(name); // Updates the subject name value
    }

    // Returns the name property (used for UI bindings in JavaFX)
    public StringProperty nameProperty() {
        return name;
    }
}
