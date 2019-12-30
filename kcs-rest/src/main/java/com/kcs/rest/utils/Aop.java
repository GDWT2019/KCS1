package com.kcs.rest.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;



@Aspect // 表示该类是一个通知类
@Component // 交给spring管理
public class Aop {

    // 定义一个空方法，借用其注解抽取切点表达式
    @Pointcut("execution(* com.kcs.rest.service.impl.*ServiceImpl.*(..))")
    public void AopPC() {
    }

    // 前置通知(下面两种方式均可以)
    // @Before("Aop.AopPC()")
    @Before("AopPC()")
    public void before(JoinPoint joinPoint) throws Exception {
        System.out.println("---------------前置通知开始(注解)~~~~~~~~~~~");

    }

    // 后置通知(异常发生后不会调用)
    @AfterReturning("AopPC()")
    public void afterRunning() {
        System.out.println("这是后置通知(异常发生后不会调用)。。。。(注解)");
    }

    // 环绕通知
    @Around("AopPC()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("----------------环绕通知之前 的部分(注解)----------------");

        // 让方法执行（proceed是方法的返回结果，可以针对返回结果做一些处理）
        System.out.println("--------------方法开始执行(注解)------------------");
        Object proceed = pjp.proceed();

        // 环绕通知之后的业务逻辑部分
        System.out.println("----------------环绕通知之后的部分(注解)-----------------");
        return proceed;
    }

    // 异常通知
    @AfterThrowing("AopPC()")
    public void afterException() {
        System.out.println("这是异常通知(发生异常后调用)~~~~~~~~~~~(注解)");
    }

    // 最终通知(发生异常也会在最终调用)
    @After("AopPC()")
    public void after() {
        System.out.println("这是最终通知(发生异常也会在最终调用)........(注解)");
    }
}
