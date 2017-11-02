package com.veeshostak.github;

// Wait and notify 

// wait() called only within synchronized block. It hands over the control over the block that the synchronized block is locked on.

// notify() notifies the waiting thread that it can wake up. 
// Has to be Nondeterministic (if multiple threads are waiting for the lock (locking on the class) there is no guarantee what thread will get the lock eventually (if we call notify, not sure if 2nd thread or 3rd thread… is going to wake up )).

// Call notify(), and if we have some code after notify, it will run first, then will notify the waiting thread that it can wake up. 

// wait(1000); // can specify how long it will wait

//  NotifyAll() notifies all threads, for many threads

// Output:
// We are in the producer method...
// Consumer method...
// Again in producer method...


class Processor {
	
	public void produce() throws InterruptedException {
		
		//Synchronize (this): intrinsic lock on the class
		synchronized (this) {
			System.out.println("We are in the producer method...");
			wait(10000); // can specify how long it will wait
			System.out.println("Again in producer method...");
		}
	}
	
	public void consume() throws InterruptedException {
		
		Thread.sleep(1000);
		
		//Synchronize (this): intrinsic lock on the class
		synchronized (this) {
			System.out.println("Consumer method...");
			// Call notify(), and if we have some code after notify, it will run first, 
			// then will notify the waiting thread that it can wake up. 
			notify(); 
			//notifyAll(); // for notifying all threads
			Thread.sleep(3000);
		}
	}
}

public class Demo {
	
	public static void main(String[] args) {

		Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
}