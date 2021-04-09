package com.securityexample.config.db;

import com.securityexample.enums.DbType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<DbType> CONTEXT = new ThreadLocal<DbType>();

    public static void setDbType(DbType type) {
        CONTEXT.set(type);
    }

    public static DbType getDbType() {
        return CONTEXT.get();
    }

    public static void remove() {
        CONTEXT.set(DbType.MLB);
    }
}
