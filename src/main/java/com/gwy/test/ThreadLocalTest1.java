//package com.gwy.test;
//
//public class ThreadLocalTest1 {
//
//    private static final ThreadLocal<String> threadLocal=new ThreadLocal<String>(){
//        @Override
//        protected String initialValue() {
//            return "我是默认值";
//        }
//    };
//
//    public static void main(String[] args){
//        String hello = "hello";
//        threadLocal.set(hello);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                threadLocal.set("你好!");
//                System.out.println("子线程 ："+threadLocal.get());
//            }
//        }).start();
//
//        System.out.println("主线程 ："+threadLocal.get());
//
//    }
//}