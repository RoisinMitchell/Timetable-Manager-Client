package com.example.timetablemanagerclient;

public class ClassScheduleModel {
    private String classId; // Format: LM051-2022
    private String module; // Format: CS4115
    private String room; // Format: S205
    private String startTime; // Format: HH:mm
    private String endTime; // Format: HH:mm
    private String date; // Format: yyyy-mm-dd

    public ClassScheduleModel(String classId, String module, String room, String startTime, String endTime, String date){
        this.classId = classId;
        this.module = module;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public void setClassId(String className){
        this.classId = className;
    }

    public String getClassId(){
        return this.classId;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    @Override
    public String toString(){
        return classId + "," + module + "," + room + "," + startTime + "," + endTime + "," + date;
    }

}