package com.min.spring.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.min.spring.entity.User;
import lombok.Data;

import java.text.SimpleDateFormat;

public class ExportUserDto extends User {

    @Excel(name = "创建时间", width = 20,format ="yyyy-MM-dd HH:mm:ss")
    private String createdStr;
    @Excel(name = "更新时间", width = 20,format ="yyyy-MM-dd HH:mm:ss")
    private String updatedStr;

    public String getCreatedStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(getCreated());
    }

    public String getUpdatedStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(getUpdated());
    }
}