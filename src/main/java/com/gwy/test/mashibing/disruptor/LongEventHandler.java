package com.gwy.test.mashibing.disruptor;


import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

    public static long count =0;

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("["+Thread.currentThread().getName()+"]"+event+"序列号："+sequence);

    }
}