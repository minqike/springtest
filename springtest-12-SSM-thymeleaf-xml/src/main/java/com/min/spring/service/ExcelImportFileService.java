package com.min.spring.service;

import com.min.spring.entity.ExcelImportFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ExcelImportFileService {


    ExcelImportFile save(String importType, MultipartFile file, HttpServletRequest request);
}
