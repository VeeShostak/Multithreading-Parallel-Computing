package com.github.veeshostak;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/*
Priority Blocking Queue 

It implements the BlockingQueue interface

- unbounded concurrent queue
- it uses the same ordering rules as the java.util.PriorityQueue class -> have to implement the Comparable interface
(Like a priority queue but is concurrent (thread-safe))

The Comparable interface will determine what will be the order in the queue
// things will be taken out by their priority

The priority can have elements that are the same, compare() == 0 case
- no null items 
 */

// takes item
class FirstWorker2 implements Runnable {

	private BlockingQueue<String> blockingQueue;
	
	public FirstWorker2(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			blockingQueue.put("B");
			blockingQueue.put("H");
			blockingQueue.put("F");
            Thread.sleep(1000);
            blockingQueue.put("A");
            Thread.sleep(1000);
            blockingQueue.put("E");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }	
	}
}

// will be taken out by their priority
class SecondWorker2 implements Runnable {

	private BlockingQueue<String> blockingQueue;
	
	public SecondWorker2(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}

public class Demo {

	public static void main(String[] args) {
		
		BlockingQueue<String> queue = new PriorityBlockingQueue<>();

		FirstWorker2 firstWorker = new FirstWorker2(queue);
		SecondWorker2 secondWorker = new SecondWorker2(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
		
	}
}
