package com.example.timetablemanagerclient;

import java.io.IOException;
import java.net.UnknownHostException;

public class ApplicationController {
    private ClassSchedule model; // The model representing a class schedule
    private ApplicationView view; // The view for displaying information
    private ClientServerConnection connection; // The connection to the server application


    public boolean addClass(ClassSchedule classSchedule) throws IOException {
        this.connection = new ClientServerConnection();
        boolean addClassResult = connection.requestAddClass("add," + classSchedule);
        this.view = new ApplicationView("add", addClassResult);
        return addClassResult;
    }

    public boolean removeClass(ClassSchedule classSchedule) throws IOException {
        this.connection = new ClientServerConnection();
        boolean removeClassResult = connection.requestRemoveClass("remove," + classSchedule);
        this.view = new ApplicationView("remove", removeClassResult);
        return  removeClassResult;
    }

    public String displayClass(String className) throws IOException {
        // Sending String request to the server
        this.connection = new ClientServerConnection();
        String displayClassResult = connection.requestDisplayClass(className);
        this.view = new ApplicationView("display", displayClassResult);
        return displayClassResult;
    }

    public void requestCloseConnection(){
        this.connection = new ClientServerConnection();
        connection.requestCloseConnection();
    }
}