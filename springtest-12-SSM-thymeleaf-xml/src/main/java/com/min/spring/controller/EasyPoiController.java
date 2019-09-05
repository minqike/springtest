package com.min.spring.controller;

import com.min.spring.service.EasyPoiExportService;
import com.min.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class EasyPoiController {


    @Autowired
    EasyPoiExportService easyPoiExportService;
    /**
     * 不使用模板导出excel
     * @param response
     * @throws Exception
     */
    @GetMapping("/user/export")
    public void exportUser(HttpServletResponse response) throws IOException {
        easyPoiExportService.exportUser(response);
    }

    /**
     * 使用模板导出excel
     * @param response
     * @throws Exception
     */
    @GetMapping("/user/exportUseTemplate")
    public void exportUserTemplate(HttpServletResponse response) throws Exception {
        easyPoiExportService.exportUserUseTemplate(response);
    }
}
