package com.experimental.douban.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
/**
 * AOP 面向切面编程
 * 检测业务层方法完成时间，在不改变项目主体代码情况下完成此功能
 * */
@Aspect
@Component
public class TimeAccountAop {
    @Around("execution(* com.experimental.douban.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint point)throws Throwable{
        long start = System.currentTimeMillis();
        Object result = point.proceed();//调用相关方法
        long end = System.currentTimeMillis();
        System.out.println("耗时为"+(end-start));
        return result;
    }

}
