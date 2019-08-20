package com.ithem.lxcq.system.controller;

import com.ithem.lxcq.system.result.CodeMsg;
import com.ithem.lxcq.system.result.Result;
import com.ithem.lxcq.system.service.CourseService;
import com.ithem.lxcq.system.service.SelectUserService;
import com.ithem.lxcq.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ithem.lxcq.system.domain.LoginVo;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;
    @Autowired
    SelectUserService selectUserService;
    private static Logger log= LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/to_login")
    public String toLogin(){
        return "index";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    /*
    分布式session采用的是客户端使用cookie保存用户信息+本地使用redis保存用户信息
    cookie是保存在response里面，因此需要respons参数
     */
    public Result<LoginVo> doLogin(HttpServletResponse response,LoginVo loginVo){
        log.info(loginVo.toString());
        CodeMsg cm=selectUserService.loginVo(response,loginVo);
        if(cm.getCode()==0){
            return Result.success(loginVo);
        }else {
            return Result.error(cm);
        }

    }

}
