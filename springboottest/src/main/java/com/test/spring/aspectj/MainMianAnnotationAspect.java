package com.test.spring.aspectj;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class MainMianAnnotationAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Around(value = "@annotation(com.test.spring.aspectj.MianAnnotation)")
    public Object sagaFlowProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        //调用业务方法
        joinPoint.proceed();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 方法参数类型List
        List<String> methodParamTypeList = Arrays.stream(methodSignature.getParameterTypes()).map(Class::getName).collect(Collectors.toList());

        // 参数类型List
        List<String> paramTypeList = new ArrayList<>();
        // 参数值的List
        List<String> paramJsonList = new ArrayList<>();

        //joinPoint.getArgs() JSON字符串后会把null的入参去掉，这里需处理
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Object arg = joinPoint.getArgs()[i];
            paramJsonList.add(JSON.toJSONString(arg));
            paramTypeList.add(null == arg ? methodParamTypeList.get(i) : arg.getClass().getName());
        }

        Class<?> targetClass = joinPoint.getTarget().getClass();
        Object service = applicationContext.getBean(targetClass);
        Method method = methodSignature.getMethod();

        /** 使用joinPoint.getArgs()反射 */
        //ReflectionUtils.invokeMethod(method, service,joinPoint.getArgs());

        /** 使用json串反射 */
        List<Object> objectList = Lists.newArrayList();
        for (int i = 0; i < paramTypeList.size(); i++) {
            objectList.add(JSON.parseObject(paramJsonList.get(i),Thread.currentThread().getContextClassLoader().loadClass(paramTypeList.get(i))));
        }
       ReflectionUtils.invokeMethod(method, service,objectList.toArray());

        return null;
    }

}
