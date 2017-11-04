package com.github.veeshostak;

public class ParallelWorker extends Thread {

	private int[] nums;
	// sum up within an interval of indices
	private int low;
	private int high;
	
	private int partialSum;

	public ParallelWorker(int[] nums, int low, int high) {
		this.nums = nums;
		this.low = low;
		this.high = Math.min(high, nums.length);
	}

	public int getPartialSum() {
		return partialSum;
	}

	@Override
	public void run() {
		
		partialSum = 0;

		for (int i = low; i < high; i++) {
			// accumulate
			partialSum += nums[i];
		}
	}
}
