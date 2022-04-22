package com.eximias.ecommerce.service;


import java.io.IOException;
import java.util.Map;

public interface PdfGenerateService {
    void generatePdfFile(String templateName, Map<String, Object> data);
}
