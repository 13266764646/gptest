package com.gwy.test.mashibing.c_201;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class T08_Semphore {
    volatile List<Object> lists = new ArrayList<>();

    public void add(Object o){
        lists.add(o);
    }


    public int size(){
        return lists.size();
    }

    static Thread t1 = null,t2 = null;
    public static void main(String[] args) {
        T08_Semphore c = new T08_Semphore();
        Semaphore semaphore = new Semaphore(1);




        t1 =  new Thread(() ->{
            System.out.println("t1---------begin----");
            try {
                semaphore.acquire();
                for(int i =0;i< 5; i++){
                    c.lists.add(i);
                    System.out.println("add-------------"+i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                semaphore.acquire();
                for(int i =5;i< 10; i++){
                    c.lists.add(i);
                    System.out.println("add-------------"+i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
            System.out.println("t1---------end----");



        },"t1");


        t2 =  new Thread(()->{
            System.out.println("t2---------begin----");
            try {
                semaphore.acquire();
                System.out.println("t2---------end----");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

        },"t2");



        t1.start();
    }
}