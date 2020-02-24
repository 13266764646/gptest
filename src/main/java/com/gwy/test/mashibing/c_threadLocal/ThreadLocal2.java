package com.gwy.test.mashibing.c_threadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
     static  ThreadLocal<Person1> threadLocal = new ThreadLocal();

    public static void main(String[] args) {
      new Thread(()->{
          try {
              TimeUnit.SECONDS.sleep(2);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println(threadLocal.get());
      },"t1").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set(new Person1());
        },"t2").start();
    }


}

class Person1 {
    String name = "zhangsan";
}