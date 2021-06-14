package com.multithreading.basics;

public class Synchronization {
    public static void main(String[] args) {
        process();
    }

    private static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++)
                    increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++)
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

        System.out.println("The final value of counter: "+ counter);
    }

    // synchronized keyword makes sure that only one thread accesses the counter at a time
    // so there is no inconsistency and the value is written properly
    public static synchronized void increment() {
        counter++;
    }

    public static int counter = 0;
}
