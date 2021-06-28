package com.test.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Operator {

    @Pointcut("execution(* com.test.spring.aop..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("@Before");
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("@After");
    }

    @AfterReturning(pointcut="pointCut()",returning="returnVal")
    public void afterReturn(JoinPoint joinPoint,Object returnVal){
        System.out.println("@AfterReturning" + returnVal);
    }

    /*@AfterThrowing(pointcut="pointCut()",throwing="error")
    public void afterThrowing(JoinPoint joinPoint,Throwable error){
        System.out.println("AOP AfterThrowing Advice..." + error);
        System.out.println("AfterThrowing...");
    }*/

    /*@Around("pointCut()")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("@Around before...");
        try {
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("@Around after...");
    }*/
}
