package com.min.spring.service.impl;

import com.min.spring.dao.ExcelImportDataDao;
import com.min.spring.dto.ExcelImportDto;
import com.min.spring.entity.ExcelImportData;
import com.min.spring.service.ExcelImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ExcelImportDataServiceImpl implements ExcelImportDataService {
    @Autowired
    ExcelImportDataDao excelImportDataDao;

    @Override
    public int save(String importType, String fileName, Integer id, List<ExcelImportDto> list) {
        List<ExcelImportData> importDataList = new ArrayList<>();

        for (int i =  0; i < list.size() ; i++) {
            ExcelImportData excelImportData = new ExcelImportData();
            ExcelImportDto item = list.get(i);
            excelImportData.setHostId(item.getHost());
            excelImportData.setMessage(item.getMessage());
            excelImportData.setStartTime(item.getStartTime());
            excelImportData.setEndTime(item.getEndTime());
            excelImportData.setFileId(id);
            excelImportData.setFileName(fileName);
            excelImportData.setImportType(importType);
            excelImportData.setStatus(0);
            excelImportData.setCreated(new Date());
            excelImportData.setUpdated(excelImportData.getCreated());
            importDataList.add(excelImportData);
        }

        int count  = excelImportDataDao.batchInsert(importDataList);
        return count;

    }
}
