package com.github.veeshostak;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {

		int[] nums = initializeNums();
		
		// ==========
		// sequential
		SequentialMergesort mergesort = new SequentialMergesort(nums);

		System.out.println("sequential");
		long start = System.currentTimeMillis();
		mergesort.mergeSort(nums);
		System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
		
		// ==========
		// parallel
		
		MergeSortTask mergeSortRootTask = new MergeSortTask(nums);
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		
		System.out.println("parallel");
		start = System.currentTimeMillis();
		pool.invoke(mergeSortRootTask);
		System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
		
		
	}

	private static int[] initializeNums() {

		Random random = new Random();

		int[] nums = new int[10000000];

		for (int i = 0; i < 10000000; ++i)
			nums[i] = random.nextInt(100);

		return nums;
	}
}
