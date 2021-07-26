package com.fifi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ContainerNotSafeDemo
 *
 * @author Alicia
 * @description
 * @date 2021/1/7
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List list  = new ArrayList();
        for (int i=1; i<=3; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
                }, String.valueOf(i)).start();        }
    }
}
