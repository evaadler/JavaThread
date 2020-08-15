package com.fifi;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CASDemo
 * 1. CAS 是什么？==》 compareAndSwap  比较并交换
 *
 *
 * @author Alicia
 * @description
 * @date 2020/7/11
 */
public class CASDemo {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current data: "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t current data: "+atomicInteger.get());


    }
}