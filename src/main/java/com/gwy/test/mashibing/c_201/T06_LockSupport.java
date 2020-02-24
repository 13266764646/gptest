package com.gwy.test.mashibing.c_201;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T06_LockSupport {
    volatile List<Object> lists = new ArrayList<>();

    public void add(Object o){
        lists.add(o);
    }


    public int size(){
        return lists.size();
    }

    static Thread t1 = null, t2 = null;
    public static void main(String[] args) {
        T05_CountDownLatch c = new T05_CountDownLatch();

        CountDownLatch latch = new CountDownLatch(1);



        final  Object o = new Object();

         t2 = new Thread(()->{
            System.out.println("t2 启动");
            if(c.size() != 5){
                try {
                    LockSupport.park();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
            LockSupport.unpark(t1);
        },"t2");

        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         t1 = new Thread(()->{
            System.out.println("t1 启动");
            for(int i =0;i< 10;i++){
                c.add(i);
                System.out.println("add----"+i);
                if(i == 5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println();
            System.out.println("t1  结束");

        },"t1");
        t1.start();
    }
}