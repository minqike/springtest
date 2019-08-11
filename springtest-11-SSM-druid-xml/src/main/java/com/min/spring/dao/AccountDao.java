package com.min.spring.dao;

import com.min.spring.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountDao {

    int insert(@Param("pojo") Account pojo);

    int insertList(@Param("pojos") List<Account> pojo);

    List<Account> select(@Param("pojo") Account pojo);

    int update(@Param("pojo") Account pojo);

}
