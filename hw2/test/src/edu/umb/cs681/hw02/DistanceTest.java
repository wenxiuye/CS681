package edu.umb.cs681.hw02;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class DistanceTest {

	@Test
	void testForTwoPoints() {
		
		//Create a Distance object
		Distance dis = new Distance();
		//Create two points
		List<Double> point1 = Arrays.asList(2.0, 3.0);
		List<Double> point2 = Arrays.asList(5.0, 7.0);
		
		//Create Manhattan distance metric using Lambda expression
		DistanceMetric manhattan = (p1, p2) -> {
			double sumOfDifference = 0.0;
			for(int i=0; i < p1.size(); i++) {
					sumOfDifference += Math.abs( p1.get(i)-p2.get(i));
			}
			return Math.sqrt(sumOfDifference);
		};
		//Call the get method with two points and the Manhattan distance metric
		//to get the Manhattan distance between two points
		double distanceBetweenTwoPoints = dis.get(point1, point2, manhattan);	
		
		//Create a Manhattan object
		//I use Manhattan class for testing
		Manhattan manhattanDistance = new Manhattan();
		//Calculate the distance between two points using Manhattan distance metric
		double expectedDistanceBetweenTwoPoints = manhattanDistance.distance(point1, point2);
		
		//check to see if the distance matches
		assertEquals(expectedDistanceBetweenTwoPoints, distanceBetweenTwoPoints);
		
		
		
		//Create a list of points
		List<List<Double>> points = new ArrayList<List<Double>>();
		points.add(point1);
		points.add(point2);
		
		//get the matrix using Manhattan Distance Matrix created by Manhattan Class
		List<List<Double>> expectedMatrix = dis.matrix(points, manhattanDistance);
		//get the matrix using Manhattan Distance Matrix created by lambda expression
		List<List<Double>> actualMatrix = dis.matrix(points, manhattan);
		
		assertArrayEquals(convertListofListToString(expectedMatrix), convertListofListToString(actualMatrix));

	
	}

	@Test
	void testForFiveThreeDimensionalPoints() {
		
		Distance dis = new Distance();
		List<Double> point1 = Arrays.asList(2.0, 3.0, 1.0);
		List<Double> point2 = Arrays.asList(5.0, 7.0, 2.0);
		List<Double> point3 = Arrays.asList(2.0, 3.0, 8.0);
		List<Double> point4 = Arrays.asList(5.0, 7.0, 10.0);
		List<Double> point5 = Arrays.asList(2.0, 3.0, 5.0);
		

		DistanceMetric manhattan = (p1, p2) -> {
			double sumOfDifference = 0.0;
			for(int i=0; i < p1.size(); i++) {
					sumOfDifference += Math.abs( p1.get(i)-p2.get(i));
			}
			return Math.sqrt(sumOfDifference);
		};
		
		Manhattan manhattanDistance = new Manhattan();
		
		double distanceBetweenTwoPoints = dis.get(point1, point2, manhattan);	
		double expectedDistanceBetweenTwoPoints = dis.get(point1, point2, manhattanDistance);		
		//check to see if the distance matches
		assertEquals(expectedDistanceBetweenTwoPoints, distanceBetweenTwoPoints);
		
		//Create a list of points
		List<List<Double>> points = new ArrayList<List<Double>>();
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		points.add(point5);
		
		//get the matrix using Manhattan Distance Matrix created by Manhattan Class
		List<List<Double>> expectedMatrix = dis.matrix(points, manhattanDistance);
		//get the matrix using Manhattan Distance Matrix created by lambda expression
		List<List<Double>> actualMatrix = dis.matrix(points, manhattan);
		
		assertArrayEquals(convertListofListToString(expectedMatrix), convertListofListToString(actualMatrix));

	
	}
	
	private String[] convertListofListToString(List<List<Double>> matric) {
		
		String[] list = new String [matric.size()];
		
		int count = 0; 
		for(List<Double> m : matric) {
			list[count] = m.toString();
			count++;
		}
		return list;
	}
}
