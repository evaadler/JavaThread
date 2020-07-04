package com.fifi;

import jdk.nashorn.internal.ir.WhileNode;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * VolatileDemo
 * 1. 验证volatile的可见性
 *      1。1 假如int number=0；number变量开始没有添加volatile关键字，没有可见性
 *
 * @author Alicia
 * @description
 * @date 2020/7/4
 */
public class VolatileDemo {

    // main是一切方法的运行入口
    public static void main(String[] args) {
        seeOkByVolatile();

    }

    /**
     * volatile 可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
     */
    private static void seeOkByVolatile() {
        MyData myData = new MyData(); // 资源类

        new Thread(() -> {

            System.out.println(Thread.currentThread().getName()+"\t come in");
            //Thread.sleep();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();

            System.out.println(Thread.currentThread().getName()+"\t updated number value: "+myData.number);
        }, "AAA").start();

        // 第2个线程就是我们的main线程
        while (myData.number == 0) {
            // main线程就一直在这里等待循环，直到number值不在等于0为止
        }

        System.out.println(Thread.currentThread().getName() + "\t missison is over");
    }


}

class MyData{
    volatile int number = 0;

    public void addTo60(){
        number = 60;
    }


}