package com.dining.philosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        lock = new ReentrantLock();
    }

    public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
        // this is where we will simulate deadlock
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher + " picked up " + state.toString() + " " + this);
            return true;
        }
        return false;
    }

    public void putDown (Philosopher philosopher, State right) {
        lock.unlock();
        System.out.println(philosopher + " put down " + this);
    }

    @Override
    public String toString () {
        return "Chopstick " + id;
    }
}
