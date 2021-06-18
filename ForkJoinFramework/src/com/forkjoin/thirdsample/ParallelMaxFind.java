package com.forkjoin.thirdsample;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFind extends RecursiveTask<Integer> {
    int[] nums;
    int lowIndex;
    int highIndex;

    public ParallelMaxFind (int[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    /**
     * The main computation performed by this task.
     * @return the result of the computation
     */
    @Override
    protected Integer compute () {
        if (highIndex - lowIndex < App.THRESHOLD) {
            return sequentialMaxFind();
        } else {
            int middleIndex = lowIndex + (highIndex - lowIndex) / 2;
            ParallelMaxFind task1 = new ParallelMaxFind(nums, lowIndex, middleIndex);
            ParallelMaxFind task2 = new ParallelMaxFind(nums, middleIndex, highIndex);
            // below statement adds all tasks to the fork-join pool
            invokeAll(task1, task2);

            return Math.max(task1.join(), task2.join());
        }
    }

    private Integer sequentialMaxFind () {
        int max = nums[lowIndex];
        for (int i = lowIndex + 1; i < highIndex; i++) {
            if (nums[i] > max)
                max = nums[i];
        }
        return max;
    }
}
