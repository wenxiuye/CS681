package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton implements Runnable{
    
    
    private static ConcurrentSingleton instance = null; 
    private static ReentrantLock lock = new ReentrantLock();
    
    private ConcurrentSingleton() {
        
    }
    
    // Factory method to create or return the singleton instance 
    public static ConcurrentSingleton getInstance(){
        lock.lock();
        try{
            if(instance==null){ 
                instance = new ConcurrentSingleton();}
            return instance;
        } finally{
            lock.unlock();
        }
    }
    
    @Override
    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
    }
    
    
    public static void main(String[] args) {
        ConcurrentSingleton concurrentSingleton1 = ConcurrentSingleton.getInstance();
        ConcurrentSingleton concurrentSingleton2 = ConcurrentSingleton.getInstance();
        ConcurrentSingleton concurrentSingleton3 = ConcurrentSingleton.getInstance();
        ConcurrentSingleton concurrentSingleton4 = ConcurrentSingleton.getInstance();
        ConcurrentSingleton concurrentSingleton5 = ConcurrentSingleton.getInstance();

        
        Thread t1 = new Thread(concurrentSingleton1);
        Thread t2 = new Thread(concurrentSingleton2);
        Thread t3 = new Thread(concurrentSingleton3);
        Thread t4 = new Thread(concurrentSingleton4);
        Thread t5 = new Thread(concurrentSingleton5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            
        } catch (InterruptedException e) {e.printStackTrace();}
        
    }


}
