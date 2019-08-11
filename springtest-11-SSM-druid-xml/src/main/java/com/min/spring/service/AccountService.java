package com.min.spring.service;

import com.min.spring.entity.Account;

import java.util.List;

public interface AccountService {

    int insert(Account pojo);

    int insertList(List<Account> pojo);

    List<Account> select(Account pojo);

    int update(Account pojo);
}
