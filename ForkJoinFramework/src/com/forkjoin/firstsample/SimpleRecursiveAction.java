package com.forkjoin.firstsample;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {
    private int simulatedWork;

    public SimpleRecursiveAction (int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }


    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute () {
        if (simulatedWork > 100) {
            System.out.println("Parallel execution is required, splitting... " + simulatedWork);
            SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(simulatedWork / 2);

            simpleRecursiveAction1.fork();
            simpleRecursiveAction2.fork();
        } else {
            System.out.println("No need for parallel execution. Sequential is fine... " + simulatedWork);
        }
    }
}
