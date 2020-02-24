package com.gwy.test.mashibing.c_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PS {

    static List<Integer> nums = new ArrayList<Integer>();

    static{
        Random r = new Random();
        for(int i =0;i< 10000;i++){
            nums.add(1000000+r.nextInt(100000));
        }
    }

    static  void foreach(){
        nums.forEach(v -> isPrime(v));
    }

    static  void parallel(){
        nums.parallelStream().forEach(PS::isPrime);
    }

    private static boolean isPrime(Integer num) {
        for(int i =2;i < num/2;i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}