package com.min.spring.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.spring.entity.User;
import com.min.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String list (Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "2") Integer size
                        ){
        PageHelper.startPage(page, size);
        List<User> users = userService.findAll();
        PageInfo<User> info = new PageInfo<>(users);
        model.addAttribute("list", info.getList());

        model.addAttribute("pageInfo", info);
        System.out.println(info);
        return "user";

    }

    @RequestMapping(value = "/user/add",method = RequestMethod.GET)
    public String add (Model model){
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("pagetype","2");
        return "user_form";

    }


    @RequestMapping(value = "/user/edit",method = RequestMethod.POST)
    public String save (User user){

        User user1 = userService.save(user);

        return "redirect:/user/list";

    }

    @RequestMapping(value = "/user/edit",method = RequestMethod.GET)
    public String edit (@RequestParam int id,
                        Model model){

        User user = userService.findById(id);
        model.addAttribute("pagetype","1");
        model.addAttribute("user",user);

        return "user_form";

    }

    @RequestMapping(value = "/user/view",method = RequestMethod.GET)
    public String view (@RequestParam int id,
                        Model model){

        User user = userService.findById(id);
        model.addAttribute("pagetype","0");
        model.addAttribute("user",user);

        return "user_form";

    }
}
