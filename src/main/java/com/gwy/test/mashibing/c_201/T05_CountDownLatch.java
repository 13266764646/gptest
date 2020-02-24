package com.gwy.test.mashibing.c_201;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T05_CountDownLatch {
    volatile List<Object> lists = new ArrayList<>();

    public void add(Object o){
        lists.add(o);
    }


    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch c = new T05_CountDownLatch();

        CountDownLatch latch = new CountDownLatch(1);



        final  Object o = new Object();

        new Thread(()->{
                System.out.println("t2 启动");
                if(c.size() != 5){
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 启动");
            for(int i =0;i< 10;i++){
                c.add(i);
                System.out.println("add----"+i);
                if(i == 5){
                   latch.countDown();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
               try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            System.out.println("t1  结束");

        },"t1").start();
    }
}