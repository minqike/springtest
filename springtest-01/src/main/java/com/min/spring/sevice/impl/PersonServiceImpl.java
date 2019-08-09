package com.min.spring.sevice.impl;

import com.min.spring.entity.Person;
import com.min.spring.sevice.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public void say() {
        Person person = new Person(1, "张三", 30);
        System.out.println(person.toString());
    }
}
