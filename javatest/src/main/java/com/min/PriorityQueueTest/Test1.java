package com.min.PriorityQueueTest;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Test1 {
    private static PriorityQueue priorityQueue = new PriorityQueue(new Comparator<Item1>() {
        @Override
        public int compare(Item1 o1, Item1 o2) {
            return o1.getAge() - o2.getAge();
        }
    });
    public static void main(String[] args) {
        priorityQueue.add(new Item1("a1",10));
        priorityQueue.add(new Item1("a2",20));
        priorityQueue.add(new Item1("a3",60));
        priorityQueue.add(new Item1("a4",30));
        priorityQueue.add(new Item1("a5",40));
        priorityQueue.add(new Item1("a6",50));

        while (true) {
            Object poll = priorityQueue.poll();
            if(poll != null){
                System.out.println(poll);
            }else{
                break;
            }
        }
    }
}
