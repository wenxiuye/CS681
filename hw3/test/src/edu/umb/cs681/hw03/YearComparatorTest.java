package edu.umb.cs681.hw03; 

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class YearComparatorTest {
	Car car1 = new Car("Audi", "A4", 136636, 2000, 4880 );
	Car car2 = new Car("Audi", "A4", 84297, 2001, 7995 );
	Car car3 = new Car("Audi", "A6", 84272, 2004, 7998 );
	Car car4 = new Car("Audi", "A4", 78321, 2003, 10697 );
	Car car5 = new Car("Audi", "A4", 98362, 2002, 10900 );
	Car car6 = new Car("Audi", "A6", 0, 2020, 37897 );
	Car car7 = new Car("Audi", "A4", 6822, 2007, 24995 );
	Car car8 = new Car("Audi", "A4", 10120, 2005, 33497 );
	Car car9 = new Car("Audi", "A4", 12118, 2009, 39877);
	Car car10 = new Car("Audi", "S5", 16492, 2008, 44995 );

	@Test
	void sortCarByYearAscendingOrder() {
		ArrayList<Car> usedCar = new ArrayList<Car>();
		usedCar.add(car1);
		usedCar.add(car2);
		usedCar.add(car3);
		usedCar.add(car4);
		usedCar.add(car5);
		usedCar.add(car6);
		usedCar.add(car7);
		usedCar.add(car8);
		usedCar.add(car9);
		usedCar.add(car10);
		
		//lower to higher
		//Collections.sort(usedCar, new YearComparator<Car>());
		
		ArrayList<Car> sortedCars = (ArrayList<Car>) usedCar.stream()
				.sorted((Car car1, Car car2)-> car1.getYear() - car2.getYear())
				.collect(Collectors.toList());

		for (Car c : sortedCars) {
			System.out.println(c.getYear());
		}
		
		ArrayList<Car> expectedUsedCarList = new ArrayList<Car>();
		expectedUsedCarList.add(car1);
		expectedUsedCarList.add(car2);
		expectedUsedCarList.add(car5);
		expectedUsedCarList.add(car4);
		expectedUsedCarList.add(car3);
		expectedUsedCarList.add(car8);
		expectedUsedCarList.add(car7);
		expectedUsedCarList.add(car10);
		expectedUsedCarList.add(car9);
		expectedUsedCarList.add(car6);
		
		String[] expectedSortedUsedCarList = convertArrayListToString(expectedUsedCarList);
		String[] actualSortedUsedCarList = convertArrayListToString(sortedCars);
		
		assertArrayEquals(expectedSortedUsedCarList, actualSortedUsedCarList);
		
		
	}
	
	@Test
	void findMedian() {
		
		ArrayList<Car> usedCar = new ArrayList<Car>();
		usedCar.add(car1);
		usedCar.add(car2);
		usedCar.add(car3);
		usedCar.add(car4);
		usedCar.add(car5);
		usedCar.add(car6);
		usedCar.add(car7);
		usedCar.add(car8);
		usedCar.add(car9);
		usedCar.add(car10);
		
		ArrayList<Car> sortedCars = (ArrayList<Car>) usedCar.stream()
				.sorted((Car car1, Car car2)-> car1.getYear() - car2.getYear())
				.collect(Collectors.toList());
		
		//find the median
		int median = sortedCars.size() % 2 == 1 ? 
				sortedCars.get(sortedCars.size()/2).getYear() : 
					(sortedCars.get(sortedCars.size()/2 - 1).getYear() + sortedCars.get(sortedCars.size()/2).getYear()) /2 ;
		
		assertEquals(2004, median);
		
		
		
	}
	
	private String[] convertArrayListToString(ArrayList<Car> cars) {
		
		String[] list = new String [cars.size()];
		
		int count = 0; 
		for(Car car : cars) {
			list[count] = car.getMake() + " " + car.getModel() + " " + car.getMileage() + "  "+ car.getYear() + " " + car.getPrice();
			count++;
		}
		return list;
	}

}
