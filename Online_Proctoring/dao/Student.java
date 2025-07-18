package com.onlinepractoring.dao;

//this class includes getters and setters to modify feilds

public class Student {

    // The `id` field is a unique identifier for the student.
    int id;

    // The `name` field is the student's name.
    String name;

    // The `admissionNumber` field is the student's admission number.
    String admissionNumber;

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

    // Returns the value of the `name` field.
    public String getName() {
        // Get the value of the `name` field.
        return name;
    }

    // Sets the value of the `name` field.
    public void setName(String name) {
        // Set the value of the `name` field.
        this.name = name;
    }

    // Returns the value of the `admissionNumber` field.
    public String getAdmissionNumber() {
        // Get the value of the `admissionNumber` field.
        return admissionNumber;
    }

    // Sets the value of the `admissionNumber` field.
    public void setAdmissionNumber(String admissionNumber) {
        // Set the value of the `admissionNumber` field.
        this.admissionNumber = admissionNumber;
    }
}
