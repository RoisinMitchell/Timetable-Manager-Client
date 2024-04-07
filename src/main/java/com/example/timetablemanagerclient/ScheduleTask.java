package com.example.timetablemanagerclient;

import javafx.concurrent.Task;

import java.io.IOException;

public class ScheduleTask extends Task<String> {

    private ApplicationController controller;
    private ClassSchedule classSchedule;
    private String request;
    private String classID;

    public ScheduleTask(ClassSchedule classSchedule, String request){
        this.classSchedule = classSchedule;
        this.request = request.toLowerCase();
    }

    public ScheduleTask(String classID, String request){
        this.classID = classID;
        this.request = request.toLowerCase();
    }

    @Override
    protected String call() throws Exception {
        String result = "";
        controller = new ApplicationController();

        updateMessage("    Processing... ");

        switch (request){
            case "add":
                result = addClass(classSchedule);
                break;
            case "remove":
                result = removeClass(classSchedule);
                break;
            case "display":
                result = displayClass(classID);
                break;
        }

        updateMessage("    Done.  ");
        return result;
    }

    private String addClass(ClassSchedule classSchedule) throws IOException {
        boolean requestResult = controller.addClass(classSchedule);
        return requestResult ? "Schedule was added" : "Schedule not added";
    }

    private String removeClass(ClassSchedule classSchedule) throws IOException {
        boolean requestResult = controller.removeClass(classSchedule);
        return requestResult ? "Schedule was removed" : "Schedule was not removed";
    }

    private String displayClass(String classID) throws IOException {
        return controller.displayClass(classID);
    }

}
