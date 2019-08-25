package com.min.spring.controller.test;

import com.min.spring.entity.ExcelImportFile;
import com.min.spring.service.ExcelImportFileService;
import com.min.spring.service.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ExcelImportController {

    @Autowired
    private  ExcelImportFileService excelImportFileService;

    @Autowired
    private ExcelImportService excelImportService;

    @RequestMapping("/test/import_jxl")
    @ResponseBody
    public Map<String,String> import_jxl(MultipartFile[] files, HttpServletRequest request){
        Map<String,String> map =new HashMap<>();

        for (MultipartFile file : files) {
            excelImportService.importExcel("jxl",file,request);

        }
        map.put("count", files.length+"");
        return map;
    }

}
