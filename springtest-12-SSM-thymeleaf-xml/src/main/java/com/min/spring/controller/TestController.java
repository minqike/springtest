package com.min.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("test/modal")
    public String modal(){
        return "/test/modal";
    }
}
