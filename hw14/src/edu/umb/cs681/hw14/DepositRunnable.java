package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable{
	private BankAccount account;
	private ReentrantLock lock = new ReentrantLock();
	private AtomicBoolean done = new AtomicBoolean(false);
	
	public DepositRunnable(BankAccount account) {
		
		this.account = account;
	}
	public void setDone() {
		lock.lock();
		try {
			done.getAndSet(true);
		}finally {
			lock.unlock();
		}

	}
	public void run(){
		try{
			for(int i = 0; i < 10; i++){
		    	lock.lock();
		    	try {
		    		if(done.get()){
						System.out.println("Stopped Depositing money.");
						break;
					}
					account.deposit(100);
					Thread.sleep(2000);
		    	}finally {
		    		lock.unlock();
		    	}
			}
		}catch(InterruptedException exception){}
	}
}