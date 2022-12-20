package edu.umb.cs681.hw16;

import java.util.concurrent.locks.ReentrantLock;

public class ExitHandler implements Runnable{
	
	private AdmissionMonitor monitor;
	private ReentrantLock lock;
	volatile boolean done = false;
	
	public ExitHandler(AdmissionMonitor monitor) {
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
					System.out.println("Stopped Exiting.");
					break;
				}
	    		monitor.exit();
			}finally {
	    		lock.unlock();
	    	}
		}
		
	}

}
