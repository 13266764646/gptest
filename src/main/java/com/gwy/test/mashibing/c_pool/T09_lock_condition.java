package com.gwy.test.mashibing.c_pool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T09_lock_condition {

    public static void main(String[] args) {
        char[] an = "123456789".toCharArray();
        char[] ai ="ABCDEFJHI".toCharArray();

        Lock lock = new ReentrantLock();
        Condition t1 = lock.newCondition();
        Condition t2 = lock.newCondition();




        new Thread(()->{
            try {
                lock.lock();
                for(char n : ai){
                    System.out.println(Thread.currentThread().getName()+"------i--------"+n);
                    t2.signal();
                    try {
                        t1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                t2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();

        new Thread(()->{
            try {
                lock.lock();
                for(char n : an){
                    System.out.println(Thread.currentThread().getName()+"------number--------"+n);
                    t1.signal();
                    try {
                        t2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                t1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        },"t2").start();
    }
}