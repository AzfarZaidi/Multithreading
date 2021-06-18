package com.forkjoin.secondsample;

import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main (String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SimpleRecursiveTask task = new SimpleRecursiveTask(220);
        System.out.println(task.invoke());
    }
}
