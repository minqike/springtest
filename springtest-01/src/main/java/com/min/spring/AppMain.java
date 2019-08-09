package com.min.spring;

import com.min.spring.config.MainConfig;
import com.min.spring.sevice.PersonService;
import com.min.spring.sevice.impl.PersonServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {

    public static void main(String[] args) {
        //创建容器,利用注解方式配置,MainConfig中用@ComponentScan指定了扫描包,
        //PersonService类上用@Servie标注,代表会生成对象交给容器管理
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        //获取容器中对象
        PersonService service1 = context.getBean("personServiceImpl", PersonService.class);
        PersonService service2 = context.getBean("person2ServiceImpl", PersonService.class);
        //获取对象在容器中的名字
        //@Servie注解可以指定生成的bean的名字,默认为第一个字母小写的类名
        String[] names = context.getBeanNamesForType(PersonServiceImpl.class);
        //打印对象在容器中的名字
        for (String name : names) {
            System.out.println("BeanName=" + name);
        }
        //调用对象的方法
        service1.say();
        service2.say();
    }
}
