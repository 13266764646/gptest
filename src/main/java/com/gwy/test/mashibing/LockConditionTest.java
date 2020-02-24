package com.gwy.test.mashibing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {
    static Thread t1 = null,t2 = null;

    public static void main(String[] args) {
        char[] cn = "123456789".toCharArray();
        char[] ci = "ABCDEFJHI".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();//d队列
        Condition condition2 = lock.newCondition();

        t1 = new Thread(()->{
            try{
                lock.lock();
                for(char a : cn){
                         System.out.println("t1--------"+a);
                         condition2.signal();
                         condition1.await();
                }
                 condition2.signal();
            }catch (Exception e) {
                System.out.println(e);
            }finally {
                lock.unlock();
            }
        },"t1");

        t2 = new Thread(()->{
            try{
                lock.lock();
                for(char a : ci){
                        System.out.println("t2--------"+a);
                        condition1.signal();
                        condition2.await();
                }
                condition1.signal();
            }catch (Exception e) {
                System.out.println(e);
            }finally {
                lock.unlock();
            }
        },"t2");

        t1.start();
        t2.start();

    }
}