package com.min.spring.dao;

import com.min.spring.entity.ExcelImportFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelImportFileDao {

    int insert(@Param("pojo") ExcelImportFile pojo);

    int batchInsert(@Param("list") List<ExcelImportFile> list);

    int update(@Param("pojo") ExcelImportFile pojo);

    List<ExcelImportFile> query(@Param("pojo") ExcelImportFile pojo);

    long count(@Param("pojo") ExcelImportFile pojo);

}