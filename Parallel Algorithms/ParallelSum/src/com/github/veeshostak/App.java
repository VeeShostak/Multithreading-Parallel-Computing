package com.github.veeshostak;

import java.util.Random;

public class App {

	public static void main(String[] args) {
		
		Random random = new Random();
		
		int numOfProcessors = Runtime.getRuntime().availableProcessors();

		// generate numbers
	    int[] nums = new int[100000000];
	    for (int i = 0; i < nums.length; i++) {
	        nums[i] = random.nextInt(101) + 1; // 1 - 100
	    }

	    long start = System.currentTimeMillis();
	    SequentialSum sequentialSum = new SequentialSum();
	    System.out.println("Sum is: " + sequentialSum.sum(nums));
	    System.out.println("Sequential: " + (System.currentTimeMillis() - start)+"ms"); 

	    start = System.currentTimeMillis();
	    ParallelSum parallelSum = new ParallelSum(numOfProcessors);
	    System.out.println("Sum is: " +parallelSum.parallelSum(nums));
	    System.out.println("Parallel: " + (System.currentTimeMillis() - start)+ "ms"); 
		
	}
}
