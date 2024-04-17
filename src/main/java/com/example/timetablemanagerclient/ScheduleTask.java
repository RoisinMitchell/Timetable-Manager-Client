package com.example.timetablemanagerclient;

import javafx.concurrent.Task;

import java.io.IOException;

public class ScheduleTask extends Task<String> {

    private TimetableManagerController controller;
    private ClassScheduleModel classScheduleModel;
    private String request;
    private String classID;
    private String response;

    public ScheduleTask(ClassScheduleModel classScheduleModel, String request){
        this.classScheduleModel = classScheduleModel;
        this.request = request.toLowerCase();
    }

    public ScheduleTask(String classID, String request){
        this.classID = classID;
        this.request = request.toLowerCase();
    }

    public ScheduleTask(){
    }

    @Override
    protected String call() {

        controller = new TimetableManagerController();

        updateMessage("Processing... ");

        switch (request){
            case "add":
                response = addClass(classScheduleModel);
                updateValue(response);
                break;
            case "remove":
                response = removeClass(classScheduleModel);
                updateValue(response);
                break;
            case "display":
                response = displayClass(classID);
                updateValue(response);
                break;
            case "early":
                response = requestEarlyLectures(classID);
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

    private String addClass(ClassScheduleModel classScheduleModel) {
        return controller.addClass(classScheduleModel);
    }

    private String removeClass(ClassScheduleModel classScheduleModel) {
        return controller.removeClass(classScheduleModel);
    }

    private String displayClass(String courseID) {
        return controller.displayClass(courseID);
    }

    private String requestEarlyLectures(String courseID){
        return controller.requestEarlyLectures(courseID);
    }

}
