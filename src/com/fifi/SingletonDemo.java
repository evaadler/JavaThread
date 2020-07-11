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

    private static SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法 ");
    }

    public static SingletonDemo getInstance(){
        if (instance == null) {
            instance =  new SingletonDemo();
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
