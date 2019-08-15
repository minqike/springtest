package com.min.PriorityQueueTest;

import java.util.PriorityQueue;

public class Test2 {
    private static PriorityQueue priorityQueue = new PriorityQueue();
    public static void main(String[] args) {
        priorityQueue.add(new Item2("a1",10));
        priorityQueue.add(new Item2("a2",20));
        priorityQueue.add(new Item2("a3",60));
        priorityQueue.add(new Item2("a4",30));
        priorityQueue.add(new Item2("a5",40));
        priorityQueue.add(new Item2("a6",50));

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

