package edu.umb.cs681.hw17;

public class StockEvent {

	private String ticker;
	private float quote;
	
	public StockEvent(String t, float q) {
		this.ticker = t;
		this.quote = q;
	}

	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public float getQuote() {
		return this.quote;
	}

	public void setQuote(float quote) {
		this.quote = quote;
	}
}
