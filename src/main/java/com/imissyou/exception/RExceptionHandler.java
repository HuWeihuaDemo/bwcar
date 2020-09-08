package com.imissyou.exception;

import com.imissyou.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RExceptionHandler {

    @ExceptionHandler(RExeption.class)
    @ResponseBody
    public R rExp(RExeption rExeption){
        R r = new R();
        r.put("code",500);
        r.put("msg",rExeption.getMessage());
        return r;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R RllExp(Exception exception){
        R r = new R();
        r.put("code",500);
        r.put("msg","系统内部异常！");
        return r;
    }

    @ExceptionHandler(AuthorizationException.class)
    public String authorExcep(AuthorizationException auth){
        return "redirect:unauthorized.html";
    }

}