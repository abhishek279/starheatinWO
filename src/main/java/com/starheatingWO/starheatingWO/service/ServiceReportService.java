package com.starheatingWO.starheatingWO.service;
import com.starheatingWO.starheatingWO.model.ServiceReport;
import com.starheatingWO.starheatingWO.repository.ServiceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.ByteArrayOutputStream;

@Service
public class ServiceReportService {

    @Autowired
    private ServiceReportRepository repository;

    @Autowired
    private MailService mailService;

    @Autowired
    private PDFService pdfService;

    @Transactional
    public ServiceReport createAndSendReport(ServiceReport report) throws Exception {
        // Save the report to the database
        ServiceReport savedReport = repository.save(report);

        // Generate PDF
        ByteArrayOutputStream pdfStream = pdfService.generatePDF(savedReport);

        // Send Email with PDF
        if (pdfStream != null) {
            mailService.sendReport(savedReport.getEmail(), "Service Report", "Please find attached the Service Report.", pdfStream);
        }

        return savedReport;
    }
}
