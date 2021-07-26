package com.fifi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MyThreadPoolDemo
 *
 * @author Alicia
 * @description
 * @date 2021/1/10
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求
            for (int i=1; i<=10; i++){
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
