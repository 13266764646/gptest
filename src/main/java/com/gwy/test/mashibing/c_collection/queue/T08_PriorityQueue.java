package com.gwy.test.mashibing.c_collection.queue;

import java.util.PriorityQueue;

public class T08_PriorityQueue {

    static PriorityQueue priorityQueue = new PriorityQueue();

    public static void main(String[] args) {
        priorityQueue.add("a");
        priorityQueue.add("e");
        priorityQueue.add("y");
        priorityQueue.add("b");
        priorityQueue.add("c");

        for(int i =0;i < 5;i++){
            System.out.println(priorityQueue.poll());
        }

    }

}