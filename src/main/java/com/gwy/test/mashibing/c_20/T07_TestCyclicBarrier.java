package com.gwy.test.mashibing.c_20;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T07_TestCyclicBarrier {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(20,()->{
            System.out.println("满人，发车");
        });

        for(int i =0;i < 1000;i++){
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }


}