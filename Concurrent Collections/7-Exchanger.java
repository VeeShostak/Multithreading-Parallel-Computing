package com.github.veeshostak;

import java.util.concurrent.Exchanger;


/*
With the help of Exchanger -> two threads can exchange objects

2 threads. Each has distinct memory allocation (each has cache). They can’t see each other’s variables, so we use exchanger.
If we pass object 1 to t2 from t1, we have to pass some object2 to t1 from t2

exchange() -> it will exchange objects between 2 threads. exchanging objects is done via one of the two exchange() methods

Ex: genetic algorithms, training neural networks

Output(approximate):
1st t incremented the counter: 1
2nd t decremented the counter: -1
(They will exchange here)
1st t incremented the counter: 0
2nd t decremented the counter: 0
(They will exchange here)
1st t incremented the counter: 1
2nd t decremented the counter: -1
...

 
 */

class FirstWorker implements Runnable {

	private int counter;
	private Exchanger<Integer> exchanger;

	public FirstWorker(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		while (true) {

			counter = counter + 1;
			System.out.println("FirstWorker incremented the counter: " + counter);
			
			try {
				counter = exchanger.exchange(counter);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SecondWorker implements Runnable {

	private int counter;
	private Exchanger<Integer> exchanger;

	public SecondWorker(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		while (true) {

			counter = counter - 1;
			System.out.println("SecondWorker decremented the counter: " + counter);
			
			try {
				counter = exchanger.exchange(counter);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Demo {

	public static void main(String[] args) {

		Exchanger<Integer> exchanger = new Exchanger<>();

		new Thread(new FirstWorker(exchanger)).start();
		new Thread(new SecondWorker(exchanger)).start();

	}
}