package com.min.spring.interceptor;

import com.min.spring.constant.MyConstant;
import com.min.spring.entity.LoginUser;
import com.min.spring.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("SessionInterceptor执行");
        //获取session中的用户
        Object loginuser = (LoginUser)request.getSession().getAttribute(MyConstant.LOGIN_USER);
        if (loginuser==null || ((LoginUser) loginuser).getId()==null){
            response.sendRedirect("login");
            return false;
        }
        return true;
    }
}
