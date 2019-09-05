package com.min.spring.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Excel(name = "id", width = 10)
    private Integer id;
    @Excel(name = "用户名", width = 20)
    private String username;
    private String password;
    @Excel(name = "头像", width = 30)
    private String avatar;
    @Excel(name = "创建时间", width = 20,format ="yyyy-MM-dd HH:mm:ss")
    private Date created;
    @Excel(name = "更新时间", width = 20,format ="yyyy-MM-dd HH:mm:ss")
    private Date updated;

}
