package com.securityexample.config.db;

import com.securityexample.config.annotation.DataSourceRoutingKey;
import com.securityexample.enums.DbType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DataSourceAspect {
    @Pointcut("execution(public * com.securityexample..service..*Service.*(..)) " + "&& @annotation(com.securityexample.config.annotation.DataSourceRoutingKey)")
    public void dataSourcePointcut() {
    }

    @Around("dataSourcePointcut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        DataSourceRoutingKey dataSourceRoutingKey = method.getAnnotation(DataSourceRoutingKey.class);
        DbType sourceEnum = dataSourceRoutingKey.value();

        if (sourceEnum == DbType.MLB) {
            DataSourceContextHolder.setDbType(DbType.MLB);
        } else if (sourceEnum == DbType.DMO) {
            DataSourceContextHolder.setDbType(DbType.DMO);
        }

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            DataSourceContextHolder.remove();
        }
        return result;
    }
}