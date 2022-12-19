package edu.umb.cs681.hw17;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
	private LinkedList<Observer<T>> observers = new LinkedList<>();
	private AtomicBoolean change = new AtomicBoolean(false);
	private ReentrantLock lock = new ReentrantLock();
	
	public void addObserver(Observer<T> o) {
		lock.lock();
		try {
			observers.add(o);
		}finally {
			lock.unlock();
		}
	}

	public void clearObservers() {
		lock.lock();
		try {
			observers.clear();
		}finally {
			lock.unlock();
		}
	}
	
	public List<Observer<T>> getObservers(){
		lock.lock();
		try {
			return observers;
		}finally {
			lock.unlock();
		}
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
		lock.lock();
		try {
			return observers.size();
		}finally {
			lock.unlock();
		}
		
	}
	
	public void removeObserver(Observer<T> o) {
		lock.lock();
		try {
			observers.remove(o);
		}finally {
			lock.unlock();
		}
		
	}

	public void notifyObservers(T event) {
		LinkedList<Observer<T>> localObservers;
		lock.lock();
		try {
			localObservers = new LinkedList<>(observers);
		}finally {
			lock.unlock();
		}
		localObservers.forEach( (observer)->{observer.update(this, event);} );
	}
	
}
