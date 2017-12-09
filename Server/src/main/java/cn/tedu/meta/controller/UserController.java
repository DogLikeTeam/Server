package cn.tedu.meta.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.tedu.meta.pojo.User;
import cn.tedu.meta.service.UserService;

import cn.tedu.meta.utils.EmailAction;
import cn.tedu.meta.utils.Md5HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findUserList")
    //@ResponseBody    //需要页面跳转时，不需要添加该注解
    public String findUserList(Model model){

        List<User> userList = userService.findUserList();
        model.addAttribute("userList",userList);

        //跳转回用户列表页面
        return "userList";
    }

}
