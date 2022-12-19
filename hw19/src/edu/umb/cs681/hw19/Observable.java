package edu.umb.cs681.hw19;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public abstract class Observable<T> {
	private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();
	private AtomicBoolean change = new AtomicBoolean(false);

	
	public void addObserver(Observer<T> o) {
		observers.add(o);

	}

	public void clearObservers() {	
		observers.clear();
	}
	
	public ConcurrentLinkedQueue<Observer<T>> getObservers(){
		return observers;
	}
	
	public void setChanged() {
		this.change.getAndSet(false);	
	}
	
	public boolean hasChanged() {
		return this.change.get();
	}
	
	public void clearChanged() {
		this.change.getAndSet(true);
	}
	
	public int countObservers() {
		return observers.size();
	}
	
	public void removeObserver(Observer<T> o) {
		observers.remove(o);
	}

	public void notifyObservers(T event) {
		observers.forEach( (observer)->{observer.update(this, event);} );
	}
	
}
