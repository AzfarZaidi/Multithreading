package com.multithreading.basics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatch {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // latches are used to make a thread wait till some other tasks have been done by other threads
        CountDownLatch latch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            executorService.execute(new LatchWorker(i + 1, latch));
        }

        try {
            // this will make the main thread wait until the value of countDownLatch is 0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

class LatchWorker implements Runnable {
    private int id;
    private CountDownLatch countDownLatch;

    public LatchWorker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        // this will decrement the latch value by 1
        countDownLatch.countDown();
        System.out.println("Current latch value: " + countDownLatch.getCount());
    }

    public void doWork() {
        System.out.println("Currently doing latch work. Thread name: " + Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}