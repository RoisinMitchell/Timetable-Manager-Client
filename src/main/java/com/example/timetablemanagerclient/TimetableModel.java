package com.example.timetablemanagerclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TimetableModel {
    private static InetAddress host;
    private static final int PORT = 1234;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    public TimetableModel() {
        try {
            host = InetAddress.getLocalHost();

        } catch (IOException e) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }

        try {
            Socket connection = new Socket(host, PORT);
            dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataInputStream = new DataInputStream(connection.getInputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String requestAddClass(String classSchedule){

        try {
            System.out.println("\nSending add class request to the server...\n");
            dataOutputStream.writeUTF(classSchedule);
            dataOutputStream.flush();

            return dataInputStream.readUTF();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "No response received from server!";
    }

    public String requestRemoveClass(String classSchedule) {

        try {
            System.out.println("\nSending remove class request to the server...\n");
            dataOutputStream.writeUTF(classSchedule);
            dataOutputStream.flush();

            return dataInputStream.readUTF();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "No response received from server!";
    }

    public String
    requestDisplayClass(String courseID) {
        try {
            System.out.println("\nSending display class request to the server...\n");
            dataOutputStream.writeUTF(courseID);
            dataOutputStream.flush();

            return dataInputStream.readUTF();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return  "No response received from server!";
    }

    public String requestEarlyLectures(String courseID) {
        try {
            System.out.println("\nSending early lecture request to the server...\n");
            dataOutputStream.writeUTF(courseID);
            dataOutputStream.flush();

            return dataInputStream.readUTF();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return  "No response received from server!";
    }

}
