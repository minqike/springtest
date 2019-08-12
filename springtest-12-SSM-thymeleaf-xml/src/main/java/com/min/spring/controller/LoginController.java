package com.min.spring.controller;

import com.min.spring.constant.MyConstant;
import com.min.spring.entity.User;
import com.min.spring.service.LoginService;
import com.min.spring.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        if (request.getSession().getAttribute(MyConstant.LOGIN_USER) == null) {
            String uid = CookieUtil.getUid(request, MyConstant.REMEMBERME);
            if (StringUtils.isNotBlank(uid)){
                String[] arr = uid.split("\\|");
                model.addAttribute("username",arr[0]);
                model.addAttribute("password",arr[1]);
                model.addAttribute("remember", "on");
            }

            return "login";
        } else {
            return "redirect:index";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(required = false) String rememberMe,
                        Model model) {
        boolean remember = "on".equals(rememberMe) ? true : false;
        User user = loginService.login(username, password,remember);
        if (user != null) {
            return "redirect:index";
        }

        model.addAttribute("message", "登录失败");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        loginService.logout();
        return "redirect:login";
    }

}
