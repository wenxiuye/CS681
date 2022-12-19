package edu.umb.cs681.hw18;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{
	
	volatile boolean done = false;
	private AccessCounter ac;
	private ReentrantLock lock;
	
	public RequestHandler(AccessCounter ac) {
		this.ac = ac;
		this.lock = new ReentrantLock();
	}
	
	public void setDone(){ 
		done = true;
	}

	@Override
	public void run() {
		
		while(true) {
			lock.lock();
			try {
	    		if(done){
					System.out.println("Stopped Requesting Handler.");
					break;
				}
	    		int rand = (int)(Math.random() * 4);
				System.out.println("Rand :" + rand);
				switch (rand){
					case 0:
						Path path0 =  Paths.get("data/a.html");
						this.ac.increment(path0);
						System.out.println("path0 selected!");
						break;
					case 1:
						Path path1 =  Paths.get("data/b.html");
						this.ac.increment(path1);
						System.out.println("path1 selected!");
						break;
					case 2:
						Path path2 =  Paths.get("data/c.html");
						this.ac.increment(path2);
						System.out.println("path2 selected!");
				        break;
				    case 3:
				    	Path path3 =  Paths.get("data/d.html");
				    	this.ac.increment(path3);
				    	System.out.println("path3 selected!");
				        break;
				}
				try {
					System.out.println("Sleeping for 3 seconds");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    	}finally {
	    		lock.unlock();
	    	}
		}

		
		
	}
	

}
