package com.example.timetablemanagerclient;

import javafx.concurrent.Task;

public class ScheduleTask extends Task<String> {

    private TimetableController controller;
    private ScheduleModel schedule;
    private String request;
    private String courseID;
    private String response;

    public ScheduleTask(ScheduleModel schedule, String request){
        this.schedule = schedule;
        this.request = request.toLowerCase();
    }

    public ScheduleTask(String courseID, String request){
        this.courseID = courseID;
        this.request = request.toLowerCase();
    }

    public ScheduleTask(){
    }

    @Override
    protected String call() {

        controller = new TimetableController();

        updateMessage("Processing... ");

        switch (request){
            case "add":
                response = addClass(schedule);
                updateValue(response);
                break;
            case "remove":
                response = removeClass(schedule);
                updateValue(response);
                break;
            case "display":
                response = displayClass(courseID);
                updateValue(response);
                break;
            case "early":
                response = requestEarlyLectures(courseID);
                updateValue(response);
                break;
        }
        updateMessage("Done.  ");

        return response;
    }

    @Override
    protected void cancelled(){
        super.cancelled();
        this.updateMessage("Task was cancelled!");
    }

    @Override
    protected void failed(){
        super.failed();
        this.updateMessage("Task failed!");
    }

    @Override
    protected void succeeded(){
        super.succeeded();
        this.updateMessage("Task succeeded!");
    }

    private String addClass(ScheduleModel schedule) {
        return controller.addClass(schedule);
    }

    private String removeClass(ScheduleModel schedule) {
        return controller.removeClass(schedule);
    }

    private String displayClass(String courseID) {
        return controller.displayClass(courseID);
    }

    private String requestEarlyLectures(String courseID){
        return controller.requestEarlyLectures(courseID);
    }

}
