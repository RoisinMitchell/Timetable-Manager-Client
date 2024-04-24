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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimetableView extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Scheduling Application");
        showStartScreen();
    }

    private void showStartScreen() {
        Label welcomeLabel = new Label("SCHEDULING APPLICATION");
        welcomeLabel.setFont(Font.font(20));

        Button enterButton = createButton("Enter", 200, 40, 17, e -> showOptionsScreen());
        Button closeButton = createButton("Close", 200, 40, 17, e -> closeApplication());

        HBox buttonLayout = new HBox(20, enterButton, closeButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox welcomeLayout = new VBox(20, welcomeLabel, buttonLayout);
        welcomeLayout.setStyle("-fx-background-color: #839ca3;");
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setPadding(new Insets(20));

        Scene welcomeScene = new Scene(welcomeLayout, 400, 300);

        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    private void showOptionsScreen() {
        Button addScheduleButton = createButton("Add Schedule", 200, 40, 17, e -> showModifyTimetableScreen("Add"));
        Button removeScheduleButton = createButton("Remove Schedule", 200, 40, 17, e -> showModifyTimetableScreen("Remove"));
        Button displayTimetableButton = createButton("Display Timetable", 200, 40, 17, e -> showTimetableScreen());
        Button closeButton = createButton("Early Scheduling", 200, 40, 17, e -> showEarlySchedulingScreen());

        VBox optionsLayout = new VBox(20, addScheduleButton, removeScheduleButton, displayTimetableButton, closeButton);
        optionsLayout.setStyle("-fx-background-color: #839ca3;");
        optionsLayout.setAlignment(Pos.CENTER);
        optionsLayout.setPadding(new Insets(20));

        Scene optionsScene = new Scene(optionsLayout, 400, 300);

        primaryStage.setScene(optionsScene);
        primaryStage.show();
    }

    private void showModifyTimetableScreen(String requestType) {
        primaryStage.close();
        Stage modifyScheduleStage = new Stage();
        modifyScheduleStage.setTitle(requestType + " Schedule");

        TextField courseIDField = new TextField();
        courseIDField.setPromptText("Course ID");
        courseIDField.setPrefWidth(200); // Set the preferred width

        TextField moduleField = new TextField();
        moduleField.setPromptText("Module Code");
        moduleField.setPrefWidth(200); // Set the preferred width

        ComboBox<String> dayComboBox = new ComboBox<>();
        dayComboBox.setPromptText("Day");
        dayComboBox.setPrefWidth(200);
        ObservableList<String> days = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        dayComboBox.setItems(days);

        ComboBox<String> startTimeComboBox = new ComboBox<>();
        startTimeComboBox.setPromptText("Start Time");
        startTimeComboBox.setPrefWidth(200);
        ObservableList<String> times = FXCollections.observableArrayList(
                "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"
        );
        startTimeComboBox.setItems(times);

        ComboBox<String> endTimeComboBox = new ComboBox<>();
        endTimeComboBox.setPromptText("End Time");
        endTimeComboBox.setItems(times);
        endTimeComboBox.setPrefWidth(200);

        ComboBox<String> roomsComboBox = new ComboBox<>();
        roomsComboBox.setPromptText("Room");
        roomsComboBox.setPrefWidth(200);
        ObservableList<String> rooms = FXCollections.observableArrayList("S205", "S206", "S207", "S208", "S209");
        roomsComboBox.setItems(rooms);

        Button submitButton = new Button("Submit");
        Button cancelButton = new Button("Cancel");

        Label classIdLabel = new Label("Class ID:");
        Label moduleLabel = new Label("Module:");
        Label dayLabel = new Label("Day:");
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
        addClassLayout.add(courseIDField, 1, 0);

        addClassLayout.add(moduleLabel, 0, 1);
        addClassLayout.add(moduleField, 1, 1);

        addClassLayout.add(roomLabel, 0, 2);
        addClassLayout.add(roomsComboBox, 1, 2);

        addClassLayout.add(dayLabel, 0, 3);
        addClassLayout.add(dayComboBox, 1, 3);

        addClassLayout.add(startLabel, 0, 4);
        addClassLayout.add(startTimeComboBox, 1, 4);

        addClassLayout.add(endLabel, 0, 5);
        addClassLayout.add(endTimeComboBox, 1, 5);

        HBox buttonBox = new HBox(20, submitButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        addClassLayout.add(buttonBox, 0, 8, 2, 1);

        Scene modifyClassScene = new Scene(addClassLayout, 400, 300);

        modifyScheduleStage.setScene(modifyClassScene);
        modifyScheduleStage.show();

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String classId = courseIDField.getText();
                String module = moduleField.getText();
                String room = roomsComboBox.getValue();
                String startTime = startTimeComboBox.getValue();
                String endTime = endTimeComboBox.getValue();
                String day = dayComboBox.getValue();

                boolean fieldsCorrect = checkFields(classId, module, room, startTime, endTime, day);
                ScheduleModel schedule = new ScheduleModel(classId, module, room, startTime, endTime, day);
                Label responseLabel = new Label();

                if (fieldsCorrect) {
                    Task<String> task = new ScheduleTask(schedule, requestType.toLowerCase());
                    responseLabel.textProperty().bind(task.valueProperty());


                    task.setOnRunning((successEvent) -> {
                        submitButton.setDisable(true);
                    });

                    task.setOnSucceeded((succeededEvent) -> {
                        submitButton.setDisable(false);
                        String result = task.getValue();
                        displayAlert(result);
                        modifyScheduleStage.close();
                        showOptionsScreen();
                    });

                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(task);
                    executorService.shutdown();
                }
            }
        });

        cancelButton.setOnAction(e -> {
            modifyScheduleStage.close();
            showOptionsScreen();
        });
    }

    private void showTimetableScreen() {
        Stage timetableStage = new Stage();
        timetableStage.setTitle("Timetable");

        TextField courseIDField = new TextField();
        courseIDField.setPromptText("Enter Course ID");
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Stage timetableViewStage = new Stage();
                timetableViewStage.setTitle(courseIDField.getText() + " Timetable");

                Task<String> task = new ScheduleTask(courseIDField.getText(), "display");

                task.setOnRunning((successEvent) -> {
                    confirmButton.setDisable(true);
                });

                task.setOnSucceeded((succeededEvent) -> {
                    primaryStage.close();
                    confirmButton.setDisable(false);
                    TimetableFormatting timetableFormatting = new TimetableFormatting(task.getValue());
                    Scene timetableScene = timetableFormatting.createTimetableScene();

                    // Add OK button to revert back to the options scene
                    Button okButton = new Button("OK");
                    okButton.setOnAction(e -> {
                        showOptionsScreen();
                        timetableViewStage.close();
                    });

                    VBox timetableLayout = new VBox(20);
                    timetableLayout.setStyle("-fx-background-color: #839ca3;");
                    timetableLayout.getChildren().addAll(timetableScene.getRoot(), okButton);
                    timetableLayout.setAlignment(Pos.CENTER);
                    timetableLayout.setPadding(new Insets(20));

                    Scene scene = new Scene(timetableLayout, 1000, 800);
                    timetableViewStage.setScene(scene);
                    timetableViewStage.show();
                });

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(task);
                executorService.shutdown();
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
        timetableLayout.setAlignment(Pos.CENTER);
        timetableLayout.setPadding(new Insets(20));

        Scene timetableScene = new Scene(timetableLayout, 400, 300);

        primaryStage.setScene(timetableScene);
        primaryStage.show();
    }

    private void showEarlySchedulingScreen() {
        Stage earlyLecturesStage = new Stage();
        earlyLecturesStage.setTitle("Early Lectures");

        TextField courseIDField = new TextField();
        courseIDField.setPromptText("Enter Course ID");
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String courseID = courseIDField.getText();
                Task<String> task = new ScheduleTask(courseID, "early");

                task.setOnRunning((successEvent) -> {
                    confirmButton.setDisable(true);
                });

                task.setOnSucceeded((succeededEvent) -> {
                    confirmButton.setDisable(false);
                    String result = task.getValue();
                    displayAlert(result);
                    earlyLecturesStage.close();
                    showOptionsScreen();
                });

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(task);
                executorService.shutdown();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> earlyLecturesStage.close());

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(confirmButton, cancelButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox timetableLayout = new VBox(10);
        timetableLayout.setStyle("-fx-background-color: #839ca3;");
        timetableLayout.getChildren().addAll(courseIDField, buttonLayout);

        timetableLayout.setAlignment(Pos.CENTER);
        timetableLayout.setPadding(new Insets(20));

        Scene earlyLecturesScene = new Scene(timetableLayout, 400, 300);

        primaryStage.setScene(earlyLecturesScene);
        primaryStage.show();
    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean checkFields(String classId, String moduleCode, String room, String startTime, String endTime, String day) {
        if (moduleCode.trim().isEmpty() || day.trim().isEmpty() || startTime.trim().isEmpty() || endTime.trim().isEmpty() || room.trim().isEmpty()) {
            displayAlert("Please fill in all fields.");
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localStartTime = LocalTime.parse(startTime, formatter);
        LocalTime localEndTime = LocalTime.parse(startTime, formatter);

        if (localStartTime.isAfter(localEndTime)) {
            displayAlert("Start time cannot be after end time.");
            return false;
        }
        return true;
    }

    private Button createButton(String text, double width, double height, double fontSize, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setPrefSize(width, height);
        button.setFont(Font.font(fontSize));
        button.setOnAction(eventHandler);
        return button;
    }

    private void closeApplication() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Closing Application");
        alert.setHeaderText(null);
        alert.setContentText("Closing application...");
        alert.showAndWait();
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}