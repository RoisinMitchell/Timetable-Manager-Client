package com.example.timetablemanagerclient;

public class ApplicationView {

    private String serverResponse; // The response received from the server
    private String requestType; // The type of request (e.g., "add", "remove", "display")
    private boolean requestResult; // The result of the request (true if successful, false otherwise)

    public ApplicationView(String requestType, boolean requestResult) {
        this.requestResult = requestResult;
        this.requestType = requestType;
    }

    public ApplicationView(String requestType, String response) {
        this.serverResponse = response;
        this.requestType = requestType;
    }

    public String updateView() {
        if (requestType.equalsIgnoreCase("add")) {
            if (requestResult) {
                serverResponse = "The schedule was added!";
                System.out.println("\nThe schedule was added!");
            } else {
                serverResponse = "The schedule was not added!";
                System.out.println("\nThe schedule was not added!");
            }
        }
        if (requestType.equalsIgnoreCase("remove")) {
            if (requestResult) {
                serverResponse = "The schedule was removed!";
                System.out.println("\nThe schedule was removed!");
            } else {
                serverResponse = "The schedule was not found!";
                System.out.println("\nThe schedule was not removed!");
            }
        }
        return serverResponse;
    }
}
