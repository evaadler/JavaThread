package com.fifi;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReferenceDemo
 *
 * @author Alicia
 * @description
 * @date 2020/8/15
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User user1 = new User("lily", 22);
        User user2 = new User("makio", 15);

        // 相当于把user1变成了原子
        AtomicReference<User> ar = new AtomicReference<>();
        ar.set(user1);

        System.out.println(ar.compareAndSet(user1, user2) + "\t"+ar.get().toString());
        System.out.println(ar.compareAndSet(user1, user2) + "\t"+ar.get().toString());
    }
}

@ToString
@AllArgsConstructor
class User{

    String userName;
    int age;
}
