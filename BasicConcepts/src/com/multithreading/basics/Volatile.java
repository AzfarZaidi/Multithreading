package com.multithreading.basics;

public class Volatile {
    public static void main(String[] args) {
        WorkerNew worker = new WorkerNew();
        Thread t1 = new Thread(worker);
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.setTerminated(true);
        System.out.println("Worker thread is now terminated");
    }
}

class WorkerNew implements Runnable {
    // this means that the terminated variable will not be fetched from cache. It will always be fetched from RAM.
    private volatile boolean terminated;

    @Override
    public void run() {
        while (!terminated) {
            System.out.println("Worker is not terminated...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}