package edu.umb.cs681.hw16;


public class Test {
	
	public static void main(String[] args){
		
		AdmissionMonitor monitor = new AdmissionMonitor();
		
		for(int i = 0; i < 15; i++){
			
			EntranceHandler entrance = new EntranceHandler(monitor);
			ExitHandler exit = new ExitHandler(monitor);
			StatsHandler stats = new StatsHandler(monitor);
			
			Thread t1 = new Thread(entrance);
			Thread t2 = new Thread(exit);
			Thread t3 = new Thread(stats);

			t1.start();
			entrance.setDone();
			t1.interrupt();

			if (monitor.countCurrentVisitors() > 0){
				t2.start();
				exit.setDone();
				t2.interrupt();
				try {
					t2.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			t3.start();
			stats.setDone();
			t3.interrupt();


			try {
				t1.join();
				t3.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			
			
			// t1.start();
			// entrance.setDone();
			// t1.interrupt();
			// try {
			// 	t1.join();
			// } catch (InterruptedException e) {
			// 	e.printStackTrace();
			// }

			// if (monitor.countCurrentVisitors() > 0){
			// 	t2.start();
			// 	exit.setDone();
			// 	t2.interrupt();
			// 	try {
			// 		t2.join();
			// 	} catch (InterruptedException e) {
			// 		e.printStackTrace();
			// 	}
			// }

			// t3.start();
			// stats.setDone();
			// t3.interrupt();
			// try {
			// 	t3.join();
			// } catch (InterruptedException e) {
			// 	e.printStackTrace();
			// }
			
		}
		System.out.println("Number Of Guests: " + monitor.countCurrentVisitors());
			
	}

}
