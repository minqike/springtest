package com.min.spring.controller;

import com.min.spring.constant.MyConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping({"/","index"})
    public String home(HttpServletRequest request){

        return "index";
    }
}
