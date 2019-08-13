package com.min.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping({"/","index"})
    public String index(HttpServletRequest request){

        return "index";
    }

    @RequestMapping("home")
    public String home(HttpServletRequest request){

        return "home";
    }
}
