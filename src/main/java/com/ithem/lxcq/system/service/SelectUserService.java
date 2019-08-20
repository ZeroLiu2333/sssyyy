package com.ithem.lxcq.system.service;

import com.ithem.lxcq.system.domain.LoginVo;
import com.ithem.lxcq.system.redis.RedisService;
import com.ithem.lxcq.system.redis.SelectUserKey;
import com.ithem.lxcq.system.result.CodeMsg;
import com.ithem.lxcq.system.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ithem.lxcq.system.domain.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class SelectUserService {
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    public static final String COOKIE1_NAME_TOKEN="token";
    public user getByToken(String token){
        user user=redisService.get(SelectUserKey.token,token,user.class);
        return user;
    }
    public  CodeMsg loginVo(HttpServletResponse response, LoginVo loginVo){
        if(loginVo==null){
            return CodeMsg.userError;
        }
        user user1 = userService.getByName(loginVo.getUsername(),loginVo.getPassword());
        if(user1==null){
            return CodeMsg.userError;
        }else{
            String token= UUIDUtils.uid();
            addCookie(token,response,user1);
            return CodeMsg.success;
        }
    }
    public void addCookie(String token, HttpServletResponse response, user user){
        /*
        1.将token写到cookie中，传递给客户端，标识用户信息。
        2.将token对应用户的信息保存在redis缓存中。
         */
        redisService.set(SelectUserKey.token,token,user);
        Cookie cookie=new Cookie(COOKIE1_NAME_TOKEN,token);
        //有效期
        cookie.setMaxAge(SelectUserKey.token.expireSeconds);
        cookie.setPath("/");
        response.addCookie(cookie);

    }
}
