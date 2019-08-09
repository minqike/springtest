package com.min.spring.config.servlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(value="com.min",
		includeFilters = { //代表字扫描的内容
				@ComponentScan.Filter(type= FilterType.ANNOTATION,classes={Controller.class})
		},
		useDefaultFilters = false)
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class DispatchServletConfig implements WebMvcConfigurer {

	@Value("${web.view.prefix}")
	private String WebViewPrefix;

	@Value("${web.view.suffix}")
	private String WebViewSuffix;

	//配置视图解析器
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(WebViewPrefix);
		resolver.setSuffix(WebViewSuffix);
		return resolver;
	}
	
	/**
	 * 没有此配置，dispatcherServlet会映射为应用的默认servlet，
	 * 所以他为处理所有请求，包括静态资源，如图片的样式表
	 * 这并不是我们想要的
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
