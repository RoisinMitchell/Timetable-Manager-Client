package com.example.timetablemanagerclient;

import java.io.IOException;
import java.net.UnknownHostException;

public class ApplicationController {
    private ApplicationView view; // The view for displaying information
    private ClientServerConnection connection; // The connection to the server application
    private String serverResponse;


    public String addClass(ClassSchedule classSchedule) throws IOException {
        this.connection = new ClientServerConnection();
        this.serverResponse = connection.requestAddClass("add," + classSchedule);
        this.view = new ApplicationView("add", serverResponse);
        return serverResponse;
    }

    public String removeClass(ClassSchedule classSchedule) throws IOException {
        this.connection = new ClientServerConnection();
        this.serverResponse = connection.requestRemoveClass("remove," + classSchedule);
        this.view = new ApplicationView("remove", serverResponse);
        return  serverResponse;
    }

    public String displayClass(String className) throws IOException {
        // Sending String request to the server
        this.connection = new ClientServerConnection();
        this.serverResponse = connection.requestDisplayClass(className);
        this.view = new ApplicationView("display", serverResponse);
        return serverResponse;
    }

    public String updateView(){
        return view.updateView();
    }

    public void requestCloseConnection(){
        this.connection = new ClientServerConnection();
        connection.requestCloseConnection();
    }
}