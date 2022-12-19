package edu.umb.cs681.hw14;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	private ReentrantLock lock = new ReentrantLock();
	private AtomicBoolean done = new AtomicBoolean(false);
	
	public WithdrawRunnable(BankAccount account) {
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
		    		System.out.println("Lock obtained for WithdrawRunnable");
		    		if(done.get()){
						System.out.println("Stopped withdrawing money.");
						break;
					}
		    		account.withdraw(100);
					Thread.sleep(2000);
		    	}finally {
		    		lock.unlock();
		    	}
			}
		}catch(InterruptedException exception){}
	}
}
