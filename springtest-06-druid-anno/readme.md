## 05 Spring整合SpringMVC(纯注解模式)

### 文件目录结构

```
│  pom.xml
└─src
    └─main
        ├─java/com/min/spring
        │              ├─config/WebMvcConfig.java
        │              │  ├─listener/ContextLoaderListenerConfig.java
        │              │  ├─servlet/DispatchServletConfig.java
        │              │  └─web/WebConfiguration.java
        │              └─controller
        │                      HelloController.java
        ├─resources
        │      application.properties
        └─webapp
            ├─static
            └─WEB-INF
                └─views
                        hello.jsp
                        index.jsp
```

### 05.01 配置文件(`WebConfiguration.java`)

- 项目发布后实现AbstractAnnotationConfigDispatcherServletInitializer接口会自动运行

- 加载`ContextLoaderListenerConfig.java`配置
- 加载`DispatchServletConfig.java`配置

```java
public class WebConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 返回带有@Configuration注解的类会用来定义ContextLoaderListener
	 * 创建ApplicationContext中的bean(非web组件)
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[] {ContextLoaderListenerConfig.class};
	}

	/**
	 * 返回带有@Configuration注解的类用来定义DispatcherServlet上下文中的bean(web组件)
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class<?>[] {DispatchServletConfig.class};
	}

	/**
	 * 请求路径配置，配置为"/"表示它将处理所有请求
	 */
	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}
	@Override
	protected Filter[] getServletFilters() {

		return new Filter[] {
				new CharacterEncodingFilter("utf-8",true)};
	}
}
```

### 05.02 配置文件(`ContextLoaderListenerConfig.java`)

- 等同于配置文件`spring-context.xml`

```java
@Configuration
@ComponentScan(value="com.min",
        excludeFilters = { //扫描排除的内容
           @ComponentScan.Filter(type= FilterType.ANNOTATION,
           classes={Controller.class})
        })
public class ContextLoaderListenerConfig {

}
```

### 05.03 配置文件(`DispatchServletConfig.java`)

- 配置视图解析器

- 扫描Controller注解

```java
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
```
### 05.04 配置文件(`WebMvcConfig.java`)

- StringHttpMessageConverter
- 静态资源配置

```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        System.out.println("extendMessageConverters222 run");
        StringHttpMessageConverter stringConverter
                = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(0, stringConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/")
                .setCachePeriod(31536000);

    }
}
```

### 05.05 控制器Controller(`HelloController.java`)

```java
@Controller
public class HelloController {
    @RequestMapping("/")
    public String hello(){
        return "index";
    }
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message", "你好,MVC");
        return "hello";
    }

    @RequestMapping("/string")
    @ResponseBody
    public String string(){
        return "string字符串";
    }

    @RequestMapping("/map")
    @ResponseBody
    public Map<String,String> getmap(){
        Map<String,String> map=new HashMap<>();
        map.put("A","姓名");
        map.put("B", "年龄");
        return map;
    }
}
```

### 05.06 页面(`index.jsp`)

`WEB-INF/views/index.jsp`

```html
<%@ page contentType="text/html;charset=UTF-8" language="java"  
    pageEncoding="UTF-8"  %>
<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>
    Hello index <br />
    <a href="/hello" >hello.jsp页面</a><br />
    <a href="/string" >返回字符串</a><br />
    <a href="/map" >返回JSON字符串</a>
</body>
</html>
```

### 05.07 页面(`hello.jsp`)

`WEB-INF/views/hello.jsp`

```html
<%@ page contentType="text/html;charset=UTF-8" 
    language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
   hello.jsp,${message}
</body>
</html>
```

