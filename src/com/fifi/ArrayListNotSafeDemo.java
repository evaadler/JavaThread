package com.fifi;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayListNotSafeDemo
 *
 * 问题：java.util.ConcurrentModificationException
 *
 * 导致原因：
 * （写时复制-读写分离思想的一种）
 *
 * 解决方案：
 *  1. Vector
 *  2. Collections.synchronizedList(new ArrayList<>());
 *  3. CopyOnWriteArrayList
 *
 * 优化建议(同样的错误，不要出现第2次)：
 *
 * @author Alicia
 * @description
 * @date 2020/8/1
 */
public class ArrayListNotSafeDemo {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        for (int i=0; i<30; i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * TIPS：写时复制
 * CopyOnWrite 容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器 Object[] 添加，而是先
 * 将当前容器 Object[] 进行copy，复制出一个新的容器 Object[] newElements， 然后新的容器 Object[] newElements
 * 里添加元素，添加完元素之后，再将原容器的引用指向新的容器 setArray(newElements)；这样做的好处时可以
 * 对 CopyOnWrite 容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以 CopyOnWirte 容器
 * 也是一种读写分离的思想，读和写不同的容器
 */