//package com.starheatingWO.starheatingWO.batchJob;
//
//import com.starheatingWO.starheatingWO.model.ServiceReport;
//import com.starheatingWO.starheatingWO.repository.ServiceReportRepository;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.List;
//
//@Component
//public class BatchJob {
//
//    @Autowired
//    private ServiceReportRepository serviceReportRepository;
//
//    @Scheduled(fixedDelay = 3000) // 5 minutes in milliseconds
//    public void runBatchJob() throws Exception {
//        // Fetch data from DB
//        List<ServiceReport> forms = serviceReportRepository.findAll();
//
//        // Read master Excel file
//        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\shark\\OneDrive\\Documents\\Abhi_Doc\\masterexcel\\masterExcel.xlsx");
//        Workbook workbook = new XSSFWorkbook(fileInputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//
//        // Iterate over forms and add to master Excel file if not already exists
//        for (ServiceReport form : forms) {
//            boolean exists = false;
//            for (Row row : sheet) {
//                Cell cell = row.getCell(0);
//                if (cell.getStringCellValue().equals(form.getJobName())) {
//                    exists = true;
//                    break;
//                }
//            }
//            if (!exists) {
//                Row row = sheet.createRow(sheet.getLastRowNum() + 1);
//                row.createCell(0).setCellValue(form.getJobName());
//                // Add other columns as needed
//            }
//        }
//
//        // Write to master Excel file
//        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\shark\\OneDrive\\Documents\\Abhi_Doc\\masterexcel\\masterExcel.xlsx");
//        workbook.write(fileOutputStream);
//        fileOutputStream.close();
//    }
//}