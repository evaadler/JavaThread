package com.fifi;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * SemaphoreDemo 信号量： 抢车位
 *
 * @author Alicia
 * @description
 * @date 2021/1/9
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        // 资源：抢3个车位
        Semaphore semaphore = new Semaphore(3);

        for (int i=1; i<=6; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
