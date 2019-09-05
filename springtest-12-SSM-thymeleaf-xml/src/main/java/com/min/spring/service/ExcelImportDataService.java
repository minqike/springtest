package com.min.spring.service;

import com.min.spring.dto.ExcelImportDto;
import com.min.spring.entity.ExcelImportData;

import java.util.List;

public interface ExcelImportDataService {


    int save(String importType, String fileName , Integer id, List<ExcelImportDto> list);


}
