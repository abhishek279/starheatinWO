package com.starheatingWO.starheatingWO.controller;


import com.starheatingWO.starheatingWO.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batch")
public class BatchController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/export")
    public ResponseEntity<String> exportData() {
        try {
            excelService.exportToExcel("C:\\Users\\shark\\OneDrive\\Documents\\Abhi_Doc\\startheatingWO\\master file\\master.xlsx");
            return ResponseEntity.ok("Data exported successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to export data: " + e.getMessage());
        }
    }
    @GetMapping("/export1")
    public ResponseEntity<String> exportTimesheets() {
        try {
            excelService.exportTimesheetsToExcel("C:\\Users\\shark\\OneDrive\\Documents\\Abhi_Doc\\startheatingWO\\master file\\timeSheet_Master.xlsx");
            return ResponseEntity.ok("Timesheets exported successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to export timesheets: " + e.getMessage());
        }
    }
}