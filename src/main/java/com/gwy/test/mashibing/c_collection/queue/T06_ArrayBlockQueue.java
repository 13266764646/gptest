package com.gwy.test.mashibing.c_collection.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        for(int i =0; i < 10;i++){
            strs.add("a"+i);
        }

        strs.offer("aaa",1, TimeUnit.SECONDS);
        System.out.println(strs);

    }
}