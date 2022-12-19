package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {

	
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	
	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}
	
	public void setDone() {
		lock.lock();
		try {
			System.out.println("setDone called");
			done = true;
		}finally {
			lock.unlock();
		}

	}
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}
	
	public void generatePrimeFactors() {
		long divisor = from;
		
	    while( dividend != 1 && divisor <= to ){
	    	
	    	lock.lock();
	    	try {
	    		if(done){
					System.out.println("Stopped performing factorization.");
					this.factors.clear();
					break;
				}
		    	if( divisor > 2 && isEven(divisor)) {
		    		divisor++;
		    		continue;
		    	}
			    if(dividend % divisor == 0) {
			        factors.add(divisor);
			        dividend /= divisor;
			    }else {
			    	if(divisor==2){ divisor++; }
			    	else{ divisor += 2; }
			    }
	    	}finally {
	    		lock.unlock();
	    	}
		}
	}
	public static void main(String[] args) {
		System.out.println("Factorization of 36");
		RunnableCancellablePrimeFactorizer runnable = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread thread = new Thread(runnable);
		System.out.println("Thread #" + thread.getId() + 
			" performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
		thread.start();
		runnable.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
		
		
	
	}

}
