package com.fifi;

import java.awt.image.VolatileImage;

/**
 * ThreadWaitNotifyDemo
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 实现交替，来10轮，变量初始值为零
 * 需求2 假设两个生产，两个消费
 *
 * 1） 高内聚低耦合前提下，线程操作资源类
 * 2） 判断/干活/通知
 * 3) 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 *
 * @author Alicia
 * @description
 * @date 2020/7/5
 */
public class ThreadWaitNotifyDemo {



    public static void main(String[] args) throws Exception{
        Data data = new Data();


            new Thread(() -> {
                for (int i=0; i<10; i++) {
                try {
                    data.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}
                // System.out.println(Thread.currentThread().getName()+": " +data.number);
            }, "AAA").start();

            new Thread(() -> {
                for (int i=0; i<10; i++) {
                    try {
                        data.sub();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // System.out.println(Thread.currentThread().getName()+": " + data.number);
            }, "BBB").start();


        new Thread(() -> {
            for (int i=0; i<10; i++) {
                try {
                    data.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}
            // System.out.println(Thread.currentThread().getName()+": " +data.number);
        }, "CCC").start();



        new Thread(() -> {
            for (int i=0; i<10; i++) {
                try {
                    data.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // System.out.println(Thread.currentThread().getName()+": " + data.number);
        }, "DDD").start();


        //System.out.println("End:"+data.getNumber());

        }


}

class Data{
    private int number = 0;
    public synchronized void add() throws InterruptedException {
        // 1. 判断
        while (number != 0) {
             this.wait();
        }

        // 2. 干活
        number ++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3. 通知
        this.notifyAll();

    }
    public synchronized void sub() throws InterruptedException {
        while (number == 0) {
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

