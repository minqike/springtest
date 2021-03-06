package com.min.spring.controller;

import com.min.spring.dao.UserDao;
import com.min.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/user/list")
    @ResponseBody
    public List<User> findall() {
        return userDao.findAll();
    }


    @RequestMapping("/user/{id}")
    @ResponseBody
    public User findById(@PathVariable Integer id) {
        return userDao.findById(id);
    }

}
