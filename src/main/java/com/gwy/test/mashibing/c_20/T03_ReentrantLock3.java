package com.gwy.test.mashibing.c_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T03_ReentrantLock3 {

    Lock lock = new ReentrantLock();

    void m(){
        try {
            lock.lock();
            for(int i =0;i < 3;i++){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    void mm(){
        boolean locked = false;

        try {
          locked =   lock.tryLock(5, TimeUnit.SECONDS);

          System.out.println("mm()---------------"+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked)
                lock.unlock();
        }
    }


    public static void main(String[] args) {
        T03_ReentrantLock3 t = new T03_ReentrantLock3();
        new Thread(() -> t.m(),"thread1").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> t.mm(),"thread2").start();

    }

}