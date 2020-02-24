package com.gwy.test.mashibing.c_20;

import java.util.concurrent.Exchanger;

public class T12_TestExchange {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger();

        new Thread(()->{
            String s = "T1";
            try {
                String value = exchanger.exchange(s);
                System.out.println("t1......threadName....."+Thread.currentThread().getName()+"--------value---------"+value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            String s = "T2";
            try {
                String value = exchanger.exchange(s);
                System.out.println("t2......threadName....."+Thread.currentThread().getName()+"--------value---------"+value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();


//        new Thread(()->{
//            String s = "T3";
//            try {
//                String value = exchanger.exchange(s);
//                System.out.println("t3......threadName....."+Thread.currentThread().getName()+"--------value---------"+value);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"T3").start();
    }
}