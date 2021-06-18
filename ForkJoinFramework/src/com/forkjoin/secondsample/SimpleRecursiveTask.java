package com.forkjoin.secondsample;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {
    private int simulatedWork;

    public SimpleRecursiveTask (int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    /**
     * The main computation performed by this task.
     * @return the result of the computation
     */
    @Override
    protected Integer compute () {
        if (simulatedWork > 100) {
            System.out.println("Task is too big. Need parallel execution... " + simulatedWork);
            SimpleRecursiveTask task1 = new SimpleRecursiveTask(simulatedWork / 2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(simulatedWork / 2);

            // adding the two tasks to the fork-join pool
            task1.fork();
            task2.fork();

            int solution = 0;
            solution += task1.join();
            solution += task2.join();

            return solution;
        } else {
            System.out.println("No need for parallel execution..." + simulatedWork);
            // this returned value will be added to the solution variable
            return 2 * simulatedWork;
        }
    }
}
