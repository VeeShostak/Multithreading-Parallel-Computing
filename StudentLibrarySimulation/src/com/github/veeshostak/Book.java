package com.github.veeshostak;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

	private int id;
	private Lock lock; // oily single student can read a book at a time

	public Book(int id) {
		this.lock = new ReentrantLock();
		this.id = id;
	}

	public void read(Student student) throws InterruptedException {
		
//		if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
//			System.out.println(student + " starts reading " + this);
//			Thread.sleep(2000);
//			lock.unlock();
//			System.out.println(student + " has finished reading " + this);
//			
//		}
		
		lock.lock();
		System.out.println(student + " starts reading " + this);
		Thread.sleep(2000);
		lock.unlock();
		System.out.println(student + " has finished reading " + this);
	}

	public String toString() {
		return "Book #" + id;
	}

}