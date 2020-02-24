package com.gwy.test.mashibing.c_reference;

public class M {

    @Override
    protected  void finalize() throws  Throwable{
        System.out.println("finalize");
    }
}