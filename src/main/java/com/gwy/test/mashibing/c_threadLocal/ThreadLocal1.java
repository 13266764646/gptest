package com.gwy.test.mashibing.c_threadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
    volatile static  Person person = new Person();

    public static void main(String[] args) {
      new Thread(()->{
          try {
              TimeUnit.SECONDS.sleep(2);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println(person.name);
      },"t1").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.name ="lisi";
        },"t2").start();
    }


}

class Person {
    String name = "zhangsan";
}