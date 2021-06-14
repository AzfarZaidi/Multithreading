package com.multithreading.basics;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProdConsLock {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(() -> {
            try {
                worker.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                worker.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();
        System.out.println("Current thread: " + Thread.currentThread().getName());
    }
}

class Worker {
    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Inside the producer method... Thread name: " + Thread.currentThread().getName());
        condition.await();
        System.out.println("Inside the producer method again... Thread name: " + Thread.currentThread().getName());
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println("Inside the consumer method... Thread name: " + Thread.currentThread().getName());
        condition.signal();
        lock.unlock();
    }
}