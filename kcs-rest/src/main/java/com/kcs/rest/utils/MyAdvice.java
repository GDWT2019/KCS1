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
        System.out.println("---------------前置通知开始(注解)~~~~~~~~~~~");
        // 获取到类名
        String targetName = joinPoint.getTarget().getClass().getName();
        System.out.println("代理的类是:" + targetName);
        // 获取到方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("增强的方法是:" + methodName);
        // 获取到参数
        Object[] parameter = joinPoint.getArgs();
        System.out.println("传入的参数是:" + Arrays.toString(parameter));
        // 获取字节码对象
        Class<?> targetClass = Class.forName(targetName);
        // 获取所有的方法
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == parameter.length) {
                    System.out.println("找到这个方法");
                    break;
                }
            }
        }
        System.out.println("---------------前置通知结束(注解)~~~~~~~~~~~");
    }

    // 后置通知(异常发生后不会调用)
    @AfterReturning("MyAdvice.pc()")
    public void afterRunning() {
        System.out.println("这是后置通知(异常发生后不会调用)。。。。(注解)");
    }

    // 环绕通知
    @Around("MyAdvice.pc()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("----------------环绕通知之前 的部分(注解)----------------");
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法
        Method method = pjp.getTarget().getClass().getDeclaredMethod(methodSignature.getName(), methodSignature.getMethod().getParameterTypes());
        // 获取方法上面的注解
        LogAnno logAnno = method.getAnnotation(LogAnno.class);
        // 获取操作描述的属性值
        String operateType = logAnno.operateType();


        //所有用这种方式获取session中属性(亲测有效)
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        User user = (User) session.getAttribute("user");//获取session中的user对象


        // 让方法执行（proceed是方法的返回结果，可以针对返回结果做一些处理）
        System.out.println("--------------方法开始执行(注解)------------------");
        Object proceed = pjp.proceed();

        // 创建一个日志对象(准备记录日志)
        Log log = new Log();
        log.setOperation(operateType);// 操作说明
        log.setUserID(1);// 设置操作人
        if(user!=null){
            log.setUserID(user.getUserID());
        }
        log.setTime(GetTime.getTime());

        if (proceed==null){
            log.setResult("失败");
        }else
            log.setResult("成功");
        //logService.insertLog(log);
        // 环绕通知之后的业务逻辑部分
        System.out.println("----------------环绕通知之后的部分(注解)-----------------");
        return proceed;
    }

    // 异常通知
    @AfterThrowing("MyAdvice.pc()")
    public void afterException() {
        System.out.println("这是异常通知(发生异常后调用)~~~~~~~~~~~(注解)");
    }

    // 最终通知(发生异常也会在最终调用)
    @After("MyAdvice.pc()")
    public void after() {
        System.out.println("这是最终通知(发生异常也会在最终调用)........(注解)");
    }
}
