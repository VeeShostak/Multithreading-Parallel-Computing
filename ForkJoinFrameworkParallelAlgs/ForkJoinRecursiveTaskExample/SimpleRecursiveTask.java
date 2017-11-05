package com.github.veeshostak;

import java.util.concurrent.RecursiveTask;

// EXTENDS RecursiveTask
public class SimpleRecursiveTask extends RecursiveTask<Integer> {

	private int simulatedWork;
	
	public SimpleRecursiveTask(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}
	
	@Override
	protected Integer compute() {
		
		if( simulatedWork > 100 ) {

			// not gurtanteed that every single thread will be assigned a task, they will be inserted into the forkjoin pool
			// and it will determine if there is a thread available to excute the given task
			
			System.out.println("Use Parallel execution and split the tasks. " + simulatedWork);
			
			SimpleRecursiveTask simpleRecursiveTask1 = new SimpleRecursiveTask(simulatedWork/2);
			// other half
			SimpleRecursiveTask simpleRecursiveTask2 = new SimpleRecursiveTask(simulatedWork/2);
			
			// arranges to asynchronously execute this task in the forkJoin pool the current task is running in
			simpleRecursiveTask1.fork();
			simpleRecursiveTask2.fork();
			
			int solution = 0;
			solution = solution + simpleRecursiveTask1.join();
			solution = solution + simpleRecursiveTask2.join();
			
			return solution;
			
		} else {
			System.out.println("No need for parallel execution, use sequential" + simulatedWork);
			return 2 * simulatedWork;
		}
	}
}
