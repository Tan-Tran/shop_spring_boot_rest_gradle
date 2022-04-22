package com.eximias.ecommerce.service.impl;

import com.eximias.ecommerce.service.PdfGenerateService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {

    private Logger logger  = LoggerFactory.getLogger(PdfGenerateServiceImpl.class);

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void generatePdfFile(String templateName, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process(templateName, context);
        try {
            PDDocument pdDocument = new PDDocument();
            PDPage pdPage = new PDPage();
            PDFont font = PDType1Font.COURIER;
            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);
            contentStream.beginText();
            contentStream.setFont( PDType1Font.TIMES_ROMAN , 12 );
            contentStream.newLineAtOffset(100, 700);
            contentStream.drawString(htmlContent);
            contentStream.endText();
            contentStream.close();
            pdDocument.save("./src/main/resources/order_detail.pdf");
            pdDocument.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
