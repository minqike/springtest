package com.min.spring.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration  //表示配置文件类
@PropertySource(value = {"classpath:jdbc.properties",},ignoreResourceNotFound=true)  //添加配置文件
public class DruidDatasourceConfig {
    // 参考链接 https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE
//    @Value("${jdbc.driverClass}")
//    private String jdbc_driverClassName ;

    @Value("${jdbc.connectionURL}")
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

    @Bean(destroyMethod="close",initMethod="init")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbc_url);
//        dataSource.setDriverClassName(jdbc_driverClassName);
        dataSource.setPassword(jdbc_password);
        dataSource.setUsername(jdbc_username);
        dataSource.setInitialSize(jdbc_initialSize);
        dataSource.setMinIdle(jdbc_minIdle);
        dataSource.setMaxWait(jdbc_maxWait);
        dataSource.setMaxActive(jdbc_maxActive);
        dataSource.setValidationQuery("select 1 from dual");
        dataSource.setFilters("stat");  //配置以后监控中心才会分析执行的sql
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

}
