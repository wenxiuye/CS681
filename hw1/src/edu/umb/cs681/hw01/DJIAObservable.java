package edu.umb.cs681.hw01;

public class DJIAObservable extends Observable<Double>{
	private double quote;
	
	public void changeQuote(double q) {
		quote = q;
		notifyObservers(quote);
	}
	
}
