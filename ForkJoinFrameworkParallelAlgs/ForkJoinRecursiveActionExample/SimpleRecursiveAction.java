package com.github.veeshostak;

import java.util.concurrent.RecursiveAction;

// RecursiveAction  it is a task, but without any return value

// EXTENDS RecursiveAction
public class SimpleRecursiveAction extends RecursiveAction {

	private int simulatedWork;
	
	public SimpleRecursiveAction(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}
	
	@Override
	protected void compute() {
		
		// if workload is  over 100
		if( simulatedWork > 100 ) {

			// not gurtanteed that every single thread will be assigned a task, they will be inserted into the forkjoin pool
			// and it will determine if there is a thread available to excute the given task
			
			System.out.println("Use Parallel execution and split the tasks. " + simulatedWork);
			
			SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(simulatedWork/2);
			// other half
			SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(simulatedWork/2);
			
			// arranges to asynchronously execute this task in the forkJoin pool the current task is running in
			simpleRecursiveAction1.fork();
			simpleRecursiveAction2.fork();
			
		} else {
			System.out.println("No need for parallel execution, use sequential" + simulatedWork);
		}
	}
}
