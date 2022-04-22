package com.eximias.ecommerce.service.impl;

import com.eximias.ecommerce.service.PdfGenerateService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.Map;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {
    private Logger logger  = LoggerFactory.getLogger(PdfGenerateServiceImpl.class);
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    public void generatePdfFile(String templateName, Map<String, Object> data){
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);
        transferThymeleafToHtml(htmlContent);
        try{
            htmlToPdf("src/main/resources/templates/htmlToPdf.html", "src/main/resources/templates/test.pdf");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void transferThymeleafToHtml(String data){
        try {
            File file = new File("src/main/resources/templates/htmlToPdf.html");
            PrintWriter writer = new PrintWriter(file);
            writer.write(data);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static Document html5ParseDocument(String inputHTML) throws IOException{
        org.jsoup.nodes.Document doc;
        System.out.println("parsing ...");
        doc = Jsoup.parse(new File(inputHTML), "UTF-8");
        System.out.println("parsing done ...");
        return new W3CDom().fromJsoup(doc);
    }
    private static void htmlToPdf(String inputHTML, String outputPdf) throws IOException {
        Document doc = html5ParseDocument(inputHTML);
        String baseUri = FileSystems.getDefault()
                .getPath("src/main/resources/templates/htmlToPdf.html")
                .toUri()
                .toString();
        try{
            OutputStream os = new FileOutputStream(outputPdf);
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(outputPdf);
            builder.toStream(os);
//            using absolute path here
//            builder.useFont(new File("F:\\knpcode\\Java\\Java Programs\\PDF using Java\\PDFBox\\Gabriola.ttf"),"Gabriola");
            builder.withW3cDocument(doc, baseUri);
            builder.run();
            System.out.println("PDF generation completed");
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
