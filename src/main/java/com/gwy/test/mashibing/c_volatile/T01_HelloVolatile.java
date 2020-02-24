package com.gwy.test.mashibing.c_volatile;

import java.util.concurrent.TimeUnit;

public class T01_HelloVolatile {

     boolean running  = true;

    void m(){
        System.out.println("m start");
        while (running){
//            System.out.println(Thread.currentThread().getName()+"----------------");
           /* synchronized (this){

            }*/
        }
        System.out.println("m end");
    }


    public static void main(String[] args) {
        T01_HelloVolatile t = new T01_HelloVolatile();
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}