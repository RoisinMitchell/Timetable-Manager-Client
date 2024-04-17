package com.example.timetablemanagerclient;

public class TimetableManagerController {
    private TimetableManagerModel connection;
    private String serverResponse;

    public String addClass(ClassScheduleModel classScheduleModel) {
        this.connection = new TimetableManagerModel();
        this.serverResponse = connection.requestAddClass("add," + classScheduleModel);
        return serverResponse;
    }

    public String removeClass(ClassScheduleModel classScheduleModel){
        this.connection = new TimetableManagerModel();
        this.serverResponse = connection.requestRemoveClass("remove," + classScheduleModel);
        return  serverResponse;
    }

    public String displayClass(String courseID){
        this.connection = new TimetableManagerModel();
        this.serverResponse = connection.requestDisplayClass("display," + courseID);
        return serverResponse;
    }

    public String requestEarlyLectures(String courseID){
        this.connection = new TimetableManagerModel();
        this.serverResponse = connection.requestEarlyLectures("display," + courseID);
        return serverResponse;
    }
}