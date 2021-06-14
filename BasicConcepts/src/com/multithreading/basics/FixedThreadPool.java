package com.multithreading.basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i=0;i<100;i++) {
            executorService.execute(new WorkerThread(i));
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {}
                // executorService.shutdownNow();
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}

class WorkerThread implements Runnable {
    public int id;

    public WorkerThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Work with id: " + id + " is executing. Thread: " + Thread.currentThread().getName());
        try {
            long duration = (long) (Math.random() * 5);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}