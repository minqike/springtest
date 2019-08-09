package com.min.spring.sevice.impl;

import com.min.spring.entity.Person;
import com.min.spring.sevice.PersonService;
import org.springframework.stereotype.Service;

@Service
public class Person2ServiceImpl implements PersonService {

    @Override
    public void say() {
        Person person = new Person(2, "李四", 20);
        System.out.println(person.toString());
    }
}
