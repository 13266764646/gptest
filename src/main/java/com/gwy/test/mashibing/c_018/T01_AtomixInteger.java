package com.gwy.test.mashibing.c_018;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_AtomixInteger {

    AtomicInteger count = new AtomicInteger();


    void m(){
        for (int i = 0; i < 10000; i++){
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T01_AtomixInteger t = new T01_AtomixInteger();

        List<Thread> list = new ArrayList<>();

        for(int i =0; i < 10; i++){
            list.add(new Thread(t::m,"thread"+i));
        }


        for(Thread thread : list){
            thread.start();
        }

        list.forEach((o) ->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);

    }
}