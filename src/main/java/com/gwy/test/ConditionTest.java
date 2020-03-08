package com.gwy.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new ConditionWait(lock,condition)).start();
       try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            System.out.println("main"+e);
        }
        new Thread(new ConditionNotify(lock,condition)).start();

    }
}
