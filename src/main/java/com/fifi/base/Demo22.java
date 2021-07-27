package com.fifi.base;

/**
 * Demo22
 *
 * @author Alicia
 * @description
 * @date 2021/7/26
 */
public class Demo22 {
    public static void main(String[] args) {
        Demo22User user = new Demo22User();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                user.updateUsernameAndPassword("username", "password");
            }
        };
        t1.setName("A");
        t1.start();
        t1.setDaemon(true);
        
        Thread t2 = new Thread(){
            @Override
            public void run() {
                user.printUsernameAndPassword();
            }
        };
        t2.start();
    }
}

class Demo22User{
    private String username = "a";
    private String password = "aa";

    public void updateUsernameAndPassword(String username, String password)
    {
        this.username = username;
        if ("A".equals(Thread.currentThread().getName())){
            System.out.println("停止A线程");
            Thread.currentThread().suspend();
        }
        this.password = password;
    }

    public void printUsernameAndPassword() {


    }
}
