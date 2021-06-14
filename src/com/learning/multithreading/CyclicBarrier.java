package com.learning.multithreading;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier {
    public static void main(String[] args) {
        // the number of threads in the executor service should be equal or more than the parties in cyclic barrier
        // if they are less, they will keep on waiting for the barrier count to become 0 but it never will
        // because there aren't enough threads. this means that the code after the await() method will never be executed
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All tasks are finished...");
            }
        });

        for (int i=0;i<5;i++) {
            executorService.execute(new BarrierWorker(i+1, cyclicBarrier));
        }

        executorService.shutdown();
    }
}

class BarrierWorker implements Runnable {
    private int id;
    private Random random;
    private java.util.concurrent.CyclicBarrier cyclicBarrier;

    public BarrierWorker(int id, java.util.concurrent.CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with id: "+this.id+" working now...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with id: "+this.id+" finished working...");
        try {
            cyclicBarrier.await();
            System.out.println("After await call");
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return String.valueOf(this.id);
    }
}