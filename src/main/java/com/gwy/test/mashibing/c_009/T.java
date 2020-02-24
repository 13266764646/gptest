package com.gwy.test.mashibing.c_009;

import java.util.concurrent.TimeUnit;

public class T {
    synchronized void m(){
        System.out.println("m start()");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m end");
    }

    public static void main(String[] args) {
        new TT().mm();
    }
}