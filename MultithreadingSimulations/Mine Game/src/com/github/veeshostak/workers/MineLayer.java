package com.github.veeshostak.workers;

import java.util.Random;

import com.github.veeshostak.constants.Constants;
import com.github.veeshostak.view.Board;

public class MineLayer implements Runnable{

	private int id;
	private Board board; // get board to set mines
	// on stop clicked, stop thread
	private volatile boolean layerRunning;
	
	public MineLayer(int id, Board board){
		this.id = id;
		this.board = board;
		this.layerRunning = true;
	}
	
	@Override
	public void run() {
		
		Random random = new Random();
		
		while(layerRunning){
			
			if( Thread.currentThread().isInterrupted()){
				return;
			}
			
			// get random cell to set a mine on
			int index = random.nextInt(Constants.BOARD_ROWS * Constants.BOARD_COLUMNS);
			board.setMine(index);
			
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public void setLayerRunning(boolean layerRunning){
		this.layerRunning = layerRunning;
	}

	@Override
	public String toString() {
		return ""+this.id;
	}
}
