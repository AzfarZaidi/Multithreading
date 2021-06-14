package com.multithreading.basics;

import java.util.concurrent.locks.Lock;

public class ReentrantLock {
    public static int counter = 0;
    public static Lock lock = new java.util.concurrent.locks.ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
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

    private static void increment() {
        lock.lock();
        try {
            for (int i=0;i<10000;i++)
                counter++;
        } finally {
            System.out.println("Inside finally to close lock");
            lock.unlock();
        }
    }
}
