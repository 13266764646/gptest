package com.gwy.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionWait   implements  Runnable{

    Lock lock;

    Condition condition;

    public ConditionWait(Lock lock, Condition condition){
        this.lock = lock;
        this.condition = condition;
    }


    @Override
    public void run() {
        System.out.println("conditionWait begin");
        try{
            lock.lock();
            condition.await();
        }
        catch (Exception e){
            System.out.println("conditionWait"+e);
        }finally {
           lock.unlock();
        }
        System.out.println("conditionWait end");
    }
}
