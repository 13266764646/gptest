package com.gwy.test.mashibing.c_volatile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Mgr06 {
    private Logger logger = LoggerFactory.getLogger(Mgr06.class);
    private static volatile   Mgr06 INSTANCE = null;//分成三步：1.给指令申请内存 2.给成员变量初始化3.是把这块内存的内容赋值给INSTANCE


    public static Mgr06 getInstance(){

        if(INSTANCE == null){
            synchronized (Mgr06.class) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               if(INSTANCE == null){
                    INSTANCE = new Mgr06();
               }
            }
        }
        return  INSTANCE;
    }


    public static void main(String[] args) {
        for(int i =0;i< 100;i++){
            new Thread(() ->{
                System.out.println(  Mgr06.getInstance().hashCode() );
                }, "t1").start();
        }
    }
}