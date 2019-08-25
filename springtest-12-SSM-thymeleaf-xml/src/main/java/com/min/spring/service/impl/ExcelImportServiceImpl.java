package com.min.spring.service.impl;

import com.min.spring.service.ExcelImportDataService;
import com.min.spring.service.ExcelImportFileService;
import com.min.spring.service.ExcelImportService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImportServiceImpl implements ExcelImportService {

    @Autowired
    ExcelImportFileService importFileService;
    @Autowired
    ExcelImportDataService importDataService;

    //主要流程
    @Override
    public String importExcel(String importType, MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        List<Object> list=new ArrayList<>();
        if ("jxl".equals(importType)) {
            list = JxlreadExcel(file);
        }
        else{
            list = readExcel(file);
        }

        //TODO

//        ExcelImportFile excelImportFile = importFileService.save(importType, fileName, list.size(), request);
//
//        importDataService.save(importType,fileName,excelImportFile.getId(),list);


        return "oK";
    }

    //jxl读取excel,放回list
    private List<Object> JxlreadExcel(MultipartFile file) {

        try {
            Workbook workbook = Workbook.getWorkbook(file.getInputStream());
            //TODO
            Sheet sheet = workbook.getSheet(0);
            System.out.println("列" + sheet.getColumns());
            System.out.println("行" + sheet.getRows());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        return null;
    }

    //poi读取excel,放回list
    private List<Object> readExcel(MultipartFile file) {
        return null;
    }

}
