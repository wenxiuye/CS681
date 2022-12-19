package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

	ConcurrentHashMap<Path, AtomicInteger> concurrentMap = new ConcurrentHashMap<>();;
	private static AccessCounter instance = null; 
    private static ReentrantLock slock = new ReentrantLock();
    
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
    	
    	AtomicInteger oldCount = concurrentMap.get(path);
    	AtomicInteger newCount = oldCount == null ? (new AtomicInteger(1)): (new AtomicInteger(oldCount.getAndIncrement()));
    	concurrentMap.put(path, newCount);
    	
    	
    }
    public ConcurrentHashMap<Path, AtomicInteger> getACMap() {
    	return this.concurrentMap;
    }
    
    public AtomicInteger getCount(Path path) {
    	
    	AtomicInteger oldCount = concurrentMap.get(path);
    	AtomicInteger newCount = oldCount == null ? (new AtomicInteger(0)): oldCount;
    	return newCount;
    	
    }
}
