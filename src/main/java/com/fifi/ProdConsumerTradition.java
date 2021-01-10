package com.fifi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ProdConsumerTradition
 *
 * @author Alicia
 * @description
 * @date 2021/1/10
 */
public class ProdConsumerTradition {


    public static void main(String[] args) {
        ShareData shareData = new ShareData();
// 启动一个生产者线程
        new Thread(()->{

            for(int i=1; i<=5; i++){
                shareData.consumer();
            }
        }, "消费").start();

        // 启动一个消费者线程
        new Thread(()->{
            for (int i=1; i<=5; i++){
                shareData.producer();
            }

        },"生产").start();

        new Thread(()->{

            for(int i=1; i<=5; i++){
                shareData.consumer();
            }
        }, "消费2").start();

        // 启动一个消费者线程
        new Thread(()->{
            for (int i=1; i<=5; i++){
                shareData.producer();
            }

        },"生产2").start();




    }
}

class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() {
        lock.lock();
        try {
            while (number != 0) {
                // 等待，不能生产
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void consumer(){
        lock.lock();
        try {

            while (number == 0){
                // 等待，不能消费
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
