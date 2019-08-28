package com.min.spring.service.impl;

import com.min.spring.dto.ExcelImportDto;
import com.min.spring.entity.ExcelImportFile;
import com.min.spring.service.ExcelImportDataService;
import com.min.spring.service.ExcelImportFileService;
import com.min.spring.service.ExcelImportService;
import jxl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("excelImportService")
public class ExcelImportServiceImpl implements ExcelImportService {

    @Autowired
    ExcelImportFileService importFileService;
    @Autowired
    ExcelImportDataService importDataService;

    //主要流程
    @Override
    @Transactional
    public String importExcel(String importType, MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        List<ExcelImportDto> list = new ArrayList<>();
        //获取excel数据
        try {
            if ("jxl".equals(importType)) {
                list = JxlreadExcel(file);
            } else {
                list = readExcel(file);
            }
        } catch (Exception e) {
            return "excel读取失败";
        }
        //写入到文件表
        ExcelImportFile excelImportFile = null;
        try {
            excelImportFile = importFileService.save(importType, fileName, list.size(), request);
        } catch (Exception e) {
            return "文件表保存失败";
        }
//        excelImportFile = importFileService.save(importType, fileName, list.size(), request);
        System.out.println("111111");
        //把excel数据写入到数据表
        int count = 0;
        try {
            count = importDataService.save(importType, fileName, excelImportFile.getId(), list);
        } catch (Exception e) {
            return "excel数据表保存失败";
        }
//        count = importDataService.save(importType, fileName, excelImportFile.getId(), list);
        System.out.println("22222");
        return "导入成功,数据条数:" + count + "件";
    }

    //jxl读取excel,放回list
    private List<ExcelImportDto> JxlreadExcel(MultipartFile file) throws Exception {
        List<ExcelImportDto> list = new ArrayList<>();
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);

        for (int i = 1; i < sheet.getRows(); i++) {
            ExcelImportDto dto = new ExcelImportDto();
            dto.setHost(sheet.getCell(0, i).getContents());
            dto.setMessage(sheet.getCell(1, i).getContents());
            dto.setStartTime(getDateJxl(sheet.getCell(2, i)));
            dto.setEndTime(getDateJxl(sheet.getCell(3, i)));
            System.out.println(dto);
            list.add(dto);
        }
        return list;
    }

    //poi读取excel,放回list
    private List<ExcelImportDto> readExcel(MultipartFile file) {
        return null;
    }

    /**
     * JXL解析excel的时候会默认当前输入的时间为格林威治时间，
     * 需要转为当前时区的时间（之前8小时）
     * @param cell
     * @return
     */
    private Date getDateJxl(Cell cell) {
        Date transferDate = null;
        if (cell.getType() == CellType.DATE) {
            DateCell dc = (DateCell) cell;
            Date date = dc.getDate();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            //解析excel的时候会默认当前输入的时间为格林威治时间，需要转为当前时区的时间（之前8小时）
            c.add(Calendar.HOUR, -1 * TimeZone.getDefault().getRawOffset() / (60 * 60 * 1000));
            transferDate = c.getTime();
            return transferDate;
        } else {
            return null;
        }
    }


}
