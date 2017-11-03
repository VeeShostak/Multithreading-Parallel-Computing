package com.github.veeshostak.workers;

import java.util.Random;

import com.github.veeshostak.constants.Constants;
import com.github.veeshostak.view.Board;

public class MineSweeper implements Runnable{

	private int id;
	private Board board; // get board to clean mines
	// on stop clicked, stop thread
	private volatile boolean sweeperRunning;
	
	public MineSweeper(int id, Board board){
		this.id = id;
		this.board = board;
		this.sweeperRunning = true;
	}
	
	@Override
	public void run() {
		
		Random random = new Random();
		
		while(sweeperRunning){
			
			if( Thread.currentThread().isInterrupted()){
				return;
			}
			// get random cell to clear mine from
			int index = random.nextInt(Constants.BOARD_ROWS*Constants.BOARD_COLUMNS);
			board.sweepMine(index);
			
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
				e.printStackTrace();
			}			
		}
	}
	
	public void setSweeperRunning(boolean sweeperRunning){
		this.sweeperRunning = sweeperRunning;
	}

	@Override
	public String toString() {
		return ""+this.id;
	}
}
