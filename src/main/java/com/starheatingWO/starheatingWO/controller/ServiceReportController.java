package com.starheatingWO.starheatingWO.controller;

import com.starheatingWO.starheatingWO.model.ServiceReport;
import com.starheatingWO.starheatingWO.service.ServiceReportService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@CrossOrigin(origins = "https://starheating-wo-git-master-abhishek279s-projects.vercel.app")
@RestController
@RequestMapping("/api/serviceReports")
public class ServiceReportController {

    @Autowired
    private ServiceReportService serviceReportService;

    // Endpoint to submit the form
    @PostMapping
    public ResponseEntity<String> submitForm(@RequestBody ServiceReport form) throws Exception {
        // Save the form data
        ServiceReport savedForm = serviceReportService.createAndSendReport(form);

//        // Send email to the customer
//        try {
//            serviceReportService.sendEmailWithPdf(savedForm);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        return ResponseEntity.status(HttpStatus.CREATED).body("Form submitted successfully.");
    }

}
