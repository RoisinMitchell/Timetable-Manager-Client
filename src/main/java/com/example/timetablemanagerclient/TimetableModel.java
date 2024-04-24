package com.example.timetablemanagerclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

// The TimetableModel class is responsible for establishing a connection with the server and sending requests
public class TimetableModel {
    // The port number to connect to the server
    private static final int PORT = 1234;
    // The output stream to send data to the server
    private DataOutputStream dataOutputStream;
    // The input stream to receive data from the server
    private DataInputStream dataInputStream;

    // The constructor initialises the connection with the server and the data streams
    public TimetableModel() {
        try {
            // Get the local host
            InetAddress host = InetAddress.getLocalHost();
            // Establish a socket connection with the server
            Socket connection = new Socket(host, PORT);
            // Initialise the data output stream
            dataOutputStream = new DataOutputStream(connection.getOutputStream());
            // Initialise the data input stream
            dataInputStream = new DataInputStream(connection.getInputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // The sendRequest method sends a request to the server and returns the response
    public String sendRequest(String request){
        try {
            // Write the request to the data output stream
            dataOutputStream.writeUTF(request);
            // Flush the data output stream to ensure the data is sent
            dataOutputStream.flush();
            // Read and return the response from the data input stream
            return dataInputStream.readUTF();
        } catch (IOException exception) {
            // Print the stack trace for debugging purposes if an exception occurs
            exception.printStackTrace();
        }
        // Return a default response if no response is received from the server
        return "No response received from server!";
    }

    // The requestAddClass method sends a request to add a class to the server
    public String requestAddClass(String classSchedule){
        System.out.println("\nSending add class request to the server...\n");
        // Send the request and return the response
        return sendRequest(classSchedule);
    }

    // The requestRemoveClass method sends a request to remove a class to the server
    public String requestRemoveClass(String classSchedule) {
        System.out.println("\nSending remove class request to the server...\n");
        // Send the request and return the response
        return sendRequest(classSchedule);
    }

    // The requestDisplayClass method sends a request to display a class to the server
    public String requestDisplayClass(String courseID) {
        System.out.println("\nSending display class request to the server...\n");
        // Send the request and return the response
        return sendRequest(courseID);
    }

    // The requestEarlyLectures method sends a request to get early lectures to the server
    public String requestEarlyLectures(String courseID) {
        System.out.println("\nSending early lecture request to the server...\n");
        // Send the request and return the response
        return sendRequest(courseID);
    }
}