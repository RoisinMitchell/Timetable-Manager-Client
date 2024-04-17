package com.example.timetablemanagerclient;

import java.time.LocalDate;

public class TimetableFormatter {

    public static String formatTimetable(String timetable) {
        StringBuilder formattedTimetable = new StringBuilder();

        // Append course ID and current year
        formattedTimetable.append("Course ID: ").append("Course Name").append("\n");
        formattedTimetable.append("Year: ").append(LocalDate.now().getYear()).append("\n\n");

        // Split the timetable into lines
        String[] lines = timetable.split("\n");

        // Iterate over each line and format it
        for (String line : lines) {
            formattedTimetable.append(formatTimetableLine(line)).append("\n");
        }

        return formattedTimetable.toString();
    }

    private static String formatTimetableLine(String line) {
        // Split the line into its components
        String[] parts = line.split(" - ");

        // Format each component
        StringBuilder formattedLine = new StringBuilder();
        formattedLine.append(parts[0]).append(" - "); // Course ID
        formattedLine.append(parts[1]).append(" - "); // Course Code
        formattedLine.append(parts[2]).append(" - "); // Room
        formattedLine.append(parts[3]).append(" - "); // Start Time
        formattedLine.append(parts[4]).append(" - "); // End Time
        formattedLine.append(parts[5]); // Day

        return formattedLine.toString();
    }

}
