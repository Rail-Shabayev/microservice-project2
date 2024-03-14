package org.rail.project.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PdfGeneratorService {
    private final EmailService emailService;

    public PdfGeneratorService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void export(HttpServletResponse servletResponse) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, servletResponse.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES);
        fontTitle.setSize(14);
        Paragraph paragraph = new Paragraph("This is a title", fontTitle);
        List list = new List();
        emailService.getAllEvents().forEach(list::add);
        document.add(paragraph);
        document.add(list);
        document.close();
    }
}
