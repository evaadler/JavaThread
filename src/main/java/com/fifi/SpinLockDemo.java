package com.fifi;

import sun.jvmstat.perfdata.monitor.PerfStringMonitor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * SpinLockDemo 自旋锁
 *
 * @author Alicia
 * @description
 * @date 2021/1/8
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock(){

        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in O(∩_∩)O哈哈~");

        while (!atomicReference.compareAndSet(null, thread)){

        }

    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t invoke lock");
        atomicReference.compareAndSet(thread, null); // 解锁
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1); //保证AA线程肯定先启动
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnlock();
        }, "BB").start();

    }
}
