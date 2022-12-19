package edu.umb.cs681.hw16;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor {
	private int currentVisitors = 0;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private Condition currentCondition = rwLock.writeLock().newCondition();
	

	public int countCurrentVisitors() {
		rwLock.readLock().lock();
        try{
        	return this.currentVisitors;
        	
        }finally{
        	rwLock.readLock().unlock();
        }		
	}
	
	public void enter() {
		
		rwLock.writeLock().lock();
        try{
        	while(currentVisitors > 10){ 
        		System.out.println("Too many visitors. Please wait for a while!"); 
        		// waiting until the # of visitors goes below 10. 
        		try {
					System.out.println("Lock await");
					//Thread.sleep(1000); 
					currentCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		
        	}
        	System.out.println("Entering..."); 
        	this.currentVisitors++;
            
        } finally{
        	rwLock.writeLock().unlock();
        }
	}
	
	public void exit() {
		rwLock.writeLock().lock();
        try{
        	this.currentVisitors--;
        	System.out.println("Exiting..."); 
        	currentCondition.signalAll();
            
        } finally{
        	rwLock.writeLock().unlock();
        }
		
	}

}
