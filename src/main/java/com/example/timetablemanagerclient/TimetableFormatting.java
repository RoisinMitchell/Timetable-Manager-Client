package com.example.timetablemanagerclient;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TimetableFormatting extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create VBox to hold course information and table
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20); // Add spacing above the table
        root.setPadding(new Insets(20)); // Add padding around the VBox

        // Course information
        Label courseNameLabel = new Label("University of Limerick");
        courseNameLabel.setFont(new Font(30)); // Set font size
        Label yearLabel = new Label("Timetable 2024");
        yearLabel.setFont(new Font(25)); // Set font size

        // Add course information to the VBox
        root.getChildren().addAll(courseNameLabel, yearLabel);

        // Create GridPane for timetable
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        // Add column constraints to make the columns fill the available space evenly
        for (int i = 0; i < 6; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            colConstraints.setPrefWidth(100); // Set preferred width for each column
            gridPane.getColumnConstraints().add(colConstraints);

        }

        // Add row constraints to make the rows fill the available space evenly
        for (int i = 0; i < 10; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            rowConstraints.setPrefHeight(50); // Set preferred height for each row
            gridPane.getRowConstraints().add(rowConstraints);

        }

        // Add day labels
        String[] days = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (int i = 0; i < days.length; i++) {
            Label dayLabel = new Label(days[i]);
            dayLabel.setAlignment(Pos.CENTER);
            gridPane.add(dayLabel, i, 0);
            GridPane.setHalignment(dayLabel, HPos.CENTER); // Center horizontally
            GridPane.setValignment(dayLabel, VPos.CENTER); // Center vertically
        }

        // Add time labels
        for (int i = 8; i <= 16; i++) {
            Label timeLabel = new Label((i + 1) + ":00");
            timeLabel.setAlignment(Pos.CENTER);
            gridPane.add(timeLabel, 0, i - 7);
            GridPane.setHalignment(timeLabel, HPos.CENTER); // Center horizontally
            GridPane.setValignment(timeLabel, VPos.CENTER); // Center vertically
        }

        // Set grid lines visible for clarity
        gridPane.setGridLinesVisible(true);

        // Add gridPane to the VBox
        root.getChildren().add(gridPane);

        // Set the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Timetable");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}