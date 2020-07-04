package com.fifi;



import java.util.concurrent.ForkJoinTask;

/**
 * com.fifi.LambdaExampleDemo
 *
 * @author Alicia
 * @description
 * @date 2020/7/3
 */



public class LambdaExampleDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {for(int i=0; i<40; i++){ ticket.saleTicket();}}, "A").start();
        new Thread(() -> {for(int i=0; i<40; i++){ ticket.saleTicket();}}, "B").start();

        new Thread(() -> {for(int i=0; i<40; i++){ ticket.saleTicket();}}, "C").start();

    }
}


class Ticket{
    private int number = 40;


    public void saleTicket() {
    }
}
