package com.fifi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SyncAndReentrantLockDemo
 *
 * @author Alicia
 * @description
 * @date 2021/1/10
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        new Thread(()->{
            for (int i =1; i<=5; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            for (int i =1; i<=10; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
        }, "BB").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            for (int i =1; i<=15; i++){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

    }
}

class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();

    public void print5(){
        lock.lock();
        try {
            while (number != 1){

            }
            for (int i=1; i<=5; i++){
                System.out.println(number);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
