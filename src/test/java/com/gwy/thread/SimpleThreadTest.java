package com.gwy.thread;

public class SimpleThreadTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10;i++){
            Thread thread = new Thread("第几个线程"+i){
                public void run(){
                    System.out.println("Thread:"+getName());
                }
            };

            thread.start();
        }
    }
}