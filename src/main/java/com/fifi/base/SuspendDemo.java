package com.fifi.base;

/**
 * SuspendDemo
 *
 * @author Alicia
 * @description
 * @date 2021/7/26
 */
public class SuspendDemo {
    public static void main(String[] args) throws Exception{
        Thread t = new SuspendThreadDemo();
        t.start();
    }
}

class SuspendThreadDemo extends Thread{
    private long i = 0;
}
