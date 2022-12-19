package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
	
	protected String name;
	protected int size;
	protected LocalDateTime creationTime;
	protected Directory parent;
	protected ReentrantLock lock = new ReentrantLock();
	
	
	
	public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
		
	}
	
	public String getName() {
		lock.lock();
		try {
			return this.name;
		}finally {
			lock.unlock();
		}
		
	}
	
	public int getSize() {
		lock.lock();
		try {
			return this.size;
		}finally {
			lock.unlock();
		}
	}
	
	public Directory getParent() {
		lock.lock();
		try {

			return this.parent;
		}finally {
			lock.unlock();
		}
	}
	
	public LocalDateTime getCreationTime() {
		return this.creationTime;
	}
	
	public void setParent(Directory parent) {
		lock.lock();
		try {
			this.parent = parent;
		}finally {
			lock.unlock();
		}
	}
	
	public void setSize(int size) {
		lock.lock();
		try {
			this.size = size;
		}finally {
			lock.unlock();
		}
		
	}
	
	public void setName(String name) {
		lock.lock();
		try {
			this.name = name;
		}finally {
			lock.unlock();
		}
	}
	public abstract boolean isDiretory();
	public abstract boolean isLink();


	
	
}
