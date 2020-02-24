package com.gwy.test.mashibing.c_collection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for(int i =0;i < 10000;i++){
            tickets.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        final  Object o = new Object();
        for(int i =0;i < 10;i++){
            new Thread(()->{
                while (true){
                    String s = tickets.poll();
                    if(s == null) break;
//                    synchronized (o) {
                       else System.out.println(Thread.currentThread().getName() + "-----销售了---------" + s);
//                    }
                }
            }).start();
        }
    }
}