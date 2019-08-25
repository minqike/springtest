package com.min.spring.dao;

import com.min.spring.entity.ExcelImportData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcelImportDataDao {

    int insert(@Param("pojo") ExcelImportData pojo);

    int batchInsert(@Param("list") List<ExcelImportData> list);

    int update(@Param("pojo") ExcelImportData pojo);

    List<ExcelImportData> query(@Param("pojo") ExcelImportData pojo);

    long count(@Param("pojo") ExcelImportData pojo);

}