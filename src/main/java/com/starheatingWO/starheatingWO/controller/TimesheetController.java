package com.starheatingWO.starheatingWO.controller;


import com.starheatingWO.starheatingWO.model.Timesheet;
import com.starheatingWO.starheatingWO.model.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://starheating-wo-git-master-abhishek279s-projects.vercel.app")
@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @PostMapping
    public ResponseEntity<Timesheet> createTimesheet(@RequestBody Timesheet timesheet) {
        return ResponseEntity.ok(timesheetService.saveTimesheet(timesheet));
    }

    @GetMapping
    public ResponseEntity<List<Timesheet>> getAllTimesheets() {
        return ResponseEntity.ok(timesheetService.getAllTimesheets());
    }
}