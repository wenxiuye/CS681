package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		LinkedList<Thread> threads = new LinkedList<Thread>();
	    LinkedList<RequestHandler> rhs = new LinkedList<RequestHandler>();
	    AccessCounter ac = AccessCounter.getInstance();
	    
	    for (int i = 0; i <15; i++) {
	    	RequestHandler rh = new RequestHandler(ac);
	    	Thread t = new Thread(rh);
	    	threads.add(t);
	    	rhs.add(rh);
	    	t.start();
			rh.setDone();
			t.interrupt();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	    
	    Map<Path, Integer> map = ac.getACMap();
		System.out.println("Final result: ");
		map.forEach((key, value) -> System.out.println(key + ":" + value));
        
    }
}
