package com.min.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelImportData {
    private Integer id;
    private Integer fileId;      //导入文件的id
    private String fileName;     //导入的文件名字
    private String hostId;       //host名
    private String message;       //消息内容
    private Date startTime;      //开始时间
    private Date endTime;        //结束时间
    private Integer status;       //记录状态
    private String importType;   //导入用的技术
    private Date created;         //创建时间
    private Date updated;         //更新时间
}
