package com.github.veeshostak;

import java.util.concurrent.ForkJoinPool;

/*
fork -> split the given task into smaller subtasks that can be
		executed in a parallel manner

join -> the splitted tasks are being executed and after all of them are finished
		they are merged into one result

invoke(): executes the given task + return its result upon completion
join(): return result of the computation when done
fork():asynchronously execute the given task in the pool. We can call this on RecursiveAction or RecursiveTask(T)

*/

public class App {

	public static void main(String[] args) {
		
		// do not create more threads than the number of processors, because then it 
		// will be multithreaded execution and not parallel
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(400); // if greater than 100, will use parallel execution
		forkJoinPool.invoke(simpleRecursiveAction);
		
		/*
		Output:

		Use Parallel execution and split the tasks. 120
		No need for parallel execution, use sequential 60
		No need for parallel execution, use sequential 60
		*/
	}
}
