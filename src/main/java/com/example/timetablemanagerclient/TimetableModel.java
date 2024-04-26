package com.example.timetablemanagerclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TimetableModel {
    private static final int PORT = 1234;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private static TimetableModel instance;

    private TimetableModel() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket connection = new Socket(host, PORT);
            dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataInputStream = new DataInputStream(connection.getInputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static TimetableModel getInstance() {
        if (instance == null) {
            instance = new TimetableModel();
        }
        return instance;
    }

    public String sendRequest(String request){
        try {
            dataOutputStream.writeUTF(request);
            dataOutputStream.flush();
            return dataInputStream.readUTF();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "No response received from server!";
    }

    public String requestAddClass(String classSchedule){
        System.out.println("\nSending add class request to the server...\n");
        return sendRequest(classSchedule);
    }

    public String requestRemoveClass(String classSchedule) {
        System.out.println("\nSending remove class request to the server...\n");
        return sendRequest(classSchedule);
    }

    public String requestDisplayClass(String courseID) {
        System.out.println("\nSending display class request to the server...\n");
        return sendRequest(courseID);
    }

    public String requestEarlyLectures(String courseID) {
        System.out.println("\nSending early lecture request to the server...\n");
        return sendRequest(courseID);
    }
}