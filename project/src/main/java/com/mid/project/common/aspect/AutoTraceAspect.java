package com.mid.project.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

//切面编程    可做日志服务发送,链路追踪,方法重试调用
@Aspect
@Slf4j
@Component
public class AutoTraceAspect {
    @Around(value = "@annotation(com.jsy.service.common.aspect.AutoTracer)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AutoTracer tracer = AnnotationUtil.findAnnotation(joinPoint, AutoTracer.class);
        String spanName = tracer.spanName();
        if (Strings.isBlank(spanName)) {
            String className = joinPoint.getClass().getName();
            String methodName = methodSignature.getName();
            spanName = className + "." + methodName;

        }
        log.info("切面前调用 spanName={}", spanName);
        try {
            //执行调用方法
            return joinPoint.proceed();
        } catch (Exception e) {
            log.warn("执行业务方法异常", e);
            throw e;
        }finally {
            log.info("切面后调用");
        }
    }
}
