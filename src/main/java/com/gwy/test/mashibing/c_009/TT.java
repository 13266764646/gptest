package com.gwy.test.mashibing.c_009;

public class TT extends T {

    synchronized  void mm(){
        System.out.println("mm start()");
        super.m();
        System.out.println("mm end()");
    }
}

