package com.gwy.test.mashibing.c_004;

public class T {
    private static int count =10;

    public synchronized static void m(){//这里等同于synchronized(T.class)
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void mm(){
        synchronized (T.class){//
            count--;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
        }
    }

    public static void main(String[] args) {
      T t = new T();
        for(int i =0;i < 100;i++){
            new Thread(()->{
                t.mm();
                try {
                    Thread.sleep(1000L);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

