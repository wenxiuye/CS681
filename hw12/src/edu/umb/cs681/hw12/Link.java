package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement {
	
	FSElement target; 
	ReentrantLock lock = new ReentrantLock();
	
	public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
		super(parent, name, size, creationTime);
		this.target = target;
		
	}
	
	public FSElement getTarget() {
		lock.lock();
		try {
			return this.target;
		}finally {
			lock.unlock();
		}
		
	}
	
	public void setTarget(FSElement target) {
		lock.lock();
		try {
			this.target = target;
		}finally {
			lock.unlock();
		}
	}

	@Override
	public boolean isDiretory() {
		lock.lock();
		try {
			return false;
		}finally {
			lock.unlock();
		}
	}

	@Override
	public boolean isLink() {
		lock.lock();
		try {
			return true;
		}finally {
			lock.unlock();
		}
	}

}