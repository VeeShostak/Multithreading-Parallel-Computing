package com.github.veeshostak;

public class ParallelSum {

	private ParallelWorker[] partialSums;
	private int numOfThreads;
	
	public ParallelSum(int numOfThreads) {
		this.partialSums = new ParallelWorker[numOfThreads];
		this.numOfThreads = numOfThreads;
	}
	
	public int parallelSum(int[] numsArr) {

		// the more threads the fewer integers we can assign to a single worker
		
		int size = (int) Math.ceil(numsArr.length * 1.0 / numOfThreads);

		// create as many parallel workers as the number of cores
		for (int i = 0; i < numOfThreads; i++) {
			partialSums[i] = new ParallelWorker(numsArr, i * size, (i + 1) * size);
			partialSums[i].start();
		}

		try {
			for (ParallelWorker sum : partialSums) {
				sum.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int total = 0;

		for (ParallelWorker sum : partialSums) {
			total += sum.getPartialSum();
		}

		return total;
	}

}
