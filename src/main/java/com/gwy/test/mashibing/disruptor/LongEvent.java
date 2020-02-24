package com.gwy.test.mashibing.disruptor;

public class LongEvent {

    private Long value;

    public  void  setValue(Long value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{value="+value+"}";
    }
}