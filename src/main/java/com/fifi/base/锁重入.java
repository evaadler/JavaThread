package com.fifi.base;

import java.sql.DatabaseMetaData;

/**
 * 锁重入
 *
 * @author Alicia
 * @description
 * @date 2021/7/27
 */
public class 锁重入 {
    public static void main(String[] args) {
        Demo06Thread thread = new Demo06Thread();
        thread.start();
    }
}

class Demo06Service{
    synchronized public void foo1() throws Exception{
        System.out.println("foo1");
        Thread.sleep(1000);
        foo2();
    }

    synchronized public void foo2() throws Exception{
        System.out.println("foo2");
        Thread.sleep(2000);
        foo3();
    }

    synchronized public void foo3(){
        System.out.println("foo3");
    }
}

class 锁重入子类 extends Demo06Service{
    synchronized public void foo4() throws Exception{
        System.out.println("foo4");
        super.foo1();
    }
}

class Demo06Thread extends Thread{
    @Override
    public void run(){
        锁重入子类 service = new 锁重入子类();
        try {
            service.foo4();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
