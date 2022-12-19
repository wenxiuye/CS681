package edu.umb.cs681.hw16;

public class StatsHandler implements Runnable{
	
	private AdmissionMonitor monitor;
	
	public StatsHandler (AdmissionMonitor monitor) {
		this.monitor = monitor;
	}
	
	public void run() {
		
		monitor.countCurrentVisitors();
		
	}

}
