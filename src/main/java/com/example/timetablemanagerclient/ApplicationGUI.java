package com.example.timetablemanagerclient;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ApplicationGUI extends Application {

    private Stage primaryStage;
    private String courseID;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Class Scheduler");

        // First window: Enter course ID
        TextField courseIDField = new TextField();
        courseIDField.setPromptText("Enter Course ID");
        Button startButton = new Button("Start");
        startButton.setFont(Font.font(15));

        startButton.setOnAction(e -> {
            courseID = courseIDField.getText();
            showOptionsScreen();
        });

        VBox firstScreenLayout = new VBox(20);
        firstScreenLayout.setStyle("-fx-background-color: #839ca3;");
        firstScreenLayout.getChildren().addAll(courseIDField, startButton);
        firstScreenLayout.setAlignment(Pos.CENTER);
        firstScreenLayout.setPadding(new Insets(20));

        Scene firstScene = new Scene(firstScreenLayout, 400, 300);

        primaryStage.setScene(firstScene);
        primaryStage.show();
    }

    private void showOptionsScreen() {
        // Options screen: Add class, Remove class, Display timetable, Close app
        Button addClassButton = new Button("Add Class");
        addClassButton.setPrefSize(150, 40);
        addClassButton.setFont(Font.font(15));
        addClassButton.setOnAction(e -> showAddClassScreen());


        Button removeClassButton = new Button("Remove Class");
        removeClassButton.setOnAction(e -> showRemoveClassScreen());
        removeClassButton.setPrefSize(150, 40);
        removeClassButton.setFont(Font.font(15));

        Button displayTimetableButton = new Button("Display Timetable");
        displayTimetableButton.setOnAction(e -> showTimetableScreen());
        displayTimetableButton.setPrefSize(150, 40);
        displayTimetableButton.setFont(Font.font(15));

        Button closeButton = new Button("Close App");
        closeButton.setPrefSize(150, 40);
        closeButton.setFont(Font.font(15));
        closeButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Closing Application");
            alert.setHeaderText(null);
            alert.setContentText("Closing application...");
            alert.showAndWait();
            primaryStage.close();
        });

        VBox optionsLayout = new VBox(20);
        optionsLayout.setStyle("-fx-background-color: #839ca3;");
        optionsLayout.getChildren().addAll(addClassButton, removeClassButton, displayTimetableButton, closeButton);
        optionsLayout.setAlignment(Pos.CENTER);
        optionsLayout.setPadding(new Insets(20));

        Scene optionsScene = new Scene(optionsLayout, 400, 300);

        primaryStage.setScene(optionsScene);
        primaryStage.show();
    }

    private void showAddClassScreen() {
        Stage addClassStage = new Stage();
        addClassStage.setTitle("Add Class");

        // Text fields for module code, start date, start time, end time, and room code
        classModification(addClassStage);
    }

    private void showRemoveClassScreen() {
        Stage addClassStage = new Stage();
        addClassStage.setTitle("Remove Class");

        // Text fields for module code, start date, start time, end time, and room code
        classModification(addClassStage);
    }

    private void classModification(Stage addClassStage) {
        TextField moduleCodeField = new TextField();
        moduleCodeField.setPromptText("Module Code");
        moduleCodeField.setPrefWidth(200); // Set the preferred width

        // DatePicker for start date
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");
        startDatePicker.setPrefWidth(200); // Set the preferred width

        // Drop-down box for start time
        ComboBox<String> startTimeComboBox = new ComboBox<>();
        startTimeComboBox.setPromptText("Start Time");
        startTimeComboBox.setPrefWidth(200);
        ObservableList<String> times = FXCollections.observableArrayList(
                "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"
        );
        startTimeComboBox.setItems(times);

        // Drop-down box for end time
        ComboBox<String> endTimeComboBox = new ComboBox<>();
        endTimeComboBox.setPromptText("End Time");
        endTimeComboBox.setItems(times);
        endTimeComboBox.setPrefWidth(200);

        TextField roomCodeField = new TextField();
        roomCodeField.setPromptText("Room Code");
        roomCodeField.setPrefWidth(200); // Set the preferred width

        Button submitButton = new Button("Submit");
        Button cancelButton = new Button("Cancel");

        submitButton.setOnAction(e -> {
            // Check if any field is empty
            if (moduleCodeField.getText().isEmpty() || startDatePicker.getValue() == null ||
                    startTimeComboBox.getValue() == null || endTimeComboBox.getValue() == null ||
                    roomCodeField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
            } else {
                // Send data to server and display response
                // Dummy response
                displayAlert("Class added successfully.");
                addClassStage.close();
            }
        });

        cancelButton.setOnAction(e -> addClassStage.close());

        Label moduleLabel = new Label("Module:");
        Label dateLabel = new Label("Date:");
        Label startLabel = new Label("Start:");
        Label endLabel = new Label("End:");
        Label roomLabel = new Label("Room:");

        GridPane addClassLayout = new GridPane();
        addClassLayout.setStyle("-fx-background-color: #839ca3;");
        addClassLayout.setAlignment(Pos.CENTER);
        addClassLayout.setHgap(10);
        addClassLayout.setVgap(10);
        addClassLayout.setPadding(new Insets(20));

        addClassLayout.add(moduleLabel, 0, 0);
        addClassLayout.add(moduleCodeField, 1, 0);

        addClassLayout.add(roomLabel, 0, 1);
        addClassLayout.add(roomCodeField, 1, 1);

        addClassLayout.add(dateLabel, 0, 2);
        addClassLayout.add(startDatePicker, 1, 2);

        addClassLayout.add(startLabel, 0, 3);
        addClassLayout.add(startTimeComboBox, 1, 3);

        addClassLayout.add(endLabel, 0, 4);
        addClassLayout.add(endTimeComboBox, 1, 4);

        HBox buttonBox = new HBox(20, submitButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        addClassLayout.add(buttonBox, 0, 8, 2, 1);

        Scene addClassScene = new Scene(addClassLayout, 400, 300);

        addClassStage.setScene(addClassScene);
        addClassStage.show();
    }
    private void showTimetableScreen() {
        Stage timetableStage = new Stage();
        timetableStage.setTitle("Timetable");

        // Text field for confirming course ID
        TextField courseIDField = new TextField();
        courseIDField.setPromptText("Enter Course ID");
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            if (courseIDField.getText().equals(courseID)) {
                // Fetch timetable from server and display
                // Dummy timetable
                displayTimetable("Monday: Math (9:00 - 11:00, Room A)");
                timetableStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Course ID doesn't match.");
                alert.showAndWait();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> timetableStage.close());

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(confirmButton, cancelButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox timetableLayout = new VBox(10);
        timetableLayout.setStyle("-fx-background-color: #839ca3;");
        timetableLayout.getChildren().addAll(courseIDField, buttonLayout);
        settingScene(timetableLayout);
    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void displayTimetable(String timetable) {
        // Display timetable received from server
        TextArea timetableTextArea = new TextArea(timetable);
        timetableTextArea.setEditable(false);

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> showOptionsScreen());

        VBox timetableLayout = new VBox(10);
        timetableLayout.setStyle("-fx-background-color: #839ca3;");
        timetableLayout.getChildren().addAll(timetableTextArea, okButton);
        settingScene(timetableLayout);
    }

    private void settingScene(VBox layout){
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene displayScene = new Scene(layout, 400, 300);

        primaryStage.setScene(displayScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}