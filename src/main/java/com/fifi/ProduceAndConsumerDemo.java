package com.fifi;

import com.sun.imageio.plugins.jpeg.JPEGImageWriterResources;

import javax.xml.transform.Source;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sun.jvm.hotspot.runtime.PerfMemory.start;

/**
 * ProduceAndConsumerDemo
 *
 * @author Alicia
 * @description
 * @date 2020/12/24
 */
public class ProduceAndConsumerDemo {
    public static void main(String[] args) {
        Storage storage = new Storage();
        new Thread(() -> {
            for (int i=0; i<10; i++){

            }
            storage.consumer();
        },"消费线程-2").start();
    }




    // 库存
    static class Storage{
        int source = 0;
        // 准备一把锁
        Lock lock = new ReentrantLock();
        // 获取一个Condition对象
        Condition condition = lock.newCondition();

        // 生产者
        public void producer() throws InterruptedException {
            //ondition.await
            lock.lock(); // 加锁
            // 有库存（还没有消费）就等待
            condition.await();

            System.out.println(Thread.currentThread().getName() + "生产一个产品");
        }

        // 消费者
        public void consumer(){

        }
    }
}
