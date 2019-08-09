package com.min.spring.service;

import com.github.pagehelper.PageInfo;
import com.min.spring.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

}
