package com.fifi;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReetrantLockDemo
 *
 * @author Alicia
 * @description
 * @date 2021/1/8
 */
public class ReetrantLockDemo {


    public static void main(String[] args) {
        PhoneClass phone = new PhoneClass();

        new Thread(() -> {
            new PhoneClass().sendEmail();
        }, "t1").start();

        new Thread(() -> {
            new PhoneClass().sendEmail();
        }, "t2").start();



        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        // new Thread(phone, "t3").start();
       // new Thread(phone, "t4").start();
        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();
    }



}

class PhoneClass implements Runnable{
    public synchronized void sendEms(){
        System.out.println(Thread.currentThread().getName() + "\t sendEms");
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + "\t sendEmail");
        sendEms();
    }

    @Override
    public void run() {
        get();
    }

    Lock lock = new ReentrantLock();

    public void get(){
        lock.lock();
        try{

            System.out.println(Thread.currentThread().getName() + "\t get");
            set();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try{

            System.out.println(Thread.currentThread().getName() + "\t set");
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }
}
