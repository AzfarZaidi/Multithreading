package com.multithreading.basics;

public class App {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runner2());
        t1.start();
        t2.start();
        try {
            // the join method is used to wait for the thread (on which it has been called) to die.
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished with threads...");
    }
}

class Runner1 implements Runnable {
    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner2 implements Runnable {
    @Override
    public void run() {
        for (int i=0;i<10;i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner2: " + i);
        }
    }
}
