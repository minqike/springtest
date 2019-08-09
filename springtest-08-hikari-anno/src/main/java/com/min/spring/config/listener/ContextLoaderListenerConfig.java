package com.min.spring.config.listener;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value="com.min",
        excludeFilters = { //扫描排除的内容
           @ComponentScan.Filter(type= FilterType.ANNOTATION,classes={Controller.class})
        })
public class ContextLoaderListenerConfig {

}
