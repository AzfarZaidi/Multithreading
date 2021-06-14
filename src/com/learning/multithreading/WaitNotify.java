package com.learning.multithreading;

public class WaitNotify {
    public static void main(String[] args) {
        Process process = new Process();
        Thread t1 = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}

class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Inside the produce method");
            wait();
            System.out.println("Returned to the produce method");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Inside the consumer method");
            notify();
            Thread.sleep(5000);
        }
    }
}