package com.min.spring.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ExcelImportService {

    String importExcel(String importType, MultipartFile file, HttpServletRequest request);
}
