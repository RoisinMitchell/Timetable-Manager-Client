package com.example.timetablemanagerclient;

public class ScheduleModel {
    private String courseID; // Format: LM051-2022
    private String module; // Format: CS4115
    private String room; // Format: S205
    private String startTime; // Format: HH:mm
    private String endTime; // Format: HH:mm
    private String day; // Format: yyyy-mm-dd

    public ScheduleModel(String courseID, String module, String room, String startTime, String endTime, String day){
        this.courseID = courseID;
        this.module = module;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public ScheduleModel(){

    }

    public void setCourseID(String className){
        this.courseID = className;
    }

    public String getCourseID(){
        return this.courseID;
    }

    public void setModule(String module){
        this.module = module;
    }


    public String getModule(){
        return this.module;
    }


    public void setRoom(String room){
        this.room = room;
    }


    public String getRoom(){
        return this.room;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay(){
        return this.day;
    }

    @Override
    public String toString(){
        return courseID + "," + module + "," + room + "," + startTime + "," + endTime + "," + day;
    }

}