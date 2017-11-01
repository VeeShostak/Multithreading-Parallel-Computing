package com.veeshostak.github;


// Volatile keyword:
// RAM: 
// 	cache,cpu1: thread1
// 	cache,cpu12 thread2

// Every read of a volatile variable will be read from RAM, not from cache (usually variables are cached for performance reasons)

// Caches are faster -> do not use volatile kw if not necessary.

// If both threads using same variable, most likely will need to use RAM.

class Worker implements Runnable {

	private volatile boolean isTerminated = false;
	
	@Override
	public void run() {
		
		while(!isTerminated) {
			
			System.out.println("Hello from worker class...");
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
}

public class Demo {

	public static void main(String[] args) {
		
		Worker worker = new Worker();
		Thread t1 = new Thread(worker);
		t1.start();
			
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		worker.setTerminated(true);
		System.out.println("Finished...");
	}
}
