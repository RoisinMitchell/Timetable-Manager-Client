package com.example.timetablemanagerclient;

import javafx.concurrent.Task;

import java.io.IOException;

public class ScheduleTask extends Task<String> {

    private ApplicationController controller;
    private ClassSchedule classSchedule;
    private String request;
    private String classID;
    private String response;

    public ScheduleTask(ClassSchedule classSchedule, String request){
        this.classSchedule = classSchedule;
        this.request = request.toLowerCase();
    }

    public ScheduleTask(String classID, String request){
        this.classID = classID;
        this.request = request.toLowerCase();
    }

    public ScheduleTask(){
    }

    @Override
    protected String call() throws Exception {

        controller = new ApplicationController();

        updateMessage("Processing... ");

        switch (request){
            case "add":
                response = addClass(classSchedule);
                updateValue(response);
                break;
            case "remove":
                response = removeClass(classSchedule);
                updateValue(response);
                break;
            case "display":
                response = displayClass(classID);
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

    private String addClass(ClassSchedule classSchedule) throws IOException {
        return controller.addClass(classSchedule);
    }

    private String removeClass(ClassSchedule classSchedule) throws IOException {
        return controller.removeClass(classSchedule);
    }

    private String displayClass(String classID) throws IOException {
        return controller.displayClass(classID);
    }


}
