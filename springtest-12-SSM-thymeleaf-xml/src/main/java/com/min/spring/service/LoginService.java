package com.min.spring.service;

import com.min.spring.entity.User;

public interface LoginService {

    User login(String username,String password,boolean remember);
    void logout();

}
