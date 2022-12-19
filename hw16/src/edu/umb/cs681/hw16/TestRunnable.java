package edu.umb.cs681.hw16;
import java.util.concurrent.locks.ReentrantLock;

public class TestRunnable implements Runnable{
	
	volatile boolean done = false;
	private ReentrantLock lock;
	private AdmissionMonitor monitor;
	
	
	public TestRunnable(AdmissionMonitor monitor) {
		this.lock = new ReentrantLock();
		this.monitor = monitor;
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
	    		int rand = (int)(Math.random() * 2);
				System.out.println("Rand :" + rand);
				
				EntranceHandler entrance = new EntranceHandler(this.monitor);
				ExitHandler exit = new ExitHandler(this.monitor);
				StatsHandler stats = new StatsHandler(this.monitor);
				
				switch (rand){
					case 0:
						entrance.run();
						entrance.run();
						entrance.run();
						stats.run();
						stats.run();
						exit.run();
						exit.run();
						exit.run();
								
						break;
					case 1:
						entrance.run();
						entrance.run();
						stats.run();
						entrance.run();
						entrance.run();
						entrance.run();
						stats.run();
						exit.run();
						exit.run();
						exit.run();
						exit.run();
						exit.run();
						break;
				
				}
				
	    	}finally {
	    		lock.unlock();
	    	}
		}

		
		
	}

}
