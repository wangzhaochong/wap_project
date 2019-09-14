package com.kacha.wapproj.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.kacha.wapproj.util.Resp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
public class WebLogAspect {

    Logger log  = LoggerFactory.getLogger(this.getClass());

    @Value("${stat.aop.timeout}")
    private long timeout;

    @Pointcut("execution( * com.kacha.wapproj..controller.*.*(..))")//..代表所有子目录，最后括号里的两个..代表所有参数
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob;
        try{
            ob = pjp.proceed();// ob 为方法的返回值
        }catch (Exception e){
            long span =  (System.currentTimeMillis() - startTime);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String args = "";
            Map param = request.getParameterMap();
            if(param != null){
                args = JSON.toJSONString(param);
            }
            log.error("Exception at request! | time cost:{}ms | url:{} | args:{} | exception: {}", span, url, args, e);
            return Resp.error();
        }

        long span =  (System.currentTimeMillis() - startTime);
        if(span > timeout){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String args = "";
            Map param = request.getParameterMap();
            if(param != null){
                args = JSON.toJSONString(param);
            }
            log.error("time cost:{}ms | url:{} | args:{}", span, url, args);
        }

        return ob;
    }
}
