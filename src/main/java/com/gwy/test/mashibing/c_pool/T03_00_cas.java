package com.gwy.test.mashibing.c_pool;

public class T03_00_cas {
    enum  ReadyToRun{T1,T2}

    static ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] an = "123456789".toCharArray();
        char[] ai ="ABCDEFJHI".toCharArray();


        new Thread(()->{
            for(char n : an){
                while (r  != ReadyToRun.T1 ){}
                System.out.println(Thread.currentThread().getName()+"----------------"+n);
                r = ReadyToRun.T2;
            }
        },"t1").start();

        new Thread(()->{
            for(char n : ai){
                while (r  != ReadyToRun.T2 ){}
                System.out.println(Thread.currentThread().getName()+"----------------"+n);
                r = ReadyToRun.T1;
            }
        },"t2").start();
    }
}