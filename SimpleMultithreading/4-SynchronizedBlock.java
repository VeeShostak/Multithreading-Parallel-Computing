package com.veeshostak.github;

// Problem: Class intrinsic lock. If a class has methods with the synchronized keyword. 
// If thread uses that method, no other synchronized method can be called from that class 
// until it is done with that method (because an intrinsic lock on the class was placed).

/*
public class Demo {

	private static int count1 = 0;
	private static int count2 = 0;
	
	public synchronized static void add() {
		count1++;
	}
	
	public synchronized static void addAgain() {
		count2++;
	}

	public static void compute() {
		for(int i=0;i<100;++i) {
			add();
			addAgain();
		}
	}
	
	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();
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
		
		System.out.println("Count1="+count1+" - Count2="+count2);
	}
}
*/


// So we use different locks for each synchronized block:
// thread one can use one synchronized method, thread 2 can use another synchronized method


public class Demo {

	private static int count1 = 0;
	private static int count2 = 0;

	private static Object lcok1 = new Object();
	private static Object lcok2 = new Object();

	
	public synchronized static void add() {
		synchronized (lock1) {
			count1++;

		}
		
	}
	
	public synchronized static void addAgain() {
		synchronized (lock2) {
			count2++;

		}
	}

	public static void compute() {
		for(int i=0;i<100;++i) {
			add();
			addAgain();
		}
	}
	
	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();
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
		
		System.out.println("Count1="+count1+" - Count2="+count2);
	}
}