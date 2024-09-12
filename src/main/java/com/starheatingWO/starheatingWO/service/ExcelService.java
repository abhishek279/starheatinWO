package com.starheatingWO.starheatingWO.service;

import com.starheatingWO.starheatingWO.model.ServiceReport;
import com.starheatingWO.starheatingWO.model.Timesheet;
import com.starheatingWO.starheatingWO.repository.ServiceReportRepository;
import com.starheatingWO.starheatingWO.repository.TimesheetRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private ServiceReportRepository repository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Scheduled(cron = "0 0 20 * * ?") // Every day at 8 PM
    public void performBatchExport() throws InvalidFormatException {
        System.out.println("Batch Export Job started at: " + new java.util.Date());
        try {
            exportToExcel("C:\\path\\to\\your\\excel\\file.xlsx");
            System.out.println("Batch Export Job exportToExcel completed successfully at: " + new java.util.Date());
            exportTimesheetsToExcel("C:\\Users\\shark\\OneDrive\\Documents\\Abhi_Doc\\startheatingWO\\master file\\timeSheet_Master.xlsx");
            System.out.println("Batch Export Job exportTimesheetsToExcel completed successfully at: " + new java.util.Date());
        } catch (IOException e) {
            System.err.println("Error during Batch Export: " + e.getMessage());
        }
    }

    @Transactional
    public void exportToExcel(String path) throws IOException, InvalidFormatException {
        List<ServiceReport> reports = repository.findByExportedFalse();
        if (reports.isEmpty()) {
            System.out.println("No new reports to export.");
            return;
        }

        Workbook workbook;
        Sheet sheet;
        int rowIdx;

        // Check if the file already exists
        File file = new File(path);
        if (file.exists()) {
            // If the file exists, open it and continue appending data
            FileInputStream inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheet("Service Reports");
            if (sheet == null) { // If no sheet exists, create one
                sheet = workbook.createSheet("Service Reports");
            }
            rowIdx = sheet.getLastRowNum() + 1; // Get the index for the next available row
            inputStream.close(); // Close the input stream after reading the file
        } else {
            // If the file does not exist, create a new workbook and sheet
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Service Reports");

            // Create a header row (optional, only if the file is new)
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Job Site Name", "Billing Name", "Job Site Address", "Billing Address", "Date",
                    "Customer PO", "Equipment Make", "Model", "Serial Number", "Gas Pressure Inlet",
                    "Gas Pressure WC", "Gas Pressure Manifold", "CO Test", "CO Readings", "Total Material",
                    "Total Labour", "Travel Charges", "Subtotal", "Tax", "Total", "Recommendations",
                    "Email", "CO Test Completed", "Reason For Call", "Heat Exchanger Washed",
                    "Heat Exchanger Cleaned", "Heat Exchanger Inspected", "Refractory Tested",
                    "Refractory Set", "Operator Tested", "Operator Set", "High Limit Tested",
                    "High Limit Set", "Water Pressure Tested", "Water Pressure Set", "Flow Switch Tested",
                    "Pressure Switch Tested", "Quantity", "Material", "Unit Price"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
            rowIdx = 1; // Start adding data from the first row after the header
        }

        // Iterate through the reports to populate the sheet
        for (ServiceReport report : reports) {
            Row row = sheet.createRow(rowIdx++);
            // Populate the row with data from the report
            row.createCell(0).setCellValue(report.getJobSiteName());
            row.createCell(1).setCellValue(report.getBillingName());
            row.createCell(2).setCellValue(report.getJobSiteAddress());
            row.createCell(3).setCellValue(report.getBillingAddress());
            row.createCell(4).setCellValue(report.getDate().toString());
            row.createCell(5).setCellValue(report.getCustomerPO());
            row.createCell(6).setCellValue(report.getEquipmentMake());
            row.createCell(7).setCellValue(report.getModel());
            row.createCell(8).setCellValue(report.getSerialNumber());
            row.createCell(9).setCellValue(report.getGasPressureInlet().doubleValue());
            row.createCell(10).setCellValue(report.getGasPressureWC().doubleValue());
            row.createCell(11).setCellValue(report.getGasPressureManifold().doubleValue());
            row.createCell(12).setCellValue(report.getCoTest() ? "Yes" : "No");
            row.createCell(13).setCellValue(report.getCoReadings());
            row.createCell(14).setCellValue(report.getTotalMaterial().doubleValue());
            row.createCell(15).setCellValue(report.getTotalLabour().doubleValue());
            row.createCell(16).setCellValue(report.getTravelCharges().doubleValue());
            row.createCell(17).setCellValue(report.getSubtotal().doubleValue());
            row.createCell(18).setCellValue(report.getTax().doubleValue());
            row.createCell(19).setCellValue(report.getTotal().doubleValue());
            row.createCell(20).setCellValue(report.getRecommendations());
            row.createCell(21).setCellValue(report.getEmail());
            row.createCell(22).setCellValue(report.isCoTestCompleted() ? "Yes" : "No");
            row.createCell(23).setCellValue(report.getReasonForCall());
            row.createCell(24).setCellValue(report.isHeatExchangerWashed() ? "Yes" : "No");
            row.createCell(25).setCellValue(report.isHeatExchangerCleaned() ? "Yes" : "No");
            row.createCell(26).setCellValue(report.isHeatExchangerInspected() ? "Yes" : "No");
            row.createCell(27).setCellValue(report.isRefractoryTested() ? "Yes" : "No");
            row.createCell(28).setCellValue(report.isRefractorySet() ? "Yes" : "No");
            row.createCell(29).setCellValue(report.isOperatorTested() ? "Yes" : "No");
            row.createCell(30).setCellValue(report.isOperatorSet());
            row.createCell(31).setCellValue(report.isHighLimitTested() ? "Yes" : "No");
            row.createCell(32).setCellValue(report.isHighLimitSet());
            row.createCell(33).setCellValue(report.isWaterPressureTested() ? "Yes" : "No");
            row.createCell(34).setCellValue(report.isWaterPressureSet());
            row.createCell(35).setCellValue(report.isFlowSwitchTested() ? "Yes" : "No");
            row.createCell(36).setCellValue(report.isPressureSwitchTested() ? "Yes" : "No");
            row.createCell(37).setCellValue(report.getQuantity());
            row.createCell(38).setCellValue(report.getMaterial());
            row.createCell(39).setCellValue(report.getUnitPrice().doubleValue());

            // Mark as exported
            report.setExported(true);
        }

        // Save the Excel file
        try (FileOutputStream out = new FileOutputStream(path)) {
            workbook.write(out);
            System.out.println("Data exported successfully to " + path);
        } catch (Exception e) {
            System.err.println("Failed to write to Excel file: " + e.getMessage());
        }

        // Save updated entities to mark them as exported
        repository.saveAll(reports);
    }

    @Transactional
    public void exportTimesheetsToExcel(String path) throws IOException, InvalidFormatException {
        List<Timesheet> timesheets = timesheetRepository.findByExportedFalse();
        if (timesheets.isEmpty()) {
            System.out.println("No new timesheets to export.");
            return;
        }

        Workbook workbook;
        Sheet sheet;
        int rowIdx;

        // Check if the file already exists
        File file = new File(path);
        if (file.exists()) {
            // If the file exists, open it and get the last row number to append data
            FileInputStream inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheet("Timesheets");
            rowIdx = sheet.getLastRowNum() + 1; // Get the index for the next available row
            inputStream.close(); // Close the input stream after reading the file
        } else {
            // If the file does not exist, create a new workbook and sheet
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Timesheets");

            // Create header row
            String[] headers = {"Name", "Login Time", "Logout Time"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
            rowIdx = 1; // Start adding data from the first row after the header
        }

        // Add new data rows
        for (Timesheet timesheet : timesheets) {
            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(timesheet.getName());
            row.createCell(1).setCellValue(timesheet.getLoginTime().toString()); // Adjust formatting if needed
            row.createCell(2).setCellValue(timesheet.getLogoutTime().toString()); // Adjust formatting if needed

            // Mark as exported
            timesheet.setExported(true);
        }

        // Write changes to the Excel file
        try (FileOutputStream out = new FileOutputStream(path)) {
            workbook.write(out);
            System.out.println("Timesheets exported successfully to " + path);
        }

        // Save updated timesheets
        timesheetRepository.saveAll(timesheets);
    }





}