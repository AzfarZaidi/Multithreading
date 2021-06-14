package com.multithreading.basics;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue {
    /**
     * A blocking queue is a synchronized implementation of queue. It can be used if we want a pub-sub model -
     * an event processor queue. As soon as an event is written onto the queue, another thread
     * can pick that event from the queue and give it to another component.
     * @param args
     */
    public static void main(String[] args) {

        java.util.concurrent.BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        FirstRunner firstRunner = new FirstRunner(blockingQueue);
        SecondRunner secondRunner = new SecondRunner(blockingQueue);
        new Thread(firstRunner).start();
        new Thread(secondRunner).start();
    }
}

class FirstRunner implements Runnable {
    java.util.concurrent.BlockingQueue<Integer> blockingQueue;

    public FirstRunner(java.util.concurrent.BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                blockingQueue.put(counter);
                System.out.println("Adding value to queue: " + counter);
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondRunner implements Runnable {
    java.util.concurrent.BlockingQueue<Integer> blockingQueue;

    public SecondRunner(java.util.concurrent.BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int counter = blockingQueue.take();
                System.out.println("Taking value from queue: " + counter);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
