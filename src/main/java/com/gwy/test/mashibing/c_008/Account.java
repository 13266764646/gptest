package com.gwy.test.mashibing.c_008;


import java.util.concurrent.TimeUnit;

public class Account {
    private  String name;

    private String balance;

    public synchronized  void set(String name,String balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public  synchronized String getBalance(String name) {
        return this.balance;
    }


    public static void main(String[] args) {
        Account account = new Account();


        new Thread(()->{
            account.set("zhangsan", "100");
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));
    }
}