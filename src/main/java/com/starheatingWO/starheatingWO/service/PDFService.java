package com.starheatingWO.starheatingWO.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.starheatingWO.starheatingWO.model.ServiceReport;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

@Service
public class PDFService {

    public ByteArrayOutputStream generatePDF(ServiceReport report) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

//        // Logo
//        URL imageUrl = getClass().getResource("/static/logo.png");
//        Image logo = new Image(ImageDataFactory.create(imageUrl));
//        logo.setWidth(100);
//        logo.setHeight(50);
//        document.add(logo);

        // Header
        Paragraph header = new Paragraph("STAR HEATING SERVICES LTD. - Service Report")
                .setFont(boldFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER).setFontColor(ColorConstants.BLUE);
        document.add(header);

        // Main table setup
        Table mainTable = new Table(UnitValue.createPercentArray(new float[]{1, 3})).useAllAvailableWidth();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Date format

        // Adding all fields
        addField(mainTable, "Job Site Name", report.getJobSiteName(), font);
        addField(mainTable, "Billing Name", report.getBillingName(), font);
        addField(mainTable, "Job Site Address", report.getJobSiteAddress(), font);
        addField(mainTable, "Billing Address", report.getBillingAddress(), font);
        addField(mainTable, "Date", sdf.format(report.getDate()), font);
        addField(mainTable, "Customer's P.O. #", report.getCustomerPO(), font);
        addField(mainTable, "Equipment Make", report.getEquipmentMake(), font);
        addField(mainTable, "Model", report.getModel(), font);
        addField(mainTable, "Serial #", report.getSerialNumber(), font);
        addField(mainTable, "Co Readings", String.valueOf(report.getCoReadings()), font);
        addField(mainTable, "Reason for Call", report.getReasonForCall(), font);
        addField(mainTable, "Quantity", String.valueOf(report.getQuantity()), font);
        addField(mainTable, "Material", report.getMaterial(), font);
        addField(mainTable, "Unit Price", String.format("$%.2f", report.getUnitPrice()), font);
        addDetailedServiceChecks(mainTable, report, font);
        addFinancialDetails(mainTable, report, font);
        addRecommendationsAndSignature(mainTable, report, font);

        document.add(mainTable);
        document.close();
        return byteArrayOutputStream;
    }

    private void addField(Table table, String fieldName, String value, PdfFont font) {
        Cell keyCell = new Cell().add(new Paragraph(fieldName + ":").setFont(font));
        Cell valueCell = new Cell().add(new Paragraph(value).setFont(font));
        table.addCell(keyCell);
        table.addCell(valueCell);
    }

    private void addDetailedServiceChecks(Table mainTable, ServiceReport report, PdfFont font) {
        // Add checks for various systems with an example structure
        addChecks(mainTable, "Heat Exchanger", new String[]{"Washed", "Cleaned", "Inspected"},
                new String[]{String.valueOf(report.isHeatExchangerWashed()), String.valueOf(report.isHeatExchangerCleaned()), String.valueOf(report.isHeatExchangerInspected())}, font);
        addChecks(mainTable, "Refractory", new String[]{"Checked", "Inspected"},
                new String[]{String.valueOf(report.isRefractoryTested()), String.valueOf(report.isRefractorySet())}, font);
        addChecks(mainTable, "Operator", new String[]{"Tested", "Set@"},
                new String[]{Boolean.toString(report.isOperatorTested()), report.isOperatorSet()}, font);
        addChecks(mainTable, "High Limit", new String[]{"Tested", "Set@"},
                new String[]{String.valueOf(report.isHighLimitTested()), report.isHighLimitSet()}, font);
        addChecks(mainTable, "Water Pressure", new String[]{"Tested", "Set@"},
                new String[]{String.valueOf(report.isWaterPressureTested()), report.isWaterPressureSet()}, font);
        // Combine Flow Switch and Pressure Switch into a single row
        addCombinedSwitches(mainTable, report, font);
    }
    private void addCombinedSwitches(Table mainTable, ServiceReport report, PdfFont font) {
        // Create a sub-table with 2 columns; one for labels and one for values
        Table switchTable = new Table(2);

        // Adding Flow Switch row
        switchTable.addCell(new Cell().add(new Paragraph("Flow Switch:").setFont(font).setBold()));
        switchTable.addCell(new Cell().add(new Paragraph(report.isFlowSwitchTested() ? "True" : "False").setFont(font)));

        // Adding Pressure Switch row
        switchTable.addCell(new Cell().add(new Paragraph("Pressure Switch:").setFont(font).setBold()));
        switchTable.addCell(new Cell().add(new Paragraph(report.isPressureSwitchTested() ? "True" : "False").setFont(font)));

        // Add the sub-table to the main table ensuring it spans across all available columns in the main table
        mainTable.addCell(new Cell(1, 2).add(switchTable));  // This ensures it spans two columns in the main table
    }


    private void addChecks(Table mainTable, String header, String[] labels, String[] statuses, PdfFont font) {
        // Creating a sub-table with consistent alignment and layout
        Table checkTable = new Table(new float[]{1, 3}); // Adjust column widths for better space utilization
        checkTable.useAllAvailableWidth();

        // Adding header with background color and bold font
        checkTable.addCell(new Cell(1, 2)  // Header cell spanning two columns
                .add(new Paragraph(header))
                .setFont(font)
                .setBold()
                .setBackgroundColor(ColorConstants.LIGHT_GRAY) // Header background
                .setFontSize(9)  // Smaller font for headers to save space
                .setPadding(4)); // Reduced padding to decrease space usage

        // Looping through each status check
        for (int i = 0; i < labels.length; i++) {
            checkTable.addCell(new Cell()
                    .add(new Paragraph(labels[i] + ":").setFont(font).setFontSize(9))
                    .setPaddingLeft(10) // Adding padding to align with the header
                    .setPaddingBottom(2) // Minimal padding for compact appearance
                    .setBorder(Border.NO_BORDER));
            checkTable.addCell(new Cell()
                    .add(new Paragraph(statuses[i]).setFont(font).setFontSize(9))
                    .setPaddingBottom(2) // Minimal padding for compact appearance
                    .setBorder(Border.NO_BORDER));
        }

        // Add the check table to the main table with minimized padding to ensure it fits well
        mainTable.addCell(new Cell().add(checkTable).setPaddingTop(4).setPaddingBottom(4));
    }



    private void addSingleCheck(Table mainTable, String header, boolean status, PdfFont font) {
        Table singleCheckTable = new Table(2);
        singleCheckTable.addCell(new Paragraph(header + ":").setFont(font).setBold());
        singleCheckTable.addCell(new Paragraph(status ? "✔" : "✘").setFont(font));
        mainTable.addCell(new Cell(1, 2).add(singleCheckTable));
    }

    private void addFinancialDetails(Table table, ServiceReport report, PdfFont font) {
        addField(table, "Total Material", String.format("$%.2f", report.getTotalMaterial()), font);
        addField(table, "Total Labour", String.format("$%.2f", report.getTotalLabour()), font);
        addField(table, "Travel Charges", String.format("$%.2f", report.getTravelCharges()), font);
        addField(table, "Sub-Total", String.format("$%.2f", report.getSubtotal()), font);
        addField(table, "H.S.T.", String.format("$%.2f", report.getTax()), font);
        addField(table, "TOTAL", String.format("$%.2f", report.getTotal()), font);
    }

    private void addRecommendationsAndSignature(Table table, ServiceReport report, PdfFont font) {
        table.addCell(new Cell(1, 2).add(new Paragraph("Recommendation: " + report.getRecommendations()).setFont(font)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell(1, 2).add(new Paragraph("Authorized Signature: ______________________").setFont(font)).setBorder(Border.NO_BORDER));
    }
}
