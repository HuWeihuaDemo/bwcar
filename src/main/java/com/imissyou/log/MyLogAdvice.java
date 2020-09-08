package com.imissyou.log;

import com.alibaba.fastjson.JSON;
import com.imissyou.utils.HttpContextUtils;
import com.imissyou.utils.IPUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;


@Aspect
@Component
public class MyLogAdvice {

    private Logger logger = Logger.getLogger(MyLogAdvice.class);

    @Pointcut("@annotation(com.imissyou.log.MyLog)")
    public void myPointcut(){

    }
    //开发通知
    @AfterReturning(pointcut = "myPointcut()")
    public void myAfterRet(JoinPoint joinPoint){
        MethodSignature mockito = (MethodSignature) joinPoint.getSignature();
        Method method = mockito.getMethod();
        MyLog annotation = method.getAnnotation(MyLog.class);
        String operation = null;
        if (annotation != null){
            operation = annotation.value();
        }
        String ip = IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());
        Object[] aegs = joinPoint.getArgs();
        String toJSONString = JSON.toJSONString(aegs);
        String methodName = joinPoint.getTarget().getClass().getName() + "." + method.getName();
        logger.info(new Date()+"|"+operation+"|"+ip+"|"+toJSONString+"|"+methodName);

    }

}
