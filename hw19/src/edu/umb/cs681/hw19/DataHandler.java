package edu.umb.cs681.hw19;

import java.util.concurrent.locks.ReentrantLock;

public class DataHandler implements Runnable{
	
	volatile boolean done;
	private ReentrantLock lock;
	StockQuoteObservable<StockEvent> stockObservable;
	
	public DataHandler(StockQuoteObservable<StockEvent> stockObservable) {
		this.done = false;
		this.lock = new ReentrantLock();
		this.stockObservable = stockObservable;
	}
	
	public void setDone(){ 
		done = true;
	}

	@Override
	public void run() {
		
		while(true) {
			lock.lock();
			try {
	    		if(done){
					System.out.println("Data Handler Stopped.");
					break;
				}
	    		float quote = (float) Math.random() * 50;
				
				stockObservable.changeQuote("STOCK"+quote, quote);
				
				try {
					System.out.println("Sleeping for 3 seconds");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    	}finally {
	    		lock.unlock();
	    	}
		}

		
		
	}
}