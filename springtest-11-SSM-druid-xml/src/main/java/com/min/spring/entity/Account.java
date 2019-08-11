package com.min.spring.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Account {

    private Integer id;
    private String name;
    private String nickname;
    private Date createTime;
    private Date updateTime;

}
