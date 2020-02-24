package com.gwy.test.mashibing.c_collection.queue;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T01_ConcrrentMap {

    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();

        Random r = new Random();

        Thread[] threads = new Thread[100];

        CountDownLatch latch = new CountDownLatch(100);

        Long start = System.currentTimeMillis();

        for( int i =0;i < 100;i++){
            threads[i]  =  new Thread(()->{
                for(int k =0;k<1000;k++){
                    map.put("a"+r.nextInt(1000000)+Math.random(), "a"+r.nextInt(100000));
                }
                System.out.println(latch.getCount()+"------1111111111--------");
                latch.countDown();
            });
        }


        Arrays.asList(threads).forEach(t ->t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println(end-start);
        System.out.println(map.size());
    }
}