package com.dining.philosophers;

import java.util.Random;

public class Philosopher implements Runnable {
    private int id;
    private volatile boolean full;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;

    public Philosopher (int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        random = new Random();
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public boolean isFull () {
        return full;
    }

    public void setFull (boolean full) {
        this.full = full;
    }

    public Chopstick getLeftChopstick () {
        return leftChopstick;
    }

    public void setLeftChopstick (Chopstick leftChopstick) {
        this.leftChopstick = leftChopstick;
    }

    public Chopstick getRightChopstick () {
        return rightChopstick;
    }

    public void setRightChopstick (Chopstick rightChopstick) {
        this.rightChopstick = rightChopstick;
    }

    public Random getRandom () {
        return random;
    }

    public void setRandom (Random random) {
        this.random = random;
    }

    public int getEatingCounter () {
        return eatingCounter;
    }

    public void setEatingCounter (int eatingCounter) {
        this.eatingCounter = eatingCounter;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run () {
        try {
            while (! isFull()) {
                think();
                if (leftChopstick.pickUp(this, State.LEFT)) {
                    // acquired left chopstick
                    if (rightChopstick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this, State.LEFT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void think () throws InterruptedException {
        System.out.println("Philosopher " + this.getId() + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat () throws InterruptedException {
        System.out.println(this + " is eating...");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    @Override
    public String toString () {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
