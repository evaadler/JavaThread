package com.fifi.juc;

import java.io.PrintStream;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ThreadOrderAccess 线程---线程操作资源类
 * 多线程之间按顺序调用，实现A-》B-》C
 * 三个线程启动，要求如下：
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。。。来10轮
 *
 * 1. 高聚低合前提下，线程操作资源类
 * 2。 判断/干活/通知
 * 3。 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 * 4。 标志位
 *
 * @author Alicia
 * @description
 * @date 2020/7/5
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource sr = new ShareResource();


        new Thread(()-> {
            // 来10轮
            for (int i=0; i<10; i++){
                sr.printAA();
            }
        }, "AA").start();
        new Thread(()-> {
            // 来10轮
            for (int i=0; i<10; i++){
                sr.printBB();
            }
        }, "BB").start();
        new Thread(()-> {
            // 来10轮
            for (int i=0; i<10; i++){
                sr.printCC();
            }
        }, "CC").start();
    }
}

/**
 * // 判断
 // 干活
 // 通知
 */
class ShareResource{

    private int number = 1; // 1:A 2:B 3:C
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printAA(){
        lock.lock();
        try {
            // 1 判断
            while (number!=1){
                condition1.await();
            }
            // 2 干活
            for (int i=0; i<5; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            // 3 判断
            number = 2;
            condition2.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printBB(){
        lock.lock();
        try {
            // 1 判断
            while (number!=2){
                condition2.await();
            }
            // 2 干活
            for (int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            // 3 通知（唤醒）
            number = 3;
            condition3.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printCC(){
        lock.lock();
        try {
            // 1 判断
            while (number!=3){
                condition3.await();
            }
            // 2 干活
            for (int i=0; i<15; i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            // 3 唤醒
            number = 1;
            condition1.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
