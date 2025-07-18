package com.onlinepractoring.dto;

import java.time.LocalDateTime;

public class IncidenceDetails {

    // This class represents the details of an incident.

    // The student name.
    private String studentName;

    // The time of the incident.
    private String time;

    // The type of incident.
    private String incidentType;

    // The student ID.
    private String studentId;

    // Sets the student name.
    public void setStudentName(String studentName) {
        // This line sets the student name.
        this.studentName = studentName;
    }

    // Gets the student name.
    public String getStudentName() {
        // This line gets the student name.
        return studentName;
    }

    // Sets the time of the incident.
    public void setTime(String time) {
        // This line sets the time of the incident.
        this.time = time;
    }

    // Gets the time of the incident.
    public String getTime() {
        // This line gets the time of the incident.
        return time;
    }

    // Sets the type of incident.
    public void setIncidentType(String incidentType) {
        // This line sets the type of incident.
        this.incidentType = incidentType;
    }

    // Gets the type of incident.
    public String getIncidentType() {
        // This line gets the type of incident.
        return incidentType;
    }

    // Sets the student ID.
    public void setStudentId(String studentId) {
        // This line sets the student ID.
        this.studentId = studentId;
    }

    // Gets the student ID.
    public String getStudentId() {
        // This line gets the student ID.
        return studentId;
    }
}
