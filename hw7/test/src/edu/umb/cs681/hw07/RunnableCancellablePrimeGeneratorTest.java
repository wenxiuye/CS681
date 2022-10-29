package edu.umb.cs681.hw07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RunnableCancellablePrimeGeneratorTest {

    @Test
    void test() {
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
        Thread thread = new Thread(gen);
        thread.start();
        gen.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
        int actualSize = gen.getPrimes().size();
        assertEquals(actualSize, 0);
    }

}
