package com.min.spring.dao;

import com.min.spring.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

   @Select("select * from User")
   List<User> findAll();

   User findById(Integer id);

   User findByUsername(String username);

    int save(@Param("user")User user);

   int update(@Param("user")User user);

    List<User> findByString(@Param("keyword")String keyword);

    int deleteBatch(@Param("ids") String[] ids);
}
