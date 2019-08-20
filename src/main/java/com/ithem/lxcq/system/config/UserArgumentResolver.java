package com.ithem.lxcq.system.config;

import com.ithem.lxcq.system.service.SelectUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.ithem.lxcq.system.domain.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    SelectUserService selectUserService;
    /*
    获取参数类型，如果类型是user，就进行下列函数的处理，否则不做处理
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz=methodParameter.getParameterType();
        return clazz==user.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //为了获取cookie的token内容，需要request
        HttpServletRequest request=nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response=nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        //request.getParameter(SelectUserService.COOKIE1_NAME_TOKEN)
        /*
        PC端-客户端取cookie的值
        一个客户端可能有很多个cookie，因此要遍历寻找需要的cookie
         */
        String cookieToken=getCookieValue(request,SelectUserService.COOKIE1_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken)){
            return "index";
        }else {
            user user = selectUserService.getByToken(cookieToken);
            return user;
        }
    }
    public String getCookieValue(HttpServletRequest request,String cookiename){
        Cookie[] cookies=request.getCookies();
        if(cookies==null||cookies.length<=0){
            return null;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(cookiename)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
