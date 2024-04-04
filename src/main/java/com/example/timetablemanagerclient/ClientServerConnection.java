package com.example.timetablemanagerclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientServerConnection {
    private static InetAddress host;
    private static final int PORT = 1234;
    private Socket connection;

    public ClientServerConnection() {
        // Finding local host address
        try {
            host = InetAddress.getLocalHost();

        } catch (IOException e) {
            System.out.println("Host ID not found!");
            System.exit(1); // Exit application if host ID is not found
        }

        // Establishing socket connection
        try {
            connection = new Socket(host, PORT);
        } catch (IOException exception) {
            exception.printStackTrace(); // Print stack trace if connection fails
        }
    }

    public boolean requestAddClass(String classSchedule) throws IOException {
        boolean result = false;
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
            System.out.println("Sending add class request to the server...\n");
            dataOutputStream.writeUTF(classSchedule);
            dataOutputStream.flush();

            String serverResponse = dataInputStream.readUTF();

            result = serverResponse.equalsIgnoreCase("true");

            if(!result){
                System.out.println(serverResponse);
            }

            dataOutputStream.close();
            dataInputStream.close();
            connection.close();

        } catch (IOException exception) {
            exception.printStackTrace(); // Print stack trace if an I/O error occurs
        }
        return result;
    }

    public boolean requestRemoveClass(String classSchedule) {
        boolean result = false;
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
            System.out.println("Sending remove class request to the server...\n");
            dataOutputStream.writeUTF(classSchedule);
            dataOutputStream.flush();

            String serverResponse = dataInputStream.readUTF();

            result = serverResponse.equalsIgnoreCase("true");

            if(!result){
                System.out.println(serverResponse);
            }

            dataOutputStream.close();
            dataInputStream.close();
            connection.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public String requestDisplayClass(String className) {
        String serverResponse = "";
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
            System.out.println("Sending display class request to the server...\n");
            dataOutputStream.writeUTF(className);
            dataOutputStream.flush();

            serverResponse = dataInputStream.readUTF();

            dataOutputStream.close();
            dataInputStream.close();
            connection.close();
        } catch (IOException exception) {
            exception.printStackTrace(); // Print stack trace if an I/O error occurs
        }
        return serverResponse;
    }

    public void requestCloseConnection() {
        try {
            System.out.println("\n* Closing connection... *");
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeUTF("close");
            dataOutputStream.close();

        } catch (IOException exception) {
            System.out.println("Unable to disconnect!"); // Notify if unable to disconnect
            exception.printStackTrace(); // Print stack trace if an I/O error occurs during disconnection
        }
    }
}
