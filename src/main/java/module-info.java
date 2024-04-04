module com.example.timetablemanagerclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timetablemanagerclient to javafx.fxml;
    exports com.example.timetablemanagerclient;
}