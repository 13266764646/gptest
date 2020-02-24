package com.gwy.test.mashibing.c_20;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class T11_TestSemphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("t1 starting.....");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t1 end.........");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        },"t1").start();


        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("t2 starting.....");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t2 end.........");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        },"t2").start();
    }
}