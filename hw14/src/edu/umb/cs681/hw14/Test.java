package edu.umb.cs681.hw14;


public class Test {
	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		
		for(int i = 0; i < 5; i++){
			WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
			DepositRunnable depositRunnable = new DepositRunnable(bankAccount);
			Thread t1 = new Thread(withdrawRunnable);
			Thread t2 = new Thread(depositRunnable);
			t1.start();
			withdrawRunnable.setDone();
			t1.interrupt();
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t2.start();
			depositRunnable.setDone();
			t2.interrupt();
			try {
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    
		}
	}

}

