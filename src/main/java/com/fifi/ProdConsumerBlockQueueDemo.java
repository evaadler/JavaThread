package com.fifi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;

/**
 * ProdConsumerBlockQueueDemo
 *
 * @author Alicia
 * @description
 * @date 2021/1/10
 */
public class ProdConsumerBlockQueueDemo {



    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            try {
                myResource.myProducer();
                System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            try {
                myResource.myConsumer();
                System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consume").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("5s时间到，main线程停止，活动结束");
        System.out.println();
        System.out.println();
        myResource.stop();
    }
}

class MyResource{
    private BlockingQueue<String> blockingQueue = null;
    private boolean Flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public MyResource(BlockingQueue<String> queue){
        blockingQueue = queue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProducer() throws Exception{

        String temp = "";
        boolean returnValue = false;
        while (Flag){
            temp = atomicInteger.incrementAndGet() + "";
            returnValue = blockingQueue.offer(temp, 2L, TimeUnit.SECONDS);

            if (returnValue){
                System.out.println(Thread.currentThread().getName()+"插入队列"+temp+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"插入队列"+temp+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "fifi说停止了，表示Flag=false,生产结束");

    }

    public void myConsumer()throws Exception{
        System.out.println(Thread.currentThread().getName()+"消费线程启动");
        //atomicInteger.accumulateAndGet(, )；
        String result = "";
        while(Flag){
            // 2s等不到就不取了
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if (null == result || result.equalsIgnoreCase("")){
                Flag = false;
                System.out.println(Thread.currentThread().getName() + "消费退出");
                return;
            }

            System.out.println(Thread.currentThread().getName()+"消费队列"+result+"成功");
        }
    }

    public void stop() {
        Flag = false;
    }

}