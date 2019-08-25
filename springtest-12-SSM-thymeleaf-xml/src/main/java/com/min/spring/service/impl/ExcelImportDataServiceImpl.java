package com.min.spring.service.impl;

import com.min.spring.dao.ExcelImportDataDao;
import com.min.spring.entity.ExcelImportData;
import com.min.spring.service.ExcelImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExcelImportDataServiceImpl implements ExcelImportDataService {
    @Autowired
    ExcelImportDataDao excelImportDataDao;

    @Override
    public ExcelImportData save(String importType, String fileName, Integer id, List<Object> list) {
        ExcelImportData excelImportData = new ExcelImportData();

        //TODO
        return excelImportData;

    }
}
