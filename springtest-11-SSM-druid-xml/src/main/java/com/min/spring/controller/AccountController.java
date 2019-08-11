package com.min.spring.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.spring.entity.Account;
import com.min.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/account/list")
    @ResponseBody
    public List<Account> list(Account account){
        return accountService.select(account);
    }

    @RequestMapping("/account/page")
    @ResponseBody
    public PageInfo<Account> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                  @RequestParam(value = "size",defaultValue = "2") Integer size,
                                  Account account){

        PageHelper.startPage(page, size);
        return  new PageInfo<>(accountService.select(account));

    }

}
