package com.forkjoin.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSortTask extends RecursiveAction {
    int[] nums;

    public ParallelMergeSortTask (int[] nums) {
        this.nums = nums;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute () {
        if (nums.length <= 10) {
            mergeSort(nums);
            return;
        }
        int middleIndex = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex, nums.length);

        ParallelMergeSortTask leftTask = new ParallelMergeSortTask(left);
        ParallelMergeSortTask rightTask = new ParallelMergeSortTask(right);

        invokeAll(leftTask, rightTask);
        merge(left, right, nums);
    }

    public void mergeSort (int[] nums) {
        if (nums.length <= 1)
            return;
        int middleIndex = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex, nums.length);

        mergeSort(left);
        mergeSort(right);
        merge(left, right, nums);
    }

    private void merge (int[] left, int[] right, int[] nums) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j])
                nums[k++] = left[i++];
            else
                nums[k++] = right[j++];
        }

        while (i < left.length)
            nums[k++] = left[i++];
        while (j < right.length)
            nums[k++] = right[j++];
    }
}
