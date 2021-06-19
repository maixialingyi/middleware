package com.mid.base.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class MainMianAnnotationAspect {

    @Around(value = "@annotation(com.mid.base.aspectj.MianAnnotation)")
    public Object sagaFlowProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        return null;
    }
}
