package com.github.veeshostak;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
It was formulated by Dijkstra in 1965
They can eat or think
Philosophers can eat when they have both left and right chopsticks
A chopstick can be held by one philosopher at a given time 

The problem: how to create a concurrent algorithm such that no philosopher will starve (not wait forever for a chopstick)

*/

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executorService = null;
		Philosopher[] philosophers = null;
		
		try{
			
			philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
			ChopStick[] chopSticks = new ChopStick[Constants.NUMBER_OF_PHILOSOPHERS];
			
			for(int i=0;i<Constants.NUMBER_OF_CHOPSTICKS;i++){
				chopSticks[i] = new ChopStick(i);
			}
			
			
			executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
			
			for(int i=0;i<Constants.NUMBER_OF_PHILOSOPHERS;i++){
				// assign 2 chopsticks to every philosopher
				philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[(i+1) % Constants.NUMBER_OF_PHILOSOPHERS]);
				executorService.execute(philosophers[i]);
			}
			
			Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
			
			for(Philosopher philosopher : philosophers){
				philosopher.setFull(true);
			}		
		}finally{
			
			executorService.shutdown();
			
			while(!executorService.isTerminated()){
				Thread.sleep(1000);
			}
			
			for(Philosopher philosopher : philosophers ){
				System.out.println(philosopher+" eat #"+philosopher.getEatingCounter());
			}
			
		}
		
	}
}
