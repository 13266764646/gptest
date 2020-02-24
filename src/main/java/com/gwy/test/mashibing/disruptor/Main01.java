package com.gwy.test.mashibing.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

public class Main01 {

    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer  = disruptor.getRingBuffer();

        long sequence = ringBuffer.next();

        try {
            LongEvent event = ringBuffer.get(sequence);

            event.setValue(8888L);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}