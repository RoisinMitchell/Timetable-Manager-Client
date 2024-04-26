package com.example.timetablemanagerclient;

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
import java.time.DayOfWeek;
import java.util.*;


public class TimetableFormatter {
    private final Map<String, Map<String, ScheduleModel>> schedulesByDayAndTime;
    private String courseID;

    // The constructor initialises the schedules map and maps the input schedules to ScheduleModel objects
    public TimetableFormatter(String schedules) {
        this.schedulesByDayAndTime = new HashMap<>();

        // Initialise the schedules map with an empty map for each day of the week
        for (DayOfWeek day : DayOfWeek.values()) {
            schedulesByDayAndTime.put(day.toString().toUpperCase(), new HashMap<>());
        }

        // Map the input schedules to ScheduleModel objects
        mapToScheduleObjects(schedules);
    }

    private void addToScheduleMap(ScheduleModel schedule) {
        Map<String, ScheduleModel> schedulesByTime = schedulesByDayAndTime.get(schedule.getDay().toUpperCase());
        String timeKey = schedule.getStartTime().toString();
        schedulesByTime.put(timeKey, schedule);
        schedulesByDayAndTime.put(schedule.getDay().toUpperCase(), schedulesByTime);
    }

    // The mapToScheduleObjects method maps a string of schedules to ScheduleModel objects
    private void mapToScheduleObjects(String schedulesString) {
        String[] schedulesByLine = schedulesString.split("-");

        for(int i = 0; i < schedulesByLine.length; i++){
            ScheduleModel schedule = new ScheduleModel();
            String[] scheduleElements = schedulesByLine[i].split(",");
            this.courseID = scheduleElements[0].trim().toUpperCase();

            // Set the properties of the schedule from the schedule elements
            schedule.setCourseID(scheduleElements[0].trim().toUpperCase());
            schedule.setModule(scheduleElements[1].trim().toUpperCase());
            schedule.setRoom(scheduleElements[2].trim().toUpperCase());
            schedule.setStartTime(scheduleElements[3].trim().toUpperCase());
            schedule.setEndTime(scheduleElements[4].trim().toUpperCase());
            schedule.setDay(scheduleElements[5].trim().toUpperCase());

            // Add the schedule to the schedules map
            addToScheduleMap(schedule);
        }
    }

    // The getScheduleByDayAndTime method returns a schedule for a given day and time
    public ScheduleModel getScheduleByDayAndTime(String day, String time) {
        Map<String, ScheduleModel> schedulesByTime = schedulesByDayAndTime.get(day.toUpperCase());
        return schedulesByTime.get(time);
    }

    // The createTimetableScene method creates a scene for the timetable
    public Scene createTimetableScene() {
        // Create VBox to hold course information and table
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20); // Add spacing above the table
        root.setPadding(new Insets(20)); // Add padding around the VBox

        // Create and configure the label for the course information
        Label yearLabel = new Label(courseID + " - Timetable 2024");
        yearLabel.setFont(new Font(35)); // Set font size

        // Add course information to the VBox
        root.getChildren().addAll(yearLabel);

        // Create and configure the GridPane for the timetable
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        // Add column constraints to make the columns fill the available space evenly
        for (int i = 0; i < 6; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            colConstraints.setPrefWidth(100);
            gridPane.getColumnConstraints().add(colConstraints);
        }

        // Add row constraints to make the rows fill the available space evenly
        for (int i = 0; i < 10; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            rowConstraints.setPrefHeight(50);
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

        // Populate timetable with schedules
        for (int dayIndex = 1; dayIndex <= 5; dayIndex++) { // Loop through Monday to Friday
            String day = DayOfWeek.of(dayIndex).name().toUpperCase();

            for (int hour = 9; hour <= 16; hour++) { // Loop through 9:00 to 16:00
                String timeSlot = String.format("%02d:00", hour);
                ScheduleModel schedule = getScheduleByDayAndTime(day, timeSlot);

                if (schedule != null) {
                    // Calculate the end hour of the schedule
                    int endHour = hour + schedule.getDuration();

                    // Add the schedule to each hour slot it spans
                    for (int i = hour; i < endHour; i++) {
                        String currentSlot = String.format("%02d:00", i);
                        Label scheduleLabel = new Label(schedule.getModule() + " - " + schedule.getRoom());
                        scheduleLabel.setFont(new Font(15));
                        scheduleLabel.setAlignment(Pos.CENTER);

                        gridPane.add(scheduleLabel, dayIndex, i - 8); // Adjusted index by subtracting 8
                        GridPane.setHalignment(scheduleLabel, HPos.CENTER);
                        GridPane.setValignment(scheduleLabel, VPos.CENTER);
                    }
                }
            }
        }

        root.getChildren().add(gridPane);

        return new Scene(root, 1000, 800);
    }
}
