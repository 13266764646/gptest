package com.gwy.test.mashibing.c_collection.queue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList<>();

        Random r = new Random();

        Thread[] ths = new Thread[100];

        for(int i = 0 ; i < ths.length;i++){
            ths[i] =new Thread(() ->{
                for(int j =0;j<1000;j++){
                    lists.add("a"+r.nextInt(100000));
                }
                System.out.println(Thread.currentThread().getName()+"----");
            });
        }

        Long start =  System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> {
            System.out.println(Thread.currentThread().getName()+"***************"+t.getName());
            t.start();
        });

        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();

        System.out.println(end -start);
        System.out.println(lists.size());
    }
}