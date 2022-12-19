package edu.umb.cs681.hw19;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable<T> extends Observable<Object>{

	
	private  Map<String, Float> map;
	private ReentrantLock lock = new ReentrantLock();
	
	public StockQuoteObservable() {
		this.map = new HashMap<String, Float>(); 	
	}
	
	public Map<String, Float> getMap() {
		return this.map;
	}
	
	public void changeQuote(String t, float q) {
		lock.lock();
		try {
			map.put(t, q); 
			this.setChanged();
			this.notifyObservers(new StockEvent(t, q));
		}finally {
			lock.unlock();
		}
		
	}
}