package com.gwy.test.mashibing.c_202;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {
    private LinkedList<T> lists = new LinkedList<>();

    private final Integer MAX = 2;
    volatile int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition product = lock.newCondition();
    private Condition consumer = lock.newCondition();

    private void put(T t){
        try {
            lock.lock();
            while(lists.size() == MAX){
                product.await();
            }
            lists.add(t);
            count++;
            consumer.signalAll();
            System.out.println("----------put method-----------name-"+Thread.currentThread().getName()+"------------------count-"+count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    private T get(){
        T t = null;
        try {
            lock.lock();
            while(lists.size() == 0){
                consumer.await();
            }
            lists.removeFirst();
            count--;
            product.signalAll();
            System.out.println("----------get method-----------name-"+Thread.currentThread().getName()+"------------**********------count-"+count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return t;
    }


    public static void main(String[] args) {
        MyContainer2 container2 = new MyContainer2();
        //消费端
        for(int i = 0;i < 8;i++){
            new Thread(() ->{
                for(int j = 0;j < 5;j++){
                    container2.get();
                }
            },"consumer"+i).start();
        }


        for(int i = 0 ; i < 2 ;i++){
            new Thread(() ->{
                for(int j = 0 ; j < 20;j++){
                    container2.put(Thread.currentThread().getName()+"    "+j);
                }
            },"producter"+i).start();
        }
    }
}