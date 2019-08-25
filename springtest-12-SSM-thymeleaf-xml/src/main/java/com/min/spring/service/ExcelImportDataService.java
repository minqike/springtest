package com.min.spring.service;

import com.min.spring.entity.ExcelImportData;

import java.util.List;

public interface ExcelImportDataService {


    ExcelImportData save(String importType, String fileName , Integer id, List<Object> list);


}
