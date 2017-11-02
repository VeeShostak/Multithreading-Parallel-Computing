package com.github.veeshostak;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
  	BlockingQueue -> an interface that represents a queue that is thread safe
	Put items or take items from it

	For example: one thread putting items into the queue and another thread taking items from it
	at the same time (it’s made thread-safe)

	We can do it with producer-consumer pattern (no need for wait and notify, or low level 	synchronization or locks)

	put() putting items to the queue
	take() taking items from the queue

	if full, cant add wait. if 0 cant remove, wait
  
 */

// puts items into queue
class FirstWorker implements Runnable {

	private BlockingQueue<String> blockingQueue;
	
	public FirstWorker(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			
			blockingQueue.put("A");
			System.out.println("putting into the queue: " + "A");
            Thread.sleep(1000);
            blockingQueue.put("B");
            System.out.println("putting into the queue: " + "B");
            Thread.sleep(1000);
            blockingQueue.put("C");
            System.out.println("putting into the queue: " + "C");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }	
	}
}

// takes items from queue
class SecondWorker implements Runnable {

	private BlockingQueue<String> blockingQueue;
	
	public SecondWorker(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
            System.out.println("taking from queue " + blockingQueue.take());
            Thread.sleep(4000);
            System.out.println("taking from queue " + blockingQueue.take());
            Thread.sleep(4000);
            System.out.println("taking from queue " + blockingQueue.take());
           
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}

public class Demo {

	public static void main(String[] args) {
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

		FirstWorker firstWorker = new FirstWorker(queue);
		SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
		
	}
}
