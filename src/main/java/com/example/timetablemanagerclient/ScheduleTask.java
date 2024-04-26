package com.example.timetablemanagerclient;

import javafx.concurrent.Task;

// ScheduleTask class extends the JavaFX Task class to perform certain operations in a separate thread
public class ScheduleTask extends Task<String> {

    // Instance of TimetableController to perform operations
    private TimetableController controller;
    private ScheduleModel schedule;
    private String request;
    private String courseID;

    public ScheduleTask(ScheduleModel schedule, String request){
        this.schedule = schedule;
        this.request = request.toLowerCase();
    }

    public ScheduleTask(String courseID, String request){
        this.courseID = courseID;
        this.request = request.toLowerCase();
    }

    // The call method is overridden from the Task class. It is the method that is executed in the separate thread.
    @Override
    protected String call() {
        // Initialise the controller
        controller = new TimetableController();
        // Update the message property of the Task
        updateMessage("Processing... ");

        // Perform the operation based on the request and store the response
        String response = switch (request) {
            case "add" -> controller.addClass(schedule);
            case "remove" -> controller.removeClass(schedule);
            case "display" -> controller.displayClass(courseID);
            case "early" -> controller.requestEarlyLectures(courseID);
            default -> "";
        };

        // Update the message property of the Task
        updateMessage("Done.  ");
        // Return the response
        return response;
    }
}