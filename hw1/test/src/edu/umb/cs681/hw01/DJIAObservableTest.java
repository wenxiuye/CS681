package edu.umb.cs681.hw01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DJIAObservableTest {

	@Test
	void test() {
		DJIAObservable observable = new DJIAObservable();
		
		//Create three Observer objects
		Observer LineChartObserver = (sender, event) -> System.out.println("Event: " + event + ", Sender: " + sender);
		Observer TableObserver = (sender, event) -> System.out.println("Event: " + event + ", Sender: " + sender);
		Observer ThreeDObserver = (sender, event) -> System.out.println("Event: " + event + ", Sender: " + sender);
		
		
		//Add LineChartObserver to DJIAObservable object
		observable.addObserver(LineChartObserver);
		int expectedCount = 1;
		assertEquals(expectedCount, observable.countObservers());
		
		//Add TableObserver to DJIAObservable object
		observable.addObserver(TableObserver);
		expectedCount = 2;
		assertEquals(expectedCount, observable.countObservers());
		
		observable.changeQuote(30000.44);
		observable.changeQuote(30050.99);
		
		//Add ThreeDObserver to DJIAObservable object
		observable.addObserver(ThreeDObserver);
		expectedCount = 3;
		assertEquals(expectedCount, observable.countObservers());
		
		//Remove ThreeDObserver from DJIAObservable
		observable.removeObserver(ThreeDObserver);
		expectedCount = 2;
		assertEquals(expectedCount, observable.countObservers());
		
		//Remove LineChartObserver from DJIAObservable
		observable.removeObserver(LineChartObserver);
		expectedCount = 1;
		assertEquals(expectedCount, observable.countObservers());
		
		//Remove TableObserver from DJIAObservable
		observable.removeObserver(TableObserver);
		expectedCount = 0;
		assertEquals(expectedCount, observable.countObservers());
		
		
	}

}
