package com.eximias.ecommerce.service;


import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PdfGenerateService {
    ResponseEntity<byte[]> generateOrderDetailPdfFile(String templateName, Map<String, Object> data);
}
