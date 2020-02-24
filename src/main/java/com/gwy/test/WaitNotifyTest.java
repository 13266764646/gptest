package com.gwy.test;

public class WaitNotifyTest {
    static Thread t1 = null,t2 = null;

    public static void main(String[] args) {
         char[] cn = "123456789".toCharArray();
        char[] cc = "ABCDEFJHI".toCharArray();

        final  Object object = new Object();

        t1 = new Thread(()-> {
            synchronized (object){
                for(char a : cn){
                    System.out.println(Thread.currentThread()+"t1----"+a);
                    object.notify();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();//必须，否则无法退出程序，有一个线程在wait状态。
            }

        },"t1");


        t2 = new Thread(()-> {
            synchronized (object) {
                for (char a : cc) {
                    System.out.println("t2----" + a);
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        },"t2");

        t1.start();
        t2.start();
        while(true){

        }
    }


}