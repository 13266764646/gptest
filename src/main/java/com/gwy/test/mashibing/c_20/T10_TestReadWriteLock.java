package com.gwy.test.mashibing.c_20;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T10_TestReadWriteLock {
    static Lock lock = new ReentrantLock();

    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static  Lock readLock = readWriteLock.readLock();
    static  Lock writeLock = readWriteLock.writeLock();

    private static void read(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void write(Lock lock,int v){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write over!");
            value = v;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
            Long begin = System.currentTimeMillis();
        for(int i = 0;i < 18;i++){
            new Thread(() ->read(lock),"R"+i).start();
        }

        for(int i = 0;i < 2;i++){
            new Thread(() ->write(lock,new Random().nextInt()),"W"+i).start();
        }

//            new Thread(() ->read(readLock),"R"+i).start();

//        for(int i = 0;i < 18;i++){
//        }
//
//        for(int i = 0;i < 2;i++){
//            new Thread(() ->write(writeLock,new Random().nextInt()),"W"+i).start();
//        }

//        System.out.println("------------cost---------time-----"+(System.currentTimeMillis()-begin));
    }
}