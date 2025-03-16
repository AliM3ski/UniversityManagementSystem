module com.example.universitymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires java.compiler;
    /*requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
*/

    opens com.example.universitymanagementsystem to javafx.fxml;
    exports com.example.universitymanagementsystem;
    exports com.example.universitymanagementsystem.DashBoard;
    opens com.example.universitymanagementsystem.DashBoard to javafx.fxml;
    exports com.example.universitymanagementsystem.Login;
    opens com.example.universitymanagementsystem.Login to javafx.fxml;
    exports com.example.universitymanagementsystem.EventManagement;
    opens com.example.universitymanagementsystem.EventManagement to javafx.fxml;
    exports com.example.universitymanagementsystem.StudentManagement;
    opens com.example.universitymanagementsystem.StudentManagement to javafx.fxml;
    exports com.example.universitymanagementsystem.SubjectManagement;
    opens com.example.universitymanagementsystem.SubjectManagement to javafx.fxml;
    exports com.example.universitymanagementsystem.CourseManagement;
    opens com.example.universitymanagementsystem.CourseManagement to javafx.fxml;
    exports com.example.universitymanagementsystem.FacultyManagement;
    opens com.example.universitymanagementsystem.FacultyManagement to javafx.fxml;
}