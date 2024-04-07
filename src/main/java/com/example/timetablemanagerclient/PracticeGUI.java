package com.example.timetablemanagerclient;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PracticeGUI extends Application {

    private Stage primaryStage;
    //private ApplicationController controller;

    @Override
    public void start(Stage primaryStage) throws UnknownHostException {
        //this.controller = new ApplicationController();
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Class Scheduler");
        showStartScreen();
    }

    private void showStartScreen() {
        // Welcome screen
        Label welcomeLabel = new Label("Welcome to the class scheduler");
        welcomeLabel.setFont(Font.font(20));

        Button enterButton = new Button("Enter");
        enterButton.setPrefSize(100, 40);
        enterButton.setFont(Font.font(15));

        // Enter button action
        enterButton.setOnAction(e -> showOptionsScreen());

        Button closeButton = new Button("Close");
        closeButton.setPrefSize(100, 40);
        closeButton.setFont(Font.font(15));

        // Close button action
        closeButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Closing Application");
            alert.setHeaderText(null);
            alert.setContentText("Closing application...");
            alert.showAndWait();
            primaryStage.close();
        });

        HBox buttonLayout = new HBox(20);
        buttonLayout.getChildren().addAll(enterButton, closeButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setStyle("-fx-background-color: #839ca3;");
        welcomeLayout.getChildren().addAll(welcomeLabel, buttonLayout);
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setPadding(new Insets(20));

        Scene welcomeScene = new Scene(welcomeLayout, 400, 200);

        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }


    private void showOptionsScreen() {
        // Options screen: Add class, Remove class, Display timetable, Close app
        Button addClassButton = new Button("Add Class");
        addClassButton.setPrefSize(150, 40);
        addClassButton.setFont(Font.font(15));
        addClassButton.setOnAction(e -> showAddClassScreen());

        Button removeClassButton = new Button("Remove Class");
        //removeClassButton.setOnAction(e -> showRemoveClassScreen());
        removeClassButton.setPrefSize(150, 40);
        removeClassButton.setFont(Font.font(15));

        Button displayTimetableButton = new Button("Display Timetable");
        //displayTimetableButton.setOnAction(e -> showTimetableScreen());
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

        TextField classIdField = new TextField();
        classIdField.setPromptText("Class ID");
        classIdField.setPrefWidth(200); // Set the preferred width

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
        ObservableList<String> times = FXCollections.observableArrayList("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");
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

        Label classIdLabel = new Label("Class ID:");
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

        addClassLayout.add(classIdLabel, 0, 0);
        addClassLayout.add(classIdField, 1, 0);

        addClassLayout.add(moduleLabel, 0, 1);
        addClassLayout.add(moduleCodeField, 1, 1);

        addClassLayout.add(roomLabel, 0, 2);
        addClassLayout.add(roomCodeField, 1, 2);

        addClassLayout.add(dateLabel, 0, 3);
        addClassLayout.add(startDatePicker, 1, 3);

        addClassLayout.add(startLabel, 0, 4);
        addClassLayout.add(startTimeComboBox, 1, 4);

        addClassLayout.add(endLabel, 0, 5);
        addClassLayout.add(endTimeComboBox, 1, 5);

        HBox buttonBox = new HBox(20, submitButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        addClassLayout.add(buttonBox, 0, 8, 2, 1);

        Scene addClassScene = new Scene(addClassLayout, 400, 300);

        addClassStage.setScene(addClassScene);
        addClassStage.show();


        // Submit add class request action/event
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Get the entered values
                String classId = classIdField.getText();
                String module = moduleCodeField.getText();
                String room = roomCodeField.getText();
                String startTime = startTimeComboBox.getValue();
                String endTime = endTimeComboBox.getValue();
                String date = startDatePicker.getValue().toString();

                boolean fieldsCorrect = checkFields(classId, module, room, startTime, endTime, date);
                ClassSchedule classSchedule = new ClassSchedule(classId, module, room, startTime, endTime, date);

                if(fieldsCorrect){
                    Task<String> task = new TimetableTask(classSchedule, "add");

                    task.setOnRunning((successEvent) -> {
                        submitButton.setDisable(true);
                    });

                    task.setOnSucceeded((succeededEvent) -> {
                        submitButton.setDisable(false);
                    });

                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(task);
                    executorService.shutdown();
                }
            }
        });

        cancelButton.setOnAction(e -> addClassStage.close());

    }

    private boolean checkFields(String classId, String moduleCode, String roomCode, String startTime, String endTime, String date) {
        // Check if any field is empty
        if (classId.trim().isEmpty() || moduleCode.trim().isEmpty() || date.trim().isEmpty() || startTime.trim().isEmpty() || endTime.trim().isEmpty() || roomCode.isEmpty()) {
            displayAlert("Please fill in all fields.");
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localStartTime = LocalTime.parse(startTime, formatter);
        LocalTime localEndTime = LocalTime.parse(startTime, formatter);

        // Validate start and end times
        if (localStartTime.isAfter(localEndTime)) {
            displayAlert("Start time cannot be after end time.");
            return false;
        }

        return true;
    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}