package com.gwy.test.mashibing.c_20;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T13_TestLockSupport {

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            for(int i =0;i < 10;i++){
                System.out.println("i-------------"+i);
                if(i == 5){
                    LockSupport.park();
                }
                if(i == 8){
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        LockSupport.unpark(t1);

    }
}