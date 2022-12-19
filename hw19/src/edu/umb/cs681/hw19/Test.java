package edu.umb.cs681.hw19;


public class Test {
	public static void main(String[] args) throws InterruptedException{

		StockQuoteObservable<StockEvent> stockObservable = new StockQuoteObservable<StockEvent>();
		Observer<Object> LineChartObserver = (sender, event) -> System.out.println("LineChartObserver Event: " + event + ", Sender: " + sender);
		Observer<Object> TableObserver = (sender, event) -> System.out.println("TableObserver : Event: " + event + ", Sender: " + sender);
		Observer<Object> ThreeDObserver = (sender, event) -> System.out.println("ThreeDObserver: Event: " + event + ", Sender: " + sender);
		
		stockObservable.addObserver(LineChartObserver);
		stockObservable.addObserver(TableObserver);
		stockObservable.addObserver(ThreeDObserver);
		
		
		for(int i = 0; i <= 15; i++){
			DataHandler dataHandler = new DataHandler(stockObservable);
			Thread thread = new Thread(dataHandler); 
			thread.start();

			//2-Step Thread Termination
			dataHandler.setDone();
			thread.interrupt(); 
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
