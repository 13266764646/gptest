package com.gwy.test.mashibing.c_018;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T02_AtomicVsSyncVsLongAddr {
    static  Long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();


    public static void main(String[] args) {
        Thread[] threads = new Thread[1000];


        for(int i =0;i < threads.length;i++){
            threads[i] = new Thread(() -> {
                for(int k = 0;k < 10000;k++){
                    count1.incrementAndGet();
                }
            },"thread"+i);
        }

        long start = System.currentTimeMillis();

        for(Thread thread : threads){thread.start();}

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        long end = System.currentTimeMillis();

        System.out.println("count1--------------"+count1+"-------------cost---------"+(end-start));

        //--------------------------------------------------------------------
        final  Object o = new Object();

        for(int i = 0;i < threads.length; i++){
            threads[i] = new Thread(()->{
                for(int k = 0;k < 10000;k++){
                    synchronized (o){
                        count2++;
                    }
                }
            },"thread"+i);
        }

        long start1 = System.currentTimeMillis();

        for(Thread thread : threads){thread.start();}

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        long end1 = System.currentTimeMillis();

        System.out.println("count2--------------"+count2+"-------------cost---------"+(end1-start1));

        //----------------------------------------------------------
        for(int i = 0;i < threads.length; i++){
            threads[i] = new Thread(()->{
                for(int k = 0;k < 10000;k++){
                   count3.increment();
                }
            },"thread"+i);
        }

        long start3 = System.currentTimeMillis();

        for(Thread thread : threads){thread.start();}

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        long end3 = System.currentTimeMillis();

        System.out.println("count3--------------"+count3.longValue()+"-------------cost---------"+(end3-start3));



    }

}