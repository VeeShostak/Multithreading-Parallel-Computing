package com.veeshostak.github;
/*
	Producer and consumer simulation with wait and notify

	1st thread runs producer, second runs consumer
	Add items to a list. If size of list is equal to someSIZE, 
	make consumer remove the items. If size of list 0,  producer will add items.

	Output (approximate):
	Removing: 4,3,2,1,0
	Adding: 0,1,2,3,4

	Why?
	Because although we call lock.notify(), we have code after 
	(the infinite while loop), hence it will not allow other thread 
	to run until we encounter lock.wait()
*/

import java.util.ArrayList;
import java.util.List;

class Processor {

	private List<Integer> list = new ArrayList<>();
	private final int LIMIT = 5; // add up to 5 items
	private final int BOTTOM = 0; 
	private final Object lock = new Object();
	private int value = 0;
	
	public void producer() throws InterruptedException {

		synchronized (lock) {
			// will add all 5 items 0-4
			while(true) {
				
				if( list.size() == LIMIT ) {
					System.out.println("Waiting: removing items from the list...");
					lock.wait(); // let consumer consume
				} else {
					System.out.println("Adding: "+value);
					list.add(value);
					value++;

					 // although we call lock.notify(), we have code after 
					// (the infinite while loop), hence it will not allow other 
					// thread to run until we encounter lock.wait()
					lock.notify();
				}
				
				Thread.sleep(500);
			}
		}
	}

	public void consumer() throws InterruptedException {

		// will remove all 5 items 0-4
		synchronized (lock) {
			
			while(true) {
				
				if( list.size() == BOTTOM ) {
					System.out.println("Waiting for adding items to the list...");
					lock.wait();
				} else {
					System.out.println("Removed: "+list.remove(--value));

					 // although we call lock.notify(), we have code after 
					// (the infinite while loop), hence it will not allow other 
					// thread to run until we encounter lock.wait()
					lock.notify();
				}
				
				Thread.sleep(500);
			}
			
		}
		
	}
}

public class Demo {

	static Processor processor = new Processor();

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
	}
}
