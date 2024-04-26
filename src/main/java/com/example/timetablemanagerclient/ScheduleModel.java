package com.example.timetablemanagerclient;

import java.time.Duration;
import java.time.LocalTime;

public class ScheduleModel {
    private String courseID;
    private String module;
    private String room;
    private LocalTime startTime;
    private LocalTime endTime;
    private String day;

    public ScheduleModel(String courseID, String module, String room, String startTime, String endTime, String day){
        this.courseID = courseID;
        this.module = module;
        this.room = room;
        setStartTime(startTime);
        setEndTime(endTime);
        this.day = day;
    }

    public ScheduleModel(){
    }

    public void setCourseID(String className){
        this.courseID = className;
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
        String[] timeParts = startTime.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        this.startTime = LocalTime.of(hour, 0);
    }

    public LocalTime getStartTime(){
        return this.startTime;
    }

    public void setEndTime(String endTime){
        String[] timeParts = endTime.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        this.endTime = LocalTime.of(hour, 0);
    }

    public int getDuration(){
        Duration duration = Duration.between(startTime, endTime);
        return duration.toHoursPart();
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