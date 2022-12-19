package edu.umb.cs681.hw16;


public class Test {
	
	public static void main(String[] args){
		
		AdmissionMonitor monitor = new AdmissionMonitor();
		TestRunnable test = new TestRunnable(monitor);
		for(int i = 0; i < 15; i++){
			
			Thread t = new Thread(test);
			t.start();
			test.setDone();
			t.interrupt();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Number Of Guests: " + monitor.countCurrentVisitors());
			
	}

}
