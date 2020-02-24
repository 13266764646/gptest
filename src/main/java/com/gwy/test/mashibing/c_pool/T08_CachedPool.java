package com.gwy.test.mashibing.c_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T08_CachedPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService =  Executors.newCachedThreadPool();
        System.out.println(executorService);

        for(int i =0;i < 2;i++){
            executorService.execute(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println("----------------"+executorService);
        TimeUnit.SECONDS.sleep(50);
        System.out.println("****************"+executorService);
        TimeUnit.SECONDS.sleep(80);
        System.out.println("%%%%%%%%%%%%%%%%"+executorService);
    }
}