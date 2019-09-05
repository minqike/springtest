package com.min.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("test/modal")
    public String modal() {
        return "/test/modal";
    }

    @RequestMapping("test/upload")
    public String fileupload() {
        return "/test/upload";
    }

    @RequestMapping("test/download")
    public String filedownload() {
        return "/test/download";
    }

    @RequestMapping("test/importExcel")
    public String importExcel() {
        return "/test/excel_import";
    }

    @RequestMapping("test/easypoiExportExcel")
    public String easypoiExportExcel() {
        return "/test/excel_export";
    }

}
