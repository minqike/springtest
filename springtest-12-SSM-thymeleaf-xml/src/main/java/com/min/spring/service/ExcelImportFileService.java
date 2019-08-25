package com.min.spring.service;

import com.min.spring.entity.ExcelImportFile;

import javax.servlet.http.HttpServletRequest;

public interface ExcelImportFileService {


    ExcelImportFile save(String importType, String fileName, Integer count, HttpServletRequest request);
}
