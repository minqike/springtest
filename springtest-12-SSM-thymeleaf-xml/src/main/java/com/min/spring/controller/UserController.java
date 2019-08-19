package com.min.spring.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.spring.constant.MyConstant;
import com.min.spring.dto.R;
import com.min.spring.entity.User;
import com.min.spring.exception.MyErrorCode;
import com.min.spring.exception.MyException;
import com.min.spring.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String list (Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = MyConstant.PAGE_SIZE) Integer size
                        ){
        PageHelper.startPage(page, size);
        List<User> users = userService.findAll();
        PageInfo<User> info = new PageInfo<>(users);
        model.addAttribute("list", info.getList());

        model.addAttribute("pageInfo", info);
        System.out.println(info);
        return "user";

    }

    @RequestMapping(value = "/user/search",method = RequestMethod.GET)
    public String search (@RequestParam(value = "keyword",defaultValue = "") String keyword,
                        Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = MyConstant.PAGE_SIZE) Integer size
    ){
        PageHelper.startPage(page, size);
        List<User> users = userService.findByString(keyword);
        PageInfo<User> info = new PageInfo<>(users);
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", info.getList());
        model.addAttribute("pageInfo", info);
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

    @ResponseBody
    @RequestMapping(value = "/user/deleteBatch",method = RequestMethod.POST)
    public R deleteBatch (@RequestBody String ids){

        if (StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            userService.deleteBatch(idArray);
            return R.ok();
        }

        return R.error(new MyException(MyErrorCode.DELETE_FAIL));

    }

    @ResponseBody
    @RequestMapping(value = "/user/delete",method = RequestMethod.POST)
    public R deleteById (@RequestBody String id){

        if (StringUtils.isNotBlank(id)){
            String[] idArray = new String[1];
            idArray[0] = id;
            userService.deleteBatch(idArray);
            return R.ok();
        }

        return R.error(new MyException(MyErrorCode.DELETE_FAIL));

    }

}
