package com.eximias.ecommerce.service.impl;

import com.eximias.ecommerce.service.PdfGenerateService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.w3c.dom.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Map;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {

    private final String ORDER_DETAIL_HTML_SOURCE_PATH = "src/main/resources/templates/orderDetailHtmlToPdf.html";
    private final String ORDER_DETAIL_PDF_PATH = "src/main/resources/templates/orderDetail.pdf";

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public ResponseEntity<byte[]> generateOrderDetailPdfFile(String templateName, Map<String, Object> data){
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);
        transferThymeleafToHtml(htmlContent, ORDER_DETAIL_HTML_SOURCE_PATH);
        try{
            htmlToPdf(ORDER_DETAIL_HTML_SOURCE_PATH, ORDER_DETAIL_PDF_PATH);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            File file = new File(ORDER_DETAIL_PDF_PATH);
            byte[] content =  Files.readAllBytes(file.toPath());
            return new ResponseEntity<>(content, headers, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private static void transferThymeleafToHtml(String data, String inputHTML){
        try {
            File file = new File(inputHTML);
            PrintWriter writer = new PrintWriter(file);
            writer.write(data);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static Document html5ParseDocument(String inputHTML) throws IOException{
        org.jsoup.nodes.Document doc;
        doc = Jsoup.parse(new File(inputHTML), "UTF-8");
        return new W3CDom().fromJsoup(doc);
    }
    private static void htmlToPdf(String inputHTML, String outputPdf) throws IOException {
        Document doc = html5ParseDocument(inputHTML);
        String baseUri = FileSystems.getDefault()
                .getPath(inputHTML)
                .toUri()
                .toString();
        try{
            OutputStream os = new FileOutputStream(outputPdf);
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(outputPdf);
            builder.toStream(os);
            builder.withW3cDocument(doc, baseUri);
            builder.run();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
