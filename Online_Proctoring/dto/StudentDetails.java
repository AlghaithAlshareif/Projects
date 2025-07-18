package com.onlinepractoring.dto;

public class StudentDetails {

    // This class represents the details of a student.

    // The student's name.
    private String studentName;

    // The student's admission number.
    private String admissionNumber;

    // Gets the student's name.
    public String getStudentName() {
        // This line gets the student's name.
        return studentName;
    }

    // Sets the student's name.
    public void setStudentName(String studentName) {
        // This line sets the student's name.
        this.studentName = studentName;
    }

    // Gets the student's admission number.
    public String getAdmissionNumber() {
        // This line gets the student's admission number.
        return admissionNumber;
    }

    // Sets the student's admission number.
    public void setAdmissionNumber(String admissionNumber) {
        // This line sets the student's admission number.
        this.admissionNumber = admissionNumber;
    }
}
