package org.rail.project.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.rail.project.service.PdfGeneratorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PdfController {

    private final PdfGeneratorService pdfGeneratorService;

    @GetMapping("/pdf/generate")
    public void generatePdf(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        String headerValue = "attachment; filename=pdf_" + LocalDateTime.now() + ".pdf";
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, headerValue);
        pdfGeneratorService.export(response);
    }
}
