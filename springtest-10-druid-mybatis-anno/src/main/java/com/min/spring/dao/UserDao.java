package com.min.spring.dao;

import com.min.spring.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {
    @Select("select * from User")
    List<User> findAll();

    @Select("select * from User where id=#{id}")
    User findById(Integer id);
}
