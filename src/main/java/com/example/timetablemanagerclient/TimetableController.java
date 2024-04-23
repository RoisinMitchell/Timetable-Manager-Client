package com.example.timetablemanagerclient;

public class TimetableController {
    private TimetableModel connection;
    private String serverResponse;

    public String addClass(ScheduleModel schedule) {
        this.connection = new TimetableModel();
        this.serverResponse = connection.requestAddClass("add," + schedule);
        return serverResponse;
    }

    public String removeClass(ScheduleModel schedule){
        this.connection = new TimetableModel();
        this.serverResponse = connection.requestRemoveClass("remove," + schedule);
        return  serverResponse;
    }

    public String displayClass(String courseID){
        this.connection = new TimetableModel();
        this.serverResponse = connection.requestDisplayClass("display," + courseID);
        return serverResponse;
    }

    public String requestEarlyLectures(String courseID){
        this.connection = new TimetableModel();
        this.serverResponse = connection.requestEarlyLectures("early," + courseID);
        return serverResponse;
    }
}