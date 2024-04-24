package com.example.timetablemanagerclient;

// The TimetableController class is responsible for handling requests from the user interface
public class TimetableController {
    // An instance of the TimetableModel class to handle the communication with the server
    private TimetableModel timetableModel = new TimetableModel();

    // The addClass method sends a request to add a class to the server
    public String addClass(ScheduleModel schedule) {
        // The request is formatted as a string and sent to the server
        return timetableModel.requestAddClass("add," + schedule);
    }

    // The removeClass method sends a request to remove a class from the server
    public String removeClass(ScheduleModel schedule){
        // The request is formatted as a string and sent to the server
        return timetableModel.requestRemoveClass("remove," + schedule);
    }

    // The displayClass method sends a request to display a class from the server
    public String displayClass(String courseID){
        // The request is formatted as a string and sent to the server
        return timetableModel.requestDisplayClass("display," + courseID);
    }

    // The requestEarlyLectures method sends a request to get early lectures from the server
    public String requestEarlyLectures(String courseID){
        // The request is formatted as a string and sent to the server
        return timetableModel.requestEarlyLectures("early," + courseID);
    }
}