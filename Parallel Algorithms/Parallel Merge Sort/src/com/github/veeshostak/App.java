package com.github.veeshostak;

import java.util.Random;

// in this case
// if size of array is small, under 10mill, have to use seq merge sort, as it is faster as there is parallel slowdown

public class App {

	public static Random random = new Random();
	
	public static void main(String[] args) throws Throwable {
		
		int numOfThreads  = Runtime.getRuntime().availableProcessors();
		
		
		// compare parallel mergeSort vs regular merge sort
		

		System.out.println("Number of threads/cores: " + numOfThreads);
		System.out.println("");
		
		int[] numbers = createRandomArray();
		ParallelMergeSort mergeSort = new ParallelMergeSort(numbers);


		long startTime1 = System.currentTimeMillis();
		mergeSort.parallelMergeSort(0, numbers.length - 1, numOfThreads);
		
		//mergeSort.showResult();
		long endTime1 = System.currentTimeMillis();

		System.out.println("");
		System.out.printf("Time taken to sort elements parallel =>  %6d ms \n", endTime1 - startTime1);
		System.out.println("");
		
		// ==============================
		// regular merge sort
		
		// run the algorithm and benchmark it
		startTime1 = System.currentTimeMillis();
		mergeSort.mergeSort(0, numbers.length-1);
		
		//mergeSort.showResult();
		endTime1 = System.currentTimeMillis();
		
		System.out.println("");
		System.out.printf("Time taken to sort elements sequential =>  %6d ms \n", endTime1 - startTime1);
		System.out.println("");
		
		
		
	}
	
	public static int[] createRandomArray() {
		int[] a = new int[10000];
		for (int i = 0; i < 10000; i++) {
			a[i] = random.nextInt(10000);			
		}
		return a;
	}
}
