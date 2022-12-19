package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

	Map<Path, Integer> AC = new HashMap<>();;
	private static AccessCounter instance = null; 
    private static ReentrantLock slock = new ReentrantLock();
    private  ReentrantLock lock = new ReentrantLock();
    
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
    	
    	lock.lock();
        try{
            if(AC.containsKey(path)){ 
            	AC.put(path, getCount(path) + 1);
            }else {
            	AC.put(path, 1);
            }
        } finally{
            lock.unlock();
        }
    	
    	
    }
    public Map<Path, Integer> getACMap() {
    	return this.AC;
    }
    
    public int getCount(Path path) {
    	lock.lock();
        try{
        	if(AC.containsKey(path)){ 
        		return  AC.get(path);
        	}
        	else {
        		return 0;
        	}
        }finally{
            lock.unlock();
        }	
    }
}
