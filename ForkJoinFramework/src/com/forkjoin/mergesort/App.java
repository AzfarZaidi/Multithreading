package com.forkjoin.mergesort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main (String[] args) {
        int[] nums = initializeNums();
        SequentialMergeSort mergeSort = new SequentialMergeSort();
        long startTime = System.currentTimeMillis();
        mergeSort.mergeSort(nums);
        System.out.println("Time taken by sequential: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMergeSortTask parallel = new ParallelMergeSortTask(nums);
        pool.invoke(parallel);
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
