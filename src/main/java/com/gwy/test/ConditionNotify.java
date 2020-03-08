package com.gwy.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionNotify implements Runnable{
    Lock lock;

    Condition condition;

    public ConditionNotify(Lock lock, Condition condition){
        this.lock = lock;
        this.condition = condition;
    }


    @Override
    public void run() {
        System.out.println("ConditionNotify begin");
        try{
            lock.lock();
            condition.signal();
        }
        catch (Exception e){
            System.out.println("ConditionNotify"+e);
        }finally {
            lock.unlock();
        }
        System.out.println("ConditionNotify end");
    }
}
