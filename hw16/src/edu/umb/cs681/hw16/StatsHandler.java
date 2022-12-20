package edu.umb.cs681.hw16;

import java.util.concurrent.locks.ReentrantLock;

public class StatsHandler implements Runnable{
	
	private AdmissionMonitor monitor;
	private ReentrantLock lock;
	volatile boolean done = false;
	
	public StatsHandler (AdmissionMonitor monitor) {
		this.monitor = monitor;
		this.lock = new ReentrantLock();
	}
	
	public void setDone(){ 
		
		done = true;
	}

	public void run() {
		while(true) {
			lock.lock();
			try {
	    		if(done){
					System.out.println("Stopped Requesting Stats.");
					break;
				}
	    		monitor.countCurrentVisitors();
			}finally {
	    		lock.unlock();
	    	}
		}
		
	}

}
