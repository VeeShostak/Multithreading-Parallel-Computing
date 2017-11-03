package com.github.veeshostak.view;

import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import com.github.veeshostak.constants.State;

public class Cell extends JPanel{

	private static final long serialVersionUID = 1L;
	private int id;
	private Lock lock; // only one mine layer or sweeper can acquire it
	private State state; // track what type of cell
	private boolean hasBomb;
	
	public Cell(int id) {
		initVariables(id);
		setLayout(new GridLayout());
	}

	private void initVariables(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
		this.state = State.EMPTY;
		this.hasBomb = false;
	}
	
	public void lock(){
		try{
			lock.tryLock(10,TimeUnit.HOURS);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void unlock(){
		lock.unlock();
	}

	@Override
	public String toString() {
		return ""+this.id+"-"+state.toString()+"-"+hasBomb;
	}
}
