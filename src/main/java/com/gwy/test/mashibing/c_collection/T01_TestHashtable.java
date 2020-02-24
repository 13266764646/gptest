package com.gwy.test.mashibing.c_collection;

import java.util.Hashtable;
import java.util.UUID;

public class T01_TestHashtable {


    static Hashtable<UUID, UUID> m = new Hashtable<>();

    static int count = Constants.COUNT;

    static UUID[] keys = new UUID[Constants.COUNT];

    static UUID[] values = new UUID[Constants.COUNT];

    static  final int THEAD_COUNT = Constants.THEAD_COUNT;

    static {
        for(int i =0;i< count;i++){
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static  class MyThread extends Thread{
        int start;
        int gap = count/THEAD_COUNT;

        public MyThread(int start){
            this.start = start;
        }

        @Override
        public void run(){
            for (int i = start;i<start+gap;i++){
                m.put(keys[i], values[i]);
            }
        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THEAD_COUNT];

        for(int i =0;i< threads.length;i++){
            threads[i] = new MyThread(i*(count/THEAD_COUNT));
        }

        for(Thread t : threads){
            t.start();
        }

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);


        //---------------------------
        start = System.currentTimeMillis();
        for(int i = 0;i < threads.length;i++){
            threads[i] = new Thread(()->{
                for(int j=0;j<1000000;j++){
                    m.get(keys[10]);
                }
            });
        }


        for(Thread t : threads){
            t.start();
        }

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

         end = System.currentTimeMillis();
        System.out.println("-------------"+(end-start));
    }

}