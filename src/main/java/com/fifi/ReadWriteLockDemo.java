package com.fifi;

import com.sun.javafx.image.impl.ByteIndexed;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ReadWriteLockDemo
 *
 * @author Alicia
 * @description
 * @date 2021/1/9
 */
public class ReadWriteLockDemo {





    public static void main(String[] args) {
        MyCache cache = new MyCache();
        for ( int i=1; i<=5; i++){
            final int tempInt = i;
            new Thread(() -> {

                cache.put(String.valueOf(tempInt), (Object) tempInt);
            },String.valueOf(i)).start();
        }

        for ( int i=1; i<=5; i++){
            final int tempInt = i;
            new Thread(() -> {

                cache.get( String.valueOf(tempInt));
            },String.valueOf(i)).start();
        }
    }
}

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();

    // 写方法
    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
        try {
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
    }

    // 读方法
    public void get(String key){
        System.out.println(Thread.currentThread().getName() + "\t 正在读取：" + key);
        try {
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成 " + result);
    }
}

