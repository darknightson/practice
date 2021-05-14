package com.practice.config.db;

import com.practice.config.annotation.DataSourceRoutingKey;
import com.practice.enums.DbType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author  : anthony.son
 * @since   : 2021. 05
 * @version : 1.0
 */

import java.lang.reflect.Method;

@Component
@Aspect
public class DataSourceAspect {
    @Pointcut("execution(public * com.practice..service..*Service.*(..)) " + "&& @annotation(com.practice.config.annotation.DataSourceRoutingKey)")
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