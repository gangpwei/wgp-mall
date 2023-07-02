package com.wgp.mall.common.log;

import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author gangpeng.wgp
 * @date 2023/7/2 下午2:30
 */
@Component
@Aspect
@Slf4j
public class LogInterceptor {

    @Around("execution(* com.wgp.mall..*.*(..))")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clz = joinPoint.getSignature().getDeclaringType();

        String clzName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        Log annotation = getLogAnnotation(joinPoint, clz);

        if(annotation != null && annotation.logInput()){
            log.info("打印入参, method:{}.{}, args:{}", clzName, methodName, args);
        }

        Object result;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            log.error("打印出参, method:{}.{}, args:{}, exception:{}", clzName, methodName, args, throwable);
            throw throwable;
        }

        if(annotation != null && annotation.logOutput()){
            log.info("打印出参, clzName:{}, methodName:{}, args:{}, result:{}", clzName, methodName, args, JSON.toJSONString(result));
        }
        return result;
    }

    /**
     * 从方法或者类上取Log注解
     * @param joinPoint
     * @param clz
     * @return
     */
    private Log getLogAnnotation(ProceedingJoinPoint joinPoint, Class clz) {
        Log clzAnnotation = (Log)clz.getAnnotation(Log.class);

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log methodAnnotation = method.getAnnotation(Log.class);

        return methodAnnotation == null ? clzAnnotation : methodAnnotation;
    }

}
