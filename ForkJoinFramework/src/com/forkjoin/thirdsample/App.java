package com.forkjoin.thirdsample;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static Integer THRESHOLD = 0;

    public static void main (String[] args) {
        int[] nums = initializeNums();
        THRESHOLD = nums.length / Runtime.getRuntime().availableProcessors();
        SequentialMaxFind sequentialMaxFind = new SequentialMaxFind();
        long startTime = System.currentTimeMillis();
        System.out.println("Max: " + sequentialMaxFind.sequentialMaxFind(nums, nums.length));
        System.out.println("Time taken by sequential: " + (System.currentTimeMillis() - startTime));

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxFind parallel = new ParallelMaxFind(nums, 0, nums.length);
        startTime = System.currentTimeMillis();
        System.out.println("Max: " + pool.invoke(parallel));
        System.out.println("Time taken by parallel: " + (System.currentTimeMillis() - startTime));
    }

    private static int[] initializeNums () {
        Random random = new Random();
        int[] nums = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            nums[i] = random.nextInt(1000000000);
        }
        return nums;
    }
}
