package com.ithem.lxcq.system.controller;

import com.ithem.lxcq.system.result.CodeMsg;
import com.ithem.lxcq.system.result.Result;
import com.ithem.lxcq.system.service.UserInfoService;
import com.ithem.lxcq.system.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import com.ithem.lxcq.system.domain.user;
import com.ithem.lxcq.system.domain.userinformation;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@Controller
@RequestMapping("/check")
public class UserController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserService userService;
//用户信息
    @ResponseBody
    @RequestMapping("/information")
    public Result<userinformation> checkinfo(user user,Map map){
        String username=user.getUsername();
        userinformation userInfo=userInfoService.getInfo(username);
        System.out.println(userInfo);
        return Result.success(userInfo);



    }
    //修改密码
    @ResponseBody
    @RequestMapping("/up_password")
    public Result<user> UpdatePassword(HttpServletRequest request,user user){
        String password=request.getParameter("password");
        //user user=(user)request.getSession().getAttribute("user");
        user.setPassword(password);
        request.getSession().setAttribute("user",user);
        userService.UpdatePassword(user);
        return Result.error(CodeMsg.success);

    }
}
