package com.onlinepractoring.dao;
import java.time.LocalDateTime;

public class Incidence {

    // The name of the student who was involved in the incident.
    private String studentName;

    // The time at which the incident occurred.
    private String time;

    // The type of incident that occurred.
    private String incidentType;

    // A unique identifier for the incident.
    private int id;

    // Returns the value of the `id` field.
    public int getId() {
        // Get the value of the `id` field.
        return id;
    }

    // Sets the value of the `id` field.
    public void setId(int id) {
        // Set the value of the `id` field.
        this.id = id;
    }

    // Returns the value of the `studentName` field.
    public String getStudentName() {
        // Get the value of the `studentName` field.
        return studentName;
    }

    // Sets the value of the `studentName` field.
    public void setStudentName(String studentName) {
        // Set the value of the `studentName` field.
        this.studentName = studentName;
    }

    // Returns the value of the `time` field.
    public String getTime() {
        // Get the value of the `time` field.
        return time;
    }

    // Sets the value of the `time` field.
    public void setTime(String time) {
        // Set the value of the `time` field.
        this.time = time;
    }

    // Returns the value of the `incidentType` field.
    public String getIncidentType() {
        // Get the value of the `incidentType` field.
        return incidentType;
    }

    // Sets the value of the `incidentType` field.
    public void setIncidentType(String incidentType) {
        // Set the value of the `incidentType` field.
        this.incidentType = incidentType;
    }
}
