package com.github.veeshostak;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
	New ReentrantLock(boolean fairnessParameter)
	-fairness parameter: if it is set to true -> the longest waiting thread will get the lock
	If false-> there is no access order, threads may wait for an infinite time
	
	NOTE: have to use try, catch block when doing critical section that may throw exceptions
	Call unblock in the finally block
	
	critical section: (a code segment that accesses shared variables and has to be executed as an atomic action) 
*/

/*
	ReentrantLock vs synchronized blocks:

	We can make a lock fair: prevent thread starvation
	Synchronized blocks are unfair by default

	We can check whether the given lock is held or not
	with reentrant locks

	We can get the list of threads waiting for the given lock
	with reentrant locks

	Synchronized blocks are cleaner: we do not need the 
		try-catch-finally block

*/
	
public class Demo {

	private static int counter = 0;

	// note: (ReentrantLock is the class, lock is the interface. This reentrantLock is the implementation of the lock interface)
	private static Lock lock = new ReentrantLock();
	
	public static void increment(){
		
		// equivalent of the synchronized block:
		// lock.lock();
		
		// if this throws exception, unlock will not be called: making a deadlock situation
		// hence, must be placed in a try catch
		
		//for(int i=0;i<1000;i++){
		//	counter++;
		// lock.unlock();

		lock.lock();

		try {
			for(int i=0;i<1000;i++){
				counter++;
			}
		} finally {
			lock.unlock();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				increment();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
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
		
		System.out.println(counter);
		
	}
}


