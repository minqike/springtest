package com.min.spring.service.impl;

import com.min.spring.dao.UserDao;
import com.min.spring.dto.TokenUser;
import com.min.spring.entity.User;
import com.min.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public TokenUser findByToken(String token) {
        return userDao.findBytoken(token);
    }
}
