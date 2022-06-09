package com.fic091.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogUtil{
    @Pointcut("execution(public int com.fic091.aop.MyMathCalculator.add(int,int))")
    public void youStupid(){}

    //@Before("youStupid()")
    public static void start(JoinPoint jointPoint){
        Object[] args = jointPoint.getArgs();
        Signature signature = jointPoint.getSignature();
        System.out.println(signature.getName());
        System.out.println("开始执行"+ Arrays.asList(args));
    }

    //@AfterReturning(value="execution(public int com.fic091.aop.MyMathCalculator.add(int,int))",returning = "result")
    public static void afterReturning(JoinPoint joinPoint, Object result){
        System.out.println("返回值是"+result);
    }

    @Around("youStupid()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint){
        Object[] args=proceedingJoinPoint.getArgs();
        Object proceed=null;
        try {
            System.out.println("前置通知");
           proceed=proceedingJoinPoint.proceed(args);
            System.out.println("后置通知");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            //返回值
            return proceed;
        }

    }

}


