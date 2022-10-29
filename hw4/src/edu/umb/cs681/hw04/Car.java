package edu.umb.cs681.hw04;

import java.util.ArrayList;

public class Car {
	
	private String make, model;
	private int mileage, year;
	private float price;
	private int dominationCount;
	
	public Car(String make, String model, int mileage, int year, float price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
		this.dominationCount = 0;
		
	}
	public int getDominationCount() 
	{
		return this.dominationCount;
	}
	
	public void setDominationCount(ArrayList<Car> usedCar) {
	
		int count = 0;
		for (Car car : usedCar)
		{
			if(this.getMileage() == car.getMileage() && this.getYear() == car.getYear() && this.getPrice() == car.getPrice())
				continue;
			if(this.getMileage() >= car.getMileage() && this.getYear() >= car.getYear() && this.getPrice() >= car.getPrice())
				count++;
		}
		this.dominationCount = count;
		
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getMileage() {
		return mileage;
	}
	
	public int getYear() {
		return year;
	}
	
	public float getPrice() {
		return price;
	}

}
