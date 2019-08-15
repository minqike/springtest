package com.min.spring.service;

import com.min.spring.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User findByUsername(String username);

    User save(User user);

    List<User> findByString(String keyword);

    int deleteBatch(String[] ids);
}
