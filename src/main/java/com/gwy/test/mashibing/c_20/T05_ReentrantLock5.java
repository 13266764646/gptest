package com.gwy.test.mashibing.c_20;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock5  implements Runnable{
    private static ReentrantLock lock = new ReentrantLock(true);

    public  void run(){
        for(int i = 0; i < 100;i++){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 r1 = new T05_ReentrantLock5();
        Thread th1 = new Thread(r1);

        Thread th2 = new Thread(r1);

        th1.start();

        th2.start();
    }
}