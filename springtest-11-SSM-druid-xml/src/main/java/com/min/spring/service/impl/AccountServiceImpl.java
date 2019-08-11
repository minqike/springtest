package com.min.spring.service.impl;

import com.min.spring.dao.AccountDao;
import com.min.spring.entity.Account;
import com.min.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;

    @Override
    public int insert(Account pojo) {
        return accountDao.insert(pojo);
    }

    @Override
    public int insertList(List<Account> pojo) {
        return accountDao.insertList(pojo);
    }

    @Override
    public List<Account> select(Account pojo) {
        return accountDao.select(pojo);
    }

    @Override
    public int update(Account pojo) {
        return accountDao.update(pojo);
    }
}
