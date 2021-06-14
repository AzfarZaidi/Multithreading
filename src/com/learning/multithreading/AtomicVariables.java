package com.learning.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariables {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(AtomicVariables::increment);
        Thread t2 = new Thread(AtomicVariables::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Value of counter is: " + counter);
    }

    public static void increment() {
        for (int i=0;i<10000;i++) {
            counter.getAndIncrement();
        }
    }
}
