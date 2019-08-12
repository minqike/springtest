package com.min.spring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TokenUser {
    private Integer id;
    private String name;
    private String avatar;
    private Date expirationTime;
}
