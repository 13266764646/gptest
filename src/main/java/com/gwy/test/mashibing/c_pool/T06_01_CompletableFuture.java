package com.gwy.test.mashibing.c_pool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T06_01_CompletableFuture {


    public static void main(String[] args) {
        long start,end;

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() ->priceOfTM());

        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() ->priceOfTB());


        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() ->priceOfJD());


        CompletableFuture.allOf(futureTM,futureTB,futureJD).join();

        CompletableFuture.supplyAsync(()-> priceOfTM())
                .thenApply(String::valueOf)
                .thenApply(str -> "price"+str)
                .thenAccept(System.out::println);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static <U> Double priceOfJD() {
        delay();
        return 0.50;
    }

    private static <U> Double priceOfTB() {
        delay();
        return 2.00;
    }

    private static <U> Double priceOfTM() {
        delay();
        return 1.00;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MICROSECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n",time);
    }

}