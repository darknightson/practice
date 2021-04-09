package com.securityexample.config.annotation;

import com.securityexample.enums.DbType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceRoutingKey {
    DbType value() default DbType.MLB;
}
