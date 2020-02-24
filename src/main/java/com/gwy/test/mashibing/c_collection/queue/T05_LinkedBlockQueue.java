package com.gwy.test.mashibing.c_collection.queue;

import org.apache.commons.lang.StringUtils;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class T05_LinkedBlockQueue {

    static BlockingDeque<String> strs = new LinkedBlockingDeque<>();
    static Random r = new Random();

    public static void main(String[] args) {
         for(int i =0 ;i < 100;i++){
             strs.add("a"+i);
             try {
                 TimeUnit.MICROSECONDS.sleep(5000);

             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }

         for(int i =0;i < 5;i++){
             new Thread(()->{

                 try {
                     while (true){
                         String aa = strs.take();
                         if(StringUtils.isEmpty(aa))break;
                         System.out.println("threadName is " + Thread.currentThread().getName() + "------------" + aa);
                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }).start();
         }

//        try {
//            TimeUnit.SECONDS.sleep(10);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//System.out.println("---------------------------------------------------");
//        for(int i =0 ;i < 100;i++){
//            strs.add("b"+i);
//            try {
//                TimeUnit.MICROSECONDS.sleep(5000);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}