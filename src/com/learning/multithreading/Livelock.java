package com.learning.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock {
    public final Lock lock1 = new ReentrantLock(true);
    public final Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        Livelock livelock = new Livelock();
        new Thread(livelock::worker1, "thread1").start();
        new Thread(livelock::worker2, "thread2").start();

    }

    public void worker1() {
        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("worker1 has acquired lock1");
            System.out.println("worker1 trying to acquire lock2");
            if (lock2.tryLock()) {
                System.out.println("worker1 has acquired lock2");
                lock2.unlock();
                break;
            } else {
                System.out.println("worker1 could not acquire lock2");
                continue;
            }
        }
        lock1.unlock();
    }

    public void worker2() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("worker2 acquired lock2");
            System.out.println("worker2 trying to acquire lock1");
            if (lock1.tryLock()) {
                System.out.println("worker2 has acquired lock1");
                lock1.unlock();
                break;
            } else {
                System.out.println("worker2 could not acquire lock1");
                continue;
            }
        }
        lock2.unlock();
    }
}
