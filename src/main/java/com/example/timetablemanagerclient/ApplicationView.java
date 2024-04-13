package com.example.timetablemanagerclient;

public class ApplicationView {

    private String serverResponse; // The response received from the server
    private String requestType; // The type of request (e.g., "add", "remove", "display")

    public ApplicationView(String requestType, String response) {
        this.serverResponse = response;
        this.requestType = requestType;
    }

    public String updateView() {
        System.out.println(serverResponse);
        return serverResponse;
    }
}
