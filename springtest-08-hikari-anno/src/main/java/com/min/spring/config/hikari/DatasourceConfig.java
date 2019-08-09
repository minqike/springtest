package com.min.spring.config.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration  //表示配置文件类
@PropertySource(value = {"classpath:jdbc.properties",},ignoreResourceNotFound=true)  //添加配置文件
public class DatasourceConfig {
    @Value("${jdbc.driverClass}")
    private String jdbc_driverClassName ;

    @Value("${jdbc.url}")
    private String jdbc_url ;
    @Value("${jdbc.username}")
    private String jdbc_username ;
    @Value("${jdbc.password}")
    private String jdbc_password ;
    @Value("${jdbc.pool.init}")
    private Integer jdbc_initialSize ;
    @Value("${jdbc.pool.minIdle}")
    private Integer jdbc_minIdle ;
    @Value("${jdbc.pool.maxWait}")
    private Integer jdbc_maxWait ;
    @Value("${jdbc.pool.maxActive}")
    private Integer jdbc_maxActive ;

    @Autowired
    private DataSource dataSource;


    @Bean
    public DataSource dataSource(){

        HikariConfig jdbcConfig = new HikariConfig();
        jdbcConfig.setPoolName(getClass().getName());
        jdbcConfig.setDriverClassName(jdbc_driverClassName);
        jdbcConfig.setJdbcUrl(jdbc_url);
        jdbcConfig.setUsername(jdbc_username);
        jdbcConfig.setPassword(jdbc_password);
        jdbcConfig.setMaximumPoolSize(jdbc_initialSize);
//        jdbcConfig.setMaxLifetime(maxLifetime);
//        jdbcConfig.setConnectionTimeout(connectionTimeout);
//        jdbcConfig.setIdleTimeout(idleTimeout);

        return new HikariDataSource(jdbcConfig);
    }


    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

}

