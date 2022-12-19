package edu.umb.cs681.hw16;

public class ExitHandler implements Runnable{
	
	private AdmissionMonitor monitor;
	
	public ExitHandler(AdmissionMonitor monitor) {
		this.monitor = monitor;
	}
	
	public void run() {
		
		monitor.exit();
	}

}
