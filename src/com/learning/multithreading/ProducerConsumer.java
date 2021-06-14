package com.learning.multithreading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}

class Processor {
    public final Object lock = new Object();
    public static final int UPPER_LIMIT = 5;
    public static final int LOWER_LIMIT = 0;
    public int value = 0;
    List<Integer> list = new ArrayList<>();

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while(true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing elements");
                    lock.wait();
                } else {
                    System.out.println("Adding: " + value);
                    list.add(value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while(true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding elements");
                    value = 0;
                    lock.wait();
                } else {
                    System.out.println("Removing: " + list.remove(list.size()-1));
                    lock.notify();
                }
            }
        }
    }
}
