package com.gwy.test.mashibing.c_201;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T03_NotifyHodingLock {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        final  Object o = new Object();

        new Thread(()->{
            synchronized (o){
                System.out.println("t2 启动");
                if(list.size() != 5){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                o.notify();
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 启动");
            synchronized (o){
                for(int i =0;i< 10;i++){
                    list.add(i);
                    System.out.println("add----"+i);
                    if(i == 5){
                        o.notify();
                        try {
                            o.wait();
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
            }
            System.out.println();
            System.out.println("t1  结束");

        },"t1").start();
    }
}