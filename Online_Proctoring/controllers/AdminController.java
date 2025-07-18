package com.onlinepractoring.controllers;

// importing other classes
import com.onlinepractoring.dao.Incidence;
import com.onlinepractoring.dao.IncidenceDaoService;
import com.onlinepractoring.dao.StudentDaoService;
import com.onlinepractoring.dto.IncidenceDetails;
// import from spring framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

// This class is for controlling the Admin page ( display the table of incidents )
@Controller // Handled HTTP requests and generates HTTP responses through @GetMapping and @PostMapping
public class AdminController {
    // this is used to access the incidents from the database
    IncidenceDaoService incidenceDaoService;

    // initialize the incident
    @Autowired // Automatically manages the usage of all beans
    public AdminController(IncidenceDaoService incidenceDaoService) {
        this.incidenceDaoService = incidenceDaoService;
    }
    @GetMapping("/admin") // Fetches data
    public String admin(Model model) { // send the data to the web page.
        List<Incidence> incidences = incidenceDaoService.getAllIncidences();
        System.out.println(incidences.size());
        model.addAttribute("incidents", incidences);
        return "admin";
    }

    @PostMapping("/admin/report") // this handles post requests ( Sends data ).
    public String adminReport(IncidenceDetails incidenceDetails, BindingResult bindingResult, Model model) {
        /* this method handles potential errors, it receives an ‘IncidenceDetails’ object
        and a ‘BindingResult’ object. The ‘BindingResult’ object is used to check if there were
        any errors during the binding of request parameters to the ‘IncidenceDetails’ object.
        if the incoming data correctly matches the fields in ‘IncidenceDetails’.*/
        if(bindingResult.hasErrors()) return "{ error: \"" +bindingResult.toString() + "\"}"; // this will be executed if there was an error
        Incidence incidence = new Incidence();
        incidence.setStudentName(incidenceDetails.getStudentName());
        incidence.setTime(incidenceDetails.getTime());
        incidence.setIncidentType(incidenceDetails.getIncidentType());
        incidenceDaoService.addIncidence(incidence);
        System.out.println("Done");
        return "success";
    }
}