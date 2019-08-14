package com.min.spring.service.impl;

import com.min.spring.constant.MyConstant;
import com.min.spring.entity.LoginUser;
import com.min.spring.entity.User;
import com.min.spring.service.LoginService;
import com.min.spring.service.UserService;
import com.min.spring.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;


    @Autowired
    UserService userService;


    @Override
    public User login(String username,String password,boolean remember) {

        User user = userService.findByUsername(username);
        if (user != null) {
            if(user.getPassword().equals(password)){
                if (remember==true){
                    CookieUtil.addCookie(response,MyConstant.REMEMBERME, user.getUsername()+"|"+user.getPassword(),-1);
                }else{
                    CookieUtil.removeCookie(response,MyConstant.REMEMBERME);
                }
                LoginUser loginUser = new LoginUser();
                loginUser.setId(user.getId());
                loginUser.setUsername(user.getUsername());
                loginUser.setAvatar(user.getAvatar());
                request.getSession().setAttribute(MyConstant.LOGIN_USER,loginUser);
                return user;
            }
        }

        return null;

    }

    @Override
    public void logout() {
        request.getSession().invalidate();
    }
}
