package com.example.timetablemanagerclient;

import java.time.LocalDate;

public class ClassSchedule {
    private String className; // Format: LM051-2022
    private String module; // Format: CS4115
    private String roomNumber; // Format: S205
    private String startTime; // Format: HH:mm
    private String endTime; // Format: HH:mm
    private LocalDate date; // Format: yyyy-mm-dd

    public void setClassName(String className){
        this.className = className;
    }

    public String getClassName(){
        return this.className;
    }

    public void setModule(String module){
        this.module = module;
    }


    public String getModule(){
        return this.module;
    }


    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }


    public String getRoomNumber(){
        return this.roomNumber;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate(){
        return this.date;
    }

}