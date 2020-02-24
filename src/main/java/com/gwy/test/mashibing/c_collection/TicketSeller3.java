package com.gwy.test.mashibing.c_collection;

import java.util.LinkedList;
import java.util.Vector;

public class TicketSeller3 {
    static LinkedList<String> tickets = new LinkedList<>();

    static {
        for(int i =0;i < 10000;i++){
            tickets.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        final  Object o = new Object();
        for(int i =0;i < 10;i++){
            new Thread(()->{
                while (tickets.size() >0){
//                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "-----销售了---------" + tickets.remove(0));
//                    }
                }
            }).start();
        }
    }
}