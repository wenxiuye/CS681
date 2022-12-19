package edu.umb.cs681.hw15;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {

	Map<Path, Integer> AC = new HashMap<>();;
	private static AccessCounter instance = null; 
    private static ReentrantLock slock = new ReentrantLock();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    
    private AccessCounter() {
    	
    }
 
    // Factory method to create or return the singleton instance 
    public static AccessCounter getInstance(){
        slock.lock();
        try{
            if(instance == null){ 
                instance = new AccessCounter();}        
            return instance;
        } finally{
            slock.unlock();
        }
    }
    
    public void increment(Path path) {
    	
    	rwLock.writeLock().lock();
        try{
            if(AC.containsKey(path)){ 
            	AC.put(path, getCount(path) + 1);
            }else {
            	AC.put(path, 1);
            }
        } finally{
        	rwLock.writeLock().unlock();
        }
    	
    	
    }
    public Map<Path, Integer> getACMap() {
    	return this.AC;
    }
    
    public int getCount(Path path) {
    	rwLock.readLock().lock();
        try{
        	if(AC.containsKey(path)){ 
        		return  AC.get(path);
        	}
        	else {
        		return 0;
        	}
        }finally{
        	rwLock.readLock().unlock();
        }	
    }
}
