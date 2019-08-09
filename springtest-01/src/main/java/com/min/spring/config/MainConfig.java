package com.min.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("com.min.spring")
@ImportResource("classpath:beans.xml")
public class MainConfig {
}
