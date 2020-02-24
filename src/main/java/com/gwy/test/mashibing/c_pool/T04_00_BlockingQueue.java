package com.gwy.test.mashibing.c_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T04_00_BlockingQueue {

    static BlockingQueue q1 = new ArrayBlockingQueue(1);
    static BlockingQueue q2 = new ArrayBlockingQueue(1);

    public static void main(String[] args) {
        char[] an = "123456789".toCharArray();
        char[] ai ="ABCDEFJHI".toCharArray();

        new Thread(()->{
            for (char n : an){
                try {
                    q1.take();
                    q2.put("ok");
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"----------------"+n);
            }
        },"t1").start();


        new Thread(()->{
            for (char n : ai){
                try {
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"----------------"+n);
            }
        },"t2").start();

    }
}