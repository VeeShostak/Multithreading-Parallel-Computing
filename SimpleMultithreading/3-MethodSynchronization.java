package com.veeshostak.github;


// Synchronized method:
// Problem: If two threads access method and increment var at same time: ex: in assembly language: 
// they both load 0, increment it to 1, and save. Should be 2 but instead it is a 1.

// Mark method synchronized. 
// only 1 thread can access this method until it’s done. 
// NOTE: places intrinsic lock on class (see Synchronized blocks)
//same as:
	// public static synchronized void increment() {
	// 	synchronized (App.class) {
	// 		count1++;

	// 	}
	// }

// Causes thread related overhead: execution time increases



public class Demo {

	private static int counter = 0;

	public static synchronized void increment() {
		++counter;
	}
	// same as:
	// public static synchronized void increment() {
	// 	synchronized (App.class) {
	// 		count1++;

	// 	}
	// }
	
	public static void process() {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; ++i)
					increment();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; ++i)
					increment();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		process();
		System.out.println(counter);
		
	}
}
