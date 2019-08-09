## 03 Spring整合Servlet

文件结构

```
pom.xml
src/main
      │ java/com/min/spring/controller/HelloController.java
      │ resources/spring-context.xml
      │ webapp
        │ hello.jsp
        │ index.html
        └─WEB-INF
          │ web.xml
```



### 03.01 追加依赖(`pom.xml`)

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
</dependency>
```

`spring-web`:在web.xml中配置的listener:`ContextLoaderListener`来自这个包

`javax.servlet-api`:这个包在写Controller的时候需要用到

### 03.02 添加一个Controller(`HelloController.java`)

- 配置一个Controller /hello,跳转到hello.jsp,并且传递一个参数message

```java
@Controller
public class HelloController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        					throws ServletException, IOException {
        req.getSession().setAttribute("message", "你好,name");
        resp.sendRedirect(req.getContextPath() + "/hello.jsp");
    }
}
```

###03.03 添加一个hello.jsp文件

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
   hello.jsp,${message}
</body>
</html>
```

### 03.04  配置spring的配置文件(`spring-context.xml`)

- 启动注解:`context:annotation-config`
- 指定扫描的包:`context:component-scan`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
	http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-2.5.xsd">

    <context:annotation-config></context:annotation-config>
    <context:component-scan base-package="com.min.spring"></context:component-scan>

</beans>
```



### 03.05 添加web.xml

- 配置listener:`org.springframework.web.context.ContextLoaderListener`
- 配置spring的上下文的配置文件:`contextConfigLocation`
- 配置Controller地址:`/hello`

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
        <listener-class>
            org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
    
	<!--配置controller-->
    <servlet>
        <servlet-name>helloServlet</servlet-name>
        <servlet-class>com.min.spring.controller.HelloController</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>helloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>

</web-app>
```

