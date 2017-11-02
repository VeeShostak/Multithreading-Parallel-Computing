package com.github.veeshostak;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 
  Latch --> multiple threads can wait for each other
 
  A CyclicBarrier is used in situations where you want to create a group of
  tasks to perform work in parallel + wait until they are all finished before
  moving on to the next step -> something like join() -> something like
  CountDownLatch
  
  CountDownLatch: one-shot event 
  CyclicBarrier: it can be reused over and over
  again
  
  + cyclicBarrier has a barrier action: a runnable, that will run automatically
  when the count reaches 0.
  // runs automatically when the count reaches 0, so we can run our neural network and use the n threads for other tasks
  
  new CyclicBarrier(N) -> N threads will wait for each other
 
  WE CAN NOT REUSE LATCHES BUT WE CAN REUSE CyclicBarriers --> reset() !!!
  

  ex. Make sure we assign every single thread to a different task, when threads are finished, do something
  Download data, normalize data, train neural network, use network 


  output (similar):
  Thread with id 0,3,2,4, finished
  We are able to use the trained neural network...
  After await (printed 5 times)

 */

class Worker implements Runnable {

	private int id;
	private Random random;
	private CyclicBarrier cyclicBarrier;

	public Worker(int id, CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
		this.random = new Random();
		this.id = id;
	}

	@Override
	public void run() {
		doWork();
	}

	private void doWork() {
		System.out.println("Thread with ID " + id + " starts the task...");
		try {
			Thread.sleep(random.nextInt(3000)); // sleep for 0 - 3s
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Thread with ID " + id + " finished...");

		try {
			cyclicBarrier.await();
			// await finished: CyclicBarrier reached 0, runnable was called, continue
			System.out.println("After await..."); 
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public String toString() { return ""+this.id; };
}

public class Demo {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			// runs automatically when the count reaches 0
			@Override
			public void run() {
				System.out.println("We are able to use the trained neural network...");
			}
		});
		
		for(int i=0;i<5;++i)
			executorService.execute(new Worker(i+1, barrier));
		
		executorService.shutdown();
	}
}

