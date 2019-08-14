package com.min.spring.service.impl;

import com.min.spring.dao.UserDao;
import com.min.spring.entity.User;
import com.min.spring.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public User save(User user) {

        if (user.getId()==null){
            User newUser = new User();
            BeanUtils.copyProperties(user,newUser);
            newUser.setCreated(new Date());
            newUser.setUpdated(newUser.getCreated());
            userDao.save(newUser);
            return  newUser;
        }

        else {
            User user1 = userDao.findById(user.getId());
            if (user1 == null) {
                return null;
            }

            user1.setUsername(user.getUsername());
            user1.setAvatar(user.getAvatar());
            user1.setPassword(user.getPassword());
            user1.setUpdated(new Date());
            userDao.update(user1);
            return user;

        }
    }
}
