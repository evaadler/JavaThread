package com.fifi;

/**
 * BaseThread:
 * Main 方法被命名为 main 的线程调用，线程类的构造方法是被 main 线程调用，run 方法是被 Thread-0 的线程调用，
 * run 方法是自动被调用的方法。
 *
 * @author Alicia
 * @description
 * @date 2021/7/26
 */
public class BaseThread {


    public static void main(String[] args) {

    }

}
class DemoBaseThread extends Thread{
    public DemoBaseThread(){

    }

    @Override
    public void run() {
        super.run();
    }
}
