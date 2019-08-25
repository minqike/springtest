package com.min.spring.dao;

import com.min.spring.entity.ExcelImportFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelImportFileDao {

    ExcelImportFile save(@Param("pojo") ExcelImportFile pojo);
}
