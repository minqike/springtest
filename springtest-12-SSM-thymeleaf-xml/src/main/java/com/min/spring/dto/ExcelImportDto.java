package com.min.spring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExcelImportDto {
    private String host;
    private String message;
    private Date startTime;
    private Date endTime;

}
