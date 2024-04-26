package com.example.timetablemanagerclient;

// The TimetableController class is responsible for handling requests from the user interface
public class TimetableController {
    private final TimetableModel timetableModel = TimetableModel.getInstance();

    public String addClass(ScheduleModel schedule) {
        return timetableModel.requestAddClass("add," + schedule);
    }

    public String removeClass(ScheduleModel schedule){
        return timetableModel.requestRemoveClass("remove," + schedule);
    }

    public String displayClass(String courseID){
        return timetableModel.requestDisplayClass("display," + courseID);
    }

    public String requestEarlyLectures(String courseID){
        return timetableModel.requestEarlyLectures("early," + courseID);
    }
}
