package com.gwy.test.mashibing.c_collection.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SychronizedQueue {



    public static void main(String[] args) throws InterruptedException {
          BlockingQueue strs = new SynchronousQueue();

          new Thread(()->{
              try {
                  System.out.println(strs.take());
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }).start();

          strs.put("aaa");
          System.out.println(strs.size());
    }
}