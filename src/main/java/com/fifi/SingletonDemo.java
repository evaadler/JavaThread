package com.fifi;

import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 * SingletonDemo
 * 单机版下的单利模式
 *
 * @author Alicia
 * @description
 * @date 2020/7/11
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法 ");
    }

    // DCL (Double Check Lock 双端检索机制)

    /**
     * 不关闭整个厕所（形象）
     * @return
     */
    public static SingletonDemo getInstance(){
        // 先看这个坑位有没有人
        if (instance == null) {
            // 如果没有人就进去上把锁
            synchronized (SingletonDemo.class) {
                // 进去之后不放心，再推推门看看是否锁住了
                if (instance == null) {
                    // 锁住了就干活
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        System.out.println("--------------------------");
        // 如果是多线程呢？
        for (int i = 1; i<=10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }).start();
        }
    }

}
