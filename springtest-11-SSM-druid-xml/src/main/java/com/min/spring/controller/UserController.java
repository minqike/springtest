package com.min.spring.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.spring.dao.UserDao;
import com.min.spring.entity.User;
import com.min.spring.service.UserService;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    @ResponseBody
    public List<User> findall() {
        return userService.findAll();
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }


    @RequestMapping("/user/page")
    @ResponseBody
    public PageInfo<User> page( @RequestParam(value = "pagenum",defaultValue = "1")Integer pageNum,
                            @RequestParam(value = "size",defaultValue = "2") Integer size) {
        PageHelper.startPage(pageNum, size);
        return new PageInfo(userService.findAll());

    }


}
