package com.gwy.test.mashibing.c_collection.queue;

import javafx.concurrent.Task;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T07_DelayQueue {
    static BlockingQueue tasks = new DelayQueue();

    static Random r = new Random();

    static class MyTask implements Delayed {

        String name;
        long runningTime;

        MyTask(String name,long runningTime){
            this.name = name;
            this.runningTime = runningTime;
        }


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MICROSECONDS) < o.getDelay(TimeUnit.MICROSECONDS)){
                return -1;
            }else if(this.getDelay(TimeUnit.MICROSECONDS) > o.getDelay(TimeUnit.MICROSECONDS)) {
                return  1;
            }
            return 0;
        }

        @Override
        public String toString(){
            return name +""+runningTime;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now+1000L);
        MyTask t2 = new MyTask("t2", now+2000L);
        MyTask t3 = new MyTask("t31", now+1500L);
        MyTask t4 = new MyTask("t4", now+2500L);
        MyTask t5 = new MyTask("t5", now+500L);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for(int i =0;i < 5;i++){
            System.out.println(tasks.take());
        }

    }
}