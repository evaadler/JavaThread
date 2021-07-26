package com.fifi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueueDemo
 *
 * @author Alicia
 * @description
 * @date 2021/1/9
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception{
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        System.out.println();
        blockingQueue.put("x");
    }
}
