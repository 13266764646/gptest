package com.gwy.test.mashibing.c_pool;

import javafx.concurrent.Task;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T05_00_HelloThreadPool {
    static  class MyTask implements  Runnable{
        private  int i;

        public MyTask(int i){
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"-------------"+i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Tssk{i="+i+"}";
        }
    }


    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());

        for(int i =0;i< 8;i++){
            pool.submit(new MyTask(i) {
            });
        }

        System.out.println("--------------123-----------"+pool.getQueue());

        pool.execute(new MyTask(100));

        System.out.println("--------------456-----------"+pool.getQueue());

        pool.shutdown();
    }
}