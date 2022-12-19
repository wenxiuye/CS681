package edu.umb.cs681.hw12;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{

	private LinkedList <FSElement> children;
	private ReentrantLock lock = new ReentrantLock();
	
	public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
		super(parent, name, size, creationTime);
		this.children = new LinkedList<FSElement>();
		
	}

	public LinkedList<FSElement> getChildren(){
		lock.lock();
		try {
			return this.children;
		}finally {
			lock.unlock();
		}
		
	}
	
	//add child to the list
	public void appendChild(FSElement child) {
		
		lock.lock();
		try {
			if (child.getParent() == null)
				child.setParent(this);
			children.add(child);
		}finally {
			lock.unlock();
		}
		
	}
	
	//get the size of the list
	public int countChilderen() {
		lock.lock();
		try {
			return children.size();
		}finally {
			lock.unlock();
		}
		
	}
	
	//get sub directories under current directory 
	public LinkedList<Directory> getSubDirectories(){
		LinkedList <Directory> directories = new LinkedList<Directory>();
		
		lock.lock();
		try {
			for (FSElement child : children) {
				if (child.isDiretory())
					directories.add((Directory) child);
					
			}
			return directories;
		}finally {
			lock.unlock();
		}
		
	}
	
	//get the files under current directory
	public LinkedList<File> getFiles(){
		LinkedList <File> files = new LinkedList<File>();
		
		lock.lock();
		try {
			for (FSElement child : children) {
				if (!child.isDiretory())
					files.add((File) child);	
			}
			return files;
		}finally {
			lock.unlock();
		}
		
	}
	
	//count the size of all the files under current directory
	public int getTotalSize() {
		
		int total = 0;
		lock.lock();
		try {
			for (FSElement child : children) {
				if (child.isDiretory()) {
					Directory dir = (Directory) child; 
					total += dir.getTotalSize();
				}
				else
					total += child.getSize();
			}
			return total;
		}finally {
			lock.unlock();
		}
		

	}
	@Override
	public boolean isDiretory() {
		lock.lock();
		try {
			return true;
		}finally {
			lock.unlock();
		}
	}
	
	@Override
	public boolean isLink() {
		lock.lock();
		try {
			return false;
		}finally {
			lock.unlock();
		}
	}
	
}
