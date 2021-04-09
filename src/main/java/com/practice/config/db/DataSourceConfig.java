package com.practice.config.db;

import com.practice.enums.DbType;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.securityexample.*")
public class DataSourceConfig {

    private final ApplicationContext applicationContext;

    public DataSourceConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.mlb")
    public DataSource mlbDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari.dmo")
    public DataSource dmoDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("routingDataSource")
    public DataSourceRouting routingDataSource(@Qualifier("mlbDataSource") DataSource mlbDataSource,
                                               @Qualifier("dmoDataSource") DataSource dmoDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DbType.MLB, mlbDataSource);
        targetDataSources.put(DbType.DMO, dmoDataSource);

        DataSourceRouting dataSource = new DataSourceRouting();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(mlbDataSource);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("routingDataSource") DataSourceRouting dataSourceRouting) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceRouting);
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/**.xml"));
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/mybatis/mybatis-config.xml"));
        // factoryBean.setTransactionFactory(new ManagedTransactionFactory());
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSourceRouting dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
