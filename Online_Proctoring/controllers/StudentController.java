package com.onlinepractoring.controllers;

import com.onlinepractoring.dao.Student;
import com.onlinepractoring.dao.StudentDaoService;
import com.onlinepractoring.dto.StudentDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//This class is for controlling the student page (displaying student details, checking cookies, and adding new students)
@Controller
public class StudentController {

    //An instance of the studentDaoService class, used to access student data in the database
    StudentDaoService studentDaoService;

    //Constructor to initialize the studentDaoService field to allow the class to interact with the StudentDaoService
    @Autowired
    public StudentController(StudentDaoService studentDaoService) {
        this.studentDaoService = studentDaoService;
    }


    @GetMapping("/student/exam")
    public String exam(HttpServletRequest request, Model model) {
        /*Checks whether there is a cookie name "student-name"
        * if found, it returns "student"
        * else it redirects the client to "/student"
        */

        // Check if the "student-name" cookie exists
        Cookie[] cookies = request.getCookies(); //retrieves all the cookies that are stored in student browser that are associated with our domain
        boolean cookieFound = false; //initializing it to false to use it later for searching if a cookie is found
        if (cookies != null) { //if cookies are found
            for (Cookie cookie : cookies) { //a loop to iterate over each 'Cookie' object in array cookies
                if (cookie.getName().equals("student-name")) { //if the cookie matches the string "student-name"
                    cookieFound = true;
                    break;
                }
            }
        }

        if (cookieFound) {
            return "student"; //if cookie is found it returns the student view
        } else {
            return "redirect:/student"; // Redirects to the exam proctoring page
        }
    }


    //function to handle the HTTP GET request on the "student" endpoint
    @GetMapping("/student")
    public String student(HttpServletRequest request, Model model) { //HTTP request (endpoint)
        /*
         * HttpServletRequest request: an HTTP request to get access on the resources
         * resources such as (headers, methods, URL)
         * Model model: sends the data to the view/webpage
         */
        return "student-details"; //returns the student-details page
    }


    //Function to add a new student to the database
    @PostMapping("/student")
    public String postStudent( StudentDetails studentDetails, BindingResult bindingResult, HttpServletResponse response) {

        //if there are any errors in the binding result
        if (bindingResult.hasErrors()) { //The bindingResult checks if the data entered violates the rules of the fields
            System.out.println("Error"+bindingResult.getAllErrors()); //prints the errors
            return "student-details"; //returns the "student-details" view

        }
        //if no errors are found in the binding result
        else {
            Student student = new Student(); //creates a new student object
            student.setName(studentDetails.getStudentName()); //sets the student name of the student object
            student.setAdmissionNumber(studentDetails.getAdmissionNumber()); //sets the admission number of the student object


            // Creating cookies to remember the student across the all the pages

            //cookie name is "student-name" and its value is the admission number
            Cookie cookie = new Cookie("student-name", student.getAdmissionNumber());

            // Set additional properties for the cookie (optional)
            cookie.setMaxAge(3600); // Set cookie expiration time
            cookie.setPath("/"); // Set the cookie path, cookie will be sent will all requests.

            // Add the cookie to the response
            response.addCookie(cookie);

            return "redirect:/student/exam"; //redirects to the student exam page
        }
    }

}