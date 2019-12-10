package com.gwy.concurrent.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccessingMap{
    private static final Logger logger = LoggerFactory.getLogger(AccessingMap.class);

    private static void useMap(final Map<String,Integer> scores){
        scores.put("Fred", 10);
        scores.put("Sara", 12);

        try{
            for(final String key : scores.keySet()){
                logger.info(key+"-----------score------------"+scores.get(key));
                scores.put("Joe", 14);
            }
        }catch (Exception ex){
            logger.error("---------Failed:"+ex);
        }
        logger.info("------Number of elements in the map:"+scores.keySet().size());
    }


    public static void main(String[] args) {
        System.out.println("Using plain vanilla HashMap");
        useMap(new HashMap<String,Integer>());

        System.out.println("Using Synchronized HashMap");
        useMap(Collections.synchronizedMap(new HashMap<String,Integer>()));

        System.out.println("Using Concurrent HashMap");
        useMap(new ConcurrentHashMap<String,Integer>());
    }
























}