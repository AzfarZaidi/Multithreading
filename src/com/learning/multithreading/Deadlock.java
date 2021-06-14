package com.learning.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);
    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();
        new Thread(deadlock::worker1,"workerThread1").start();
        new Thread(deadlock::worker2,"workerThread2").start();
    }

    public void worker1() {
        lock1.lock();
        System.out.println("lock1 has been acquired by " + Thread.currentThread().getName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("lock2 has been acquired by " + Thread.currentThread().getName());
        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        lock2.lock();
        System.out.println("lock2 been acquired by " + Thread.currentThread().getName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.lock();
        System.out.println("lock1 has been acquired by " + Thread.currentThread().getName());
        lock1.unlock();
        lock2.unlock();
    }
}
