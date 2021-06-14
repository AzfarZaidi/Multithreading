package com.learning.multithreading;

public class Daemon {
    public static void main(String[] args) {
        Thread t1 = new Thread(new DaemonWorker());
        Thread t2 = new Thread(new NormalWorker());
        t1.setDaemon(true);
        t1.start();
        t2.start();
        /*
         * after 4 seconds, the NormalWorker will be finished. The JVM will
         * terminate the DaemonWorker also once all the non-daemon threads have been finished.
         */
    }
}

class DaemonWorker implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("DaemonWorker is running...");
        }
    }
}

class NormalWorker implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("NormalWorker finished execution...");
    }
}