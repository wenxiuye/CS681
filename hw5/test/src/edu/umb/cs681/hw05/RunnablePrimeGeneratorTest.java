package edu.umb.cs681.hw05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class RunnablePrimeGeneratorTest {
	public static void main(String[] args) throws Exception {
		
	    LinkedList<Thread> threads = new LinkedList<Thread>();
	    LinkedList<RunnablePrimeGenerator> gens = new LinkedList<RunnablePrimeGenerator>();

	    int nThreads = Integer.parseInt(args[0]);
	    
	    long from = 1;
	    long counter = 2000000L / nThreads;
    	long to = counter;
	    
	    for (int i = 0; i < nThreads; i++) {
	  
	    	RunnablePrimeGenerator gen = new RunnablePrimeGenerator(from, to);
	    	gens.add(gen);
	    	
	    	Thread t = new Thread(gen);
	    	threads.add(t);
	    	
	    	t.start();
			try {
				t.join();
			} catch (InterruptedException e) {}
			
			from += counter;
			to = counter * (i+2);
	    	
	    }
	    
	    int actualPrimeListSize = 0;
	    for(RunnablePrimeGenerator g : gens)
	    	actualPrimeListSize += g.getPrimes().size();
	    
	    PrimeGenerator gen = new PrimeGenerator(1, 2000000L);
	    gen.generatePrimes();
	    int expectedPrimeListSize = gen.getPrimes().size();
	    
	    System.out.println("actualPrimeListSize: " + actualPrimeListSize);
	    System.out.println("expectedPrimeListSize: " + expectedPrimeListSize);
	    
	   
	  }

}
