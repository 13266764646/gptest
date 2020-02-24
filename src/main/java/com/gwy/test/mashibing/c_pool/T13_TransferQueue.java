package com.gwy.test.mashibing.c_pool;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T13_TransferQueue {

    public static void main(String[] args) {
        TransferQueue queue =  new LinkedTransferQueue<>();
        char[] an = "123456789".toCharArray();
        char[] aa = "abcdefjhi".toCharArray();


        new Thread(()->{
            for(char n : an){
                try {
                    queue.transfer(n);
                    System.out.println(Thread.currentThread().getName()+"-----------------"+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();


        new Thread(()->{
            for(char n : aa){
                try {
                    System.out.println(Thread.currentThread().getName()+"-----------------"+queue.take());
                    queue.transfer(n);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

    }
}