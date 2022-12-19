package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
	public static void main(String[] args) {
	    AccessCounter ac = AccessCounter.getInstance();
	    
	    for (int i = 0; i <15; i++) {
	    	RequestHandler rh = new RequestHandler(ac);
	    	Thread t = new Thread(rh);
	    	t.start();
			rh.setDone();
			t.interrupt();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	    ConcurrentHashMap<Path, AtomicInteger> map = ac.getACMap();
		System.out.println("Final result: ");
		map.forEach((key, value) -> System.out.println(key + ":" + value));
        
    }
}
