package com.fifi;

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
    }
}


@AllArgsConstructor
class User{

    String userName;
    int age;
}
