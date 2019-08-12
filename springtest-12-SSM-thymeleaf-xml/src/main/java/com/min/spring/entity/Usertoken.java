package com.min.spring.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Usertoken {
    private Integer id;
    private Integer uid;
    private String token;
    private Date expirationTime;

}
