package com.fifi;

/**
 * ReSortSeqDemo 指令重排 Demo
 *
 * @author Alicia
 * @description
 * @date 2020/7/10
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;
    }

    public void method02() {
        if (flag){
            a = a + 5;
            System.out.println("*****retValue: " + a);
        }
    }
}
