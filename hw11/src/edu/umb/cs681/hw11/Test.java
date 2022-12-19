package edu.umb.cs681.hw11;

public class Test {
	public static void main(String[] args) {
		
		
		RunnableAircraft runnable = new RunnableAircraft(new Position(1,2,3));
		Thread thread = new Thread(runnable);
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
