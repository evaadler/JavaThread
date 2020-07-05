package com.fifi;

import java.awt.image.VolatileImage;

/**
 * ThreadWaitNotifyDemo
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 实现交替，来10轮，变量初始值为零
 *
 * 1） 高内聚低耦合前提下，线程操作资源类
 * 2） 判断/干活/通知
 *
 * @author Alicia
 * @description
 * @date 2020/7/5
 */
public class ThreadWaitNotifyDemo {



    public static void main(String[] args) throws Exception{
        Data data = new Data();

        for (int i=0; i<10; i++) {
            new Thread(() -> {
                try {
                    data.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // System.out.println(Thread.currentThread().getName()+": " +data.number);
            }, "AAA").start();

            new Thread(() -> {
                try {
                    data.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // System.out.println(Thread.currentThread().getName()+": " + data.number);
            }, "BBB").start();
        }

        System.out.println("End:"+data.getNumber());

    }



}

class Data{
    private int number = 0;
    public synchronized void add() throws InterruptedException {
        // 1. 判断
        if (number != 0) {
             this.wait();
        }

        // 2. 干活
        number ++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3. 通知
        this.notifyAll();

    }
    public synchronized void sub() throws InterruptedException {
        if (number == 0) {
            this.wait();
        }
        number --;

        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3. 通知
        this.notifyAll();
    }

    public int getNumber() {
        return number;
    }
}

