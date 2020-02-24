package com.gwy.test.mashibing;

import java.util.concurrent.locks.LockSupport;

//LockSupport partk是阻塞的。
//uppack
/**
 * @Param
 * @return
 * @Date  2019/12/26 11:19
 * @Author guowenyao
 * @Description 字符和数字交替出现
 **/
public class LockSupportTest {

    static Thread t2 = null,t1 = null;


    public static void main(String[] args) {

        char[] cn = "123456789".toCharArray();
        char[] cc = "ABCDEFGHI".toCharArray();
//    LockSupport lockSupport = new LockSupport();
         t1 = new Thread(() ->{
            for(char a : cn){
                System.out.println("t1==="+a);
                LockSupport.unpark(t2);//叫醒t2
                LockSupport.park();//阻塞当前线程

            }
        },"t1");

        t2 = new Thread(() ->{
            for(char a : cc){
                LockSupport.park();//t2阻塞
                System.out.println("t2----"+a);
                LockSupport.unpark(t1);//叫醒t1
            }
        },"t2");
        t1.start();
        t2.start();
    }

}