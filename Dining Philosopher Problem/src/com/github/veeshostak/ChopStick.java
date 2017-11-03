package com.github.veeshostak;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

	private Lock lock;
	private int id;
	
	public ChopStick(int id){
		this.id = id;
		this.lock = new ReentrantLock();
	}
	
	// check if philosopher is able to pick up that chopstick. (State is left or right chopstick)
	public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException{
	
		// given philosopher tries to pick up for 10 milliseconds
		if( this.lock.tryLock(10, TimeUnit.MILLISECONDS)){
			// note: this: calls the toString Method
			System.out.println(philosopher+" picked up "+state.toString()+" "+ this);
			return true;
		}
		// unable to pick up chopstick, return false
		return false;
	}
	
	public void putDown(Philosopher philosopher, State state) {
		this.lock.unlock();
		// note: this: calls the toString Method
		System.out.println(philosopher+" put down "+this);
	}
	
	@Override
	public String toString() {
		return "Chopstick-"+this.id;
	}
}
