package com.min.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String hello(){
        return "index";
    }
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message", "你好,MVC");
        return "hello";
    }

    @RequestMapping("/string")
    @ResponseBody
    public String string(){
        return "string字符串";
    }

    @RequestMapping("/map")
    @ResponseBody
    public Map<String,String> getmap(){
        Map<String,String> map=new HashMap<>();
        map.put("A","姓名");
        map.put("B", "年龄");
        return map;
    }
}
