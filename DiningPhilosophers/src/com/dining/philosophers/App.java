package com.dining.philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    /**
     * The aim of this simulation is to show that it is possible to avoid thread starvation
     *  - all threads will be executed using executor service
     *  - we are able to avoid deadlocks because we used tryLock()
     * @param args
     * @throws InterruptedException
     */
    public static void main (String[] args) throws InterruptedException {
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks = null;
        ExecutorService executorService = null;
        try {
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];
            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);

            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for (int i=0;i<Constants.NUMBER_OF_PHILOSOPHERS;i++) {
                philosophers[i].setFull(true);
            }
        } finally {
            executorService.shutdown();
            while (!executorService.isTerminated())
                Thread.sleep(1000);

            for(Philosopher philosopher : philosophers)
                System.out.println(philosopher + " eat# " + philosopher.getEatingCounter() + " times!");
        }
    }
}