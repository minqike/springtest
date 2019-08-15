package com.min.PriorityQueueTest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Item2 implements Comparable<Item2>{
    private String name;
    private int age;


    @Override
    public int compareTo(Item2 o) {
        return this.age - o.age;//从小到大
    }
}