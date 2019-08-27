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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExcelImportController {

    @Autowired
    private  ExcelImportFileService excelImportFileService;

    @Autowired
    private ExcelImportService excelImportService;

    @RequestMapping("/test/import_jxl")
    @ResponseBody
    public Map<String,Object> import_jxl(MultipartFile[] files, HttpServletRequest request){
        Map<String,Object> map =new HashMap<>();
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String msg=excelImportService.importExcel("jxl",file,request);
            list.add(file.getOriginalFilename() + ":" + msg);
        }
        map.put("message",list);
        map.put("count", files.length+"");
        map.put("test", "我是中文,看看能不能正常显示");
        return map;
    }

}
