package edu.umb.cs681.hw11;

import java.util.Arrays;
import java.util.List;

public record Position(double latitude, double longitude, double altitude) {
	
	public List<Double> coordinate(){
		
		List<Double> loc = Arrays.asList(this.latitude, this.longitude, this.altitude);
		return loc;
	}
	public Position change(double newLatitude, double newLongitude, double newAltitude) {
		
		return new Position (newLatitude, newLongitude, newAltitude);
	}
	
	public double distanceTo(Position position) {
		
		double distance;
		
		List<Double> point1 = Arrays.asList(this.latitude, this.longitude, this.altitude);
		List<Double> point2 = Arrays.asList(position.latitude, position.longitude, position.altitude);
		
		double sumOfDifference = 0.0;
		for(int i=0; i < point1.size(); i++) {
			sumOfDifference += Math.abs( point1.get(i)- point2.get(i));
		}
		
		distance = Math.sqrt(sumOfDifference);
		System.out.print("Distance between two locations: " + distance);
		
		return distance;
	}
	public String toString() {
		
		return this.latitude +", " + this.longitude + ", " + this.altitude;
	}

}
