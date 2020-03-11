package com.kcs.rest.utils;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.kcs.rest.pojo.Log;
import com.kcs.rest.pojo.User;
import com.kcs.rest.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Aspect // 表示该类是一个通知类
@Component // 交给spring管理
public class MyAdvice {

    @Autowired
    private LogService logService;// 日志Service

    // 定义一个空方法，借用其注解抽取切点表达式
    @Pointcut("@annotation(com.kcs.rest.utils.LogAnno)")
    public void pc() {
    }

    // 前置通知(下面两种方式均可以)
    // @Before("MyAdvice.pc()")
    @Before("pc()")
    public void before(JoinPoint joinPoint) throws Exception {
        // 获取到类名
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        // 获取到参数
        Object[] parameter = joinPoint.getArgs();;
        // 获取字节码对象
        Class<?> targetClass = Class.forName(targetName);
        // 获取所有的方法
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == parameter.length) {
                    break;
                }
            }
        }
    }

    // 后置通知(异常发生后不会调用)
    @AfterReturning("MyAdvice.pc()")
    public void afterRunning() {
        System.out.println("这是后置通知");
    }

    // 环绕通知
    @Around("MyAdvice.pc()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法
        Method method = pjp.getTarget().getClass().getDeclaredMethod(methodSignature.getName(), methodSignature.getMethod().getParameterTypes());
        // 获取方法上面的注解
        LogAnno logAnno = method.getAnnotation(LogAnno.class);
        // 获取操作描述的属性值
        String operateType = logAnno.operateType();
        User user = GetSession.getUser();//获取session中的user对象


        // 让方法执行（proceed是方法的返回结果，可以针对返回结果做一些处理）
        Object proceed = pjp.proceed();

        // 创建一个日志对象(准备记录日志)
        Log log = new Log();
        log.setOperation(operateType);// 操作说明
        if(user!=null){
            log.setUserID(user.getUserID());
        }
        log.setTime(GetTime.getTime());

        if (proceed==null){
            log.setResult("失败");
        }else
            log.setResult("成功");
        if (user.getUserID()!=null){
            logService.insertLog(log);
        }
        // 环绕通知之后的业务逻辑部分
        return proceed;
    }

    // 异常通知
    @AfterThrowing("MyAdvice.pc()")
    public void afterException() {
        System.err.println("这是异常通知");
    }

    // 最终通知(发生异常也会在最终调用)
    @After("MyAdvice.pc()")
    public void after() {
        System.out.println("这是最终通知");
    }
}
