package com.multithreading.basics;

public class SyncBlocks {
    public static int counter1 = 0;
    public static int counter2 = 0;

    // the two lock objects below are used so that intrinsic lock of the class or object is not required.
    // if intrinsic lock is used, only one thread can execute any synchronized block. this means if there are
    // multiple synchronized blocks, then the application will be slower as only one of them can be executed at
    // a time. synchronization using the intrinsic lock would look like below -
    // synchronized (className.class) -- for static methods - applied at class's intrinsic lock
    // synchronized (this) -- for non-static methods - applied at object's intrinsic lock

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void increment1() {
        synchronized (lock1) {
            counter1++;
        }
    }
    public static void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100000;i++)
                    increment1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100000;i++)
                    increment2();
            }
        });

        System.out.println(System.currentTimeMillis());
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter1: "+counter1);
        System.out.println("Counter2: "+counter2);
    }

    public static void main(String[] args) {
        process();
    }
}
