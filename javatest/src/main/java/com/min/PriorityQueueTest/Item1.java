package com.min.PriorityQueueTest;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Item1 {
    private String name;
    private int age;

    public void setAge(int age) {
        this.age = age;
    }
}
