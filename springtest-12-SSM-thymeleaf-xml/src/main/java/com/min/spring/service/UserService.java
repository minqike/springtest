package com.min.spring.service;

import com.min.spring.dto.TokenUser;
import com.min.spring.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User findByUsername(String username);


    TokenUser findByToken(String token);

    User save(User user);
}
