package com.gwy.test.mashibing.c_202;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {
    private LinkedList<T> lists = new LinkedList<>();

    private final Integer MAX = 2;

    public synchronized void put(T t){
        while(lists.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        System.out.println("--------put---method----size---"+getAcount()+"********ThredName********"+Thread.currentThread().getName()+"*********content*******"+t);
        this.notifyAll();
    }

    public synchronized T get(){
        T t = null;
        while(lists.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--------get---method----size---"+getAcount()+"-------threadName-------"+Thread.currentThread().getName()+"------content-------"+lists.get(0));
        t = lists.removeFirst();
        this.notifyAll();
        return t;
    }

    public synchronized int getAcount(){
        return lists.size();
    }


    public static void main(String[] args) {
        MyContainer1 container1 = new MyContainer1();



        for(int i =0;i < 10;i++){
            new Thread(()->{
                for(int j = 0;j< 5;j++){
                    System.out.println(container1.get());
                }
            },"consumer"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 2; i++){
            new Thread(()->{
                for(int j =0;j< 25;j++){
                    container1.put(Thread.currentThread().getName() +"       "+j);
                }
            },"producter "+i).start();
        }
    }
}