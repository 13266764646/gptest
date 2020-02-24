package com.gwy.test.mashibing.c_pool;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T10_SchedulePool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newScheduledThreadPool(4);
        ((ScheduledExecutorService) service).scheduleAtFixedRate(()->{
            int number  = new Random().nextInt(1000);
            try {
                TimeUnit.MICROSECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-------number-------"+number);
        }, 0,1, TimeUnit.SECONDS);
    }
}