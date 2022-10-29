package edu.umb.cs681.hw08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RunnableCancellablePrimeFactorizerTest {

    @Test
    void test() {
        System.out.println("Factorization of 36");
        RunnableCancellablePrimeFactorizer runnable = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
        Thread thread = new Thread(runnable);
        System.out.println("Thread #" + thread.getId() + 
            " performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
        thread.start();
        runnable.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
        int actualSize = runnable.getPrimeFactors().size();
        assertEquals(actualSize, 0);
    }

}
