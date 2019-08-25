package com.min.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelImportFile {
    private Integer id;                  //自增id
    private String importDate;         //导入日期YYYMMDD
    private String importFileName;    //excel文件名
    private Integer importCount;        //导入件数
    private String importUser;         //导入用户
    private Integer status;              //文件状态
    private String importType;         //导入用的技术
    private Date created;             //创建时间
    private Date updated;             //更新时间
}
