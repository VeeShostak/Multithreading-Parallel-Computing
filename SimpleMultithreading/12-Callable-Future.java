package com.veeshostak.github;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



/*

	Callable and Future

	Callable, make thread return a value

	Future stores the value that is returned from the given threads.

	(ex. Parallel merge sort and return results of the subproblems)


	submit: Going to return a string, wrapped with a future object

	future.get() // gets string we have inserted


*/

class Processor implements Callable<String>{
	
	private int id;
	
	public Processor(int id){
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "Id: "+this.id;
	}
}

public class Demo {

	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<>();
		
		for(int i=0;i<5;i++){
			Future<String> future = executorService.submit(new Processor(i+1)); //  Going to return a string, wrapped with a future object
			list.add(future);
		}
		
		for(Future<String> future : list){
			try{
				System.out.println(future.get());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		executorService.shutdown();
		
	}
}
