package com.fifi;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * VolatileDemo
 * 1. 验证volatile的可见性
 *      1.1 假如int number=0；number变量开始没有添加volatile关键字，没有可见性
 *      1.2 添加volatile，可以解决可见性问题
 *
 * 2. 验证了volatile不保证原子性
 *      2.1 原子性指的是什么意思？
 *          不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割。需要整体完整
 *          要么同时成功，要么同时失败
 *      2.2 volatile不保证原子性的案例演示
 *
 *      2.4 如何解决原子性？
 *
 * @author Alicia
 * @description
 * @date 2020/7/4
 */
public class VolatileDemo {

    // main是一切方法的运行入口
    public static void main(String[] args) {
        //seeOkByVolatile();

        MyData myData = new MyData();


        for (int i=1; i<=20; i++){
            new Thread(() -> {

                for (int j=1; j<=1000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都全部计算完成后，再用mian线程取得最终结果值，看是多少？
        // 为什么>2，一是main线程，二是后台gc线程
        while (Thread.activeCount() > 2){
            Thread.yield();
        }


        System.out.println(Thread.currentThread().getName()+"\t finally number value "+myData.number);

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

    /**
     * 此时number前面已经加了volatile关键字修饰，不保证原子性
     */
    public synchronized void addPlusPlus(){
        number ++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }
}