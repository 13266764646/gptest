package com.gwy.test.mashibing.c_013;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T {

    volatile  int  count= 0;

      void m(){
        for(int i = 0; i < 10000;i++){
            count++;
            System.out.println("count------------"+count);
        }
    }

//    public static void main(String[] args) {
//        T t = new T();
//        List<Thread> threads = new ArrayList<>();
//        threads.add(new Thread());
//
//        for(int i =0;i <10 ;i++){
////            new Thread(()->{
////                try {
////                    TimeUnit.SECONDS.sleep(10);
////                    t.m();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            },"t"+i).start();
//            new Thread(() ->{
//                try {
//                    t.m();
//                   TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//            System.out.println(t.count);
//        }
//    }

    public static void main(String[] args) {
        String goodsInfoName ="";
        System.out.println(StringUtils.isNotEmpty(goodsInfoName) && goodsInfoName.length() > 40?goodsInfoName.substring(0, 40):goodsInfoName);//"商城礼品订单");
    }
}