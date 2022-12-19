package edu.umb.cs681.hw12;
import java.util.concurrent.locks.ReentrantLock;


public class FileSystemRunnable implements Runnable{
	
	volatile boolean done = false;
	private FileSystem fs;
	private ReentrantLock lock;
	
	public FileSystemRunnable(FileSystem fs) {
		this.fs = fs;
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
					System.out.println("Stopped!");
					break;
				}
	    		int rand = (int)(Math.random() * 4);
				System.out.println("Rand :" + rand);
				Directory root = this.fs.getRootDirs().get(0);
				Directory home = (Directory) root.getChildren().get(2);
				FSElement fs = root.getChildren().get(0);
				Link z = (Link) home.getChildren().get(0);
				switch (rand){
					case 0:
						System.out.println("Directory Name: "+ root.getName());
						System.out.println("Directory Size:"+ root.getSize());
						System.out.println("Directory Totoal Size:"+ root.getTotalSize());
						System.out.println("Directory isDiretory? "+ root.isDiretory());
						System.out.println("Directory Creation Time:"+ root.getCreationTime());
						break;
					case 1:
						
						System.out.println("Directory Name: "+ home.getName());
						System.out.println("Directory Size:"+ home.getSize());
						System.out.println("Directory isDiretory? "+ home.isDiretory());
						System.out.println("Directory isLink?"+ home.isLink());
						break;
					case 2:
						System.out.println("FSElement Name: "+ fs.getName());
						System.out.println("FSElement Size:"+ fs.getSize());
						System.out.println("FSElement isDiretory? "+ fs.isDiretory());
						System.out.println("FSElement isLink?"+ fs.isLink());
						break;
					case 3:
						System.out.println("Link Name: "+ z.getName());
						System.out.println("Link Target:"+ z.getTarget());
						System.out.println("Link isDiretory? "+ z.isDiretory());
						System.out.println("Link isLink?"+ z.isLink());
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
