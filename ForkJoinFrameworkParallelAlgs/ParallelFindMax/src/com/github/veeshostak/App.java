package com.github.veeshostak;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static int THREASHOLD = 0;
	
	public static void main(String[] args) {
		
		long[] nums = initializeNums();
		THREASHOLD =  nums.length / Runtime.getRuntime().availableProcessors();
		
		// =========================
		// sequential
		
		SequentialMaxFind seqMaxFind = new SequentialMaxFind();
		
		long start = System.currentTimeMillis();
		System.out.println("Sequential");
		System.out.println("Max: " + seqMaxFind.sequentialMaxFinding(nums, nums.length));
		System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");

		System.out.println();
		
		// =========================
		// parallel
		
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		MaximumFindTask parallelFindTask = new MaximumFindTask(nums, 0, nums.length);
		
		start = System.currentTimeMillis();
		System.out.println("Parallel");
		System.out.println("Max: " + pool.invoke(parallelFindTask));
		System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
	}

	
	
	private static long[] initializeNums() {
		
		Random random = new Random();
		
		long[] nums = new long[1000000];
		
		for(int i=0;i<1000000;++i)
			nums[i] = random.nextInt(100);
		
		return nums;
	}
}
