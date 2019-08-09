
## 04 Spring整合SpringMVC(xml方式)

### **目录文件结构**

```
│  pom.xml
└─src
    └─main
        ├─java/com/min/spring/controller
        │                    	│- HelloController.java
        ├─resources
        │   │- application.properties
        │   │- spring-context.xml
        │   │- spring-mvc.xml
        └─webapp
            ├─static
            └─WEB-INF
                │- web.xml
                └─views
                    │- hello.jsp
                    │- index.jsp	
```


### 04.01 pom.xml

- 将原来的`spring-web`修改为`spring-webmvc`, 因为spring-webmvc中包含了spring-web
- `javax.servlet-api`需要提供,`<scope>provided</scope>`打包的时候会排除掉,
  - 如果去掉依赖的话否则`web.xml`中配置`DispatcherServlet`的时候会报错
- jackson-databind,解决@ResponseBody返回中文乱码用

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <scope>provided</scope>
    </dependency>
    <!--解决返回中文乱码时的需要用到的,配置StringHttpMessageConverter-->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
</dependencies>

```

### 04.02 Spring的配置文件(spring-context.xml)

- 配置扫描包,排除Controller注解, spring-mvc.xml中会配置扫描Controller

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
	http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-2.5.xsd">

    <!--<context:annotation-config></context:annotation-config>-->
    <context:component-scan base-package="com.min.spring">
        <context:exclude-filter type="annotation"
                  expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
</beans>
```

### 04.03 创建SpringMVC的配置文件(`spring-mvc.xml`)

- 加载属性配置文件`application.properties`
- 使用 Annotation 自动注册 Bean,只扫描 @Controller
- 配置RequestMappingHandlerAdapter 解决@Response放回中文为乱码
- 默认的注解映射的支持,提供Controller请求转发，json自动转换等功能
- 定义视图文件解析
- 静态资源映射

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <description>Spring MVC Configuration</description>

    <!-- 加载配置属性文件 -->
    <context:property-placeholder ignore-unresolvable="true" location="classpath:application.properties"/>

    <!-- 使用Annotation自动注册 Bean,只扫描 @Controller use-default-filters设置为false-->
    <context:component-scan base-package="com.min.spring"
                            use-default-filters="false">
        <context:include-filter type="annotation"
               expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!-- 必须放在<mvc:annotation-driven>之前 ,解决@Response放回中文为乱码-->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
        <property name="messageConverters">
            <list>
                <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                    <property name="supportedMediaTypes">
                        <list>
                            <value>text/plain;charset=UTF-8</value>
                            <value>text/html;charset=UTF-8</value>
                            <value>applicaiton/javascript;charset=UTF-8</value>
                        </list>
                    </property>
                </bean>
                <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>
            </list>
        </property>
    </bean>

    <!-- 默认的注解映射的支持 -->
    <mvc:annotation-driven />

    <!-- 定义视图文件解析 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="${web.view.prefix}"/>
        <property name="suffix" value="${web.view.suffix}"/>
    </bean>

    <!-- 静态资源映射 -->
    <mvc:resources mapping="/static/**" location="/static/" cache-period="31536000"/>
</beans>
```

### 04.04 创建属性配置文件(`application.properties`)

`spring-mvc.xml`中用到了该文件用来`定义视图文件解析`

```properties
web.view.prefix=/WEB-INF/views/
web.view.suffix=.jsp
```

### 04.05 配置`web.xml `

- Spring的配置listener:`org.springframework.web.context.ContextLoaderListener`
- Spring的配置spring的上下文的配置文件:`contextConfigLocation`
- 删除了原来的servlet的配置,由springmvc来配置

- 增加了SpringMVC用的DispatcherServlet, 配置 Spring 的 Servlet 分发器处理所有 HTTP 的请求和响应
- 配置字符集过滤器，用于解决中文编码问题

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-context*.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!--配置 Spring 的 Servlet 分发器处理所有 HTTP 的请求和响应-->
    <servlet>
        <servlet-name>springServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath*:/spring-mvc*.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--配置字符集过滤器，用于解决中文编码问题-->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

### 04.06 页面(`index.jsp`)

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
    <a href="/hello" >hello</a><br />
    <a href="/string" >返回字符串</a>
</body>
</html>
```

### 04.07 页面(`hello.jsp`)

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

### 04.08 Controller(`HelloController.java`)

- `/`	返回主页index.jsp
- `/hello`	返回页面hello.jsp
- `/string`	返回字符串
- `/map`	返回JSON字符串

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
