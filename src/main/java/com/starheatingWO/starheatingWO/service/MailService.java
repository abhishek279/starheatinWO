package com.starheatingWO.starheatingWO.service;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.ByteArrayResource;



@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReport(String to, String subject, String text, ByteArrayOutputStream attachmentStream) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            // Convert ByteArrayOutputStream to ByteArrayResource for attachment
            InputStreamSource source = new ByteArrayResource(attachmentStream.toByteArray());
            helper.addAttachment("ServiceReport.pdf", source);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
