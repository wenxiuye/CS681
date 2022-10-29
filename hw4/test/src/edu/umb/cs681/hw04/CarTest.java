package edu.umb.cs681.hw04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class CarTest {
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
	void findMinPrice() {
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
		
		
		//use stream min() to get the minimum car price
		int minPrice = usedCar.stream()
				.mapToInt((Car car) -> (int) car.getPrice())
				.min()
				.getAsInt();
		
		int expectedMinPrice = (int) usedCar.stream()
				.sorted((Car car1, Car car2)->(int)(car1.getPrice() - car2.getPrice()))
				.collect(Collectors.toList())
				.get(0)
				.getPrice();
		
		//System.out.println("expectedMinPrice" + expectedMinPrice);
		//System.out.println("minPrice" + minPrice);
		
		assertEquals(expectedMinPrice, minPrice);
	}
	
	@Test
	void findMaxPrice() {
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

		//use stream max() to get the maximum car price
		int maxPrice = usedCar.stream()
				.mapToInt((Car car) -> (int) car.getPrice())
				.max()
				.getAsInt();
		
		int expectedMinPrice = (int) usedCar.stream()
				.sorted((Car car1, Car car2)->(int)(car2.getPrice() - car1.getPrice()))
				.collect(Collectors.toList())
				.get(0)
				.getPrice();
		
		
		//System.out.println("expectedMinPrice" + expectedMinPrice);
		//System.out.println("maxPrice" + maxPrice);
		assertEquals(expectedMinPrice, maxPrice);
	}
	@Test
	void findAveragePrice() {
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
		
		//use stream reduce method to calculate average of car price
		Integer averagePrice =(int) usedCar.stream()
				.map((Car car) -> car.getPrice())
				.reduce(new double[2], (result, price)->{ 
					return new double[] {(result[0] * result[1] + price) / (result[1]+1), ++result[1]};
				},(finalResult, intermediateResult)->finalResult)[0];
					
				/*double[] r = new double[2];
				r[0] = (result[0] * result[1] + price) / (result[1]+1);
				r[1] = ++result[1];
				return r;}, */
				
		//use stream map and average method to calculate average of car price
		int expectedAveragePrice = (int) usedCar.stream()
				.mapToInt((Car car) -> (int) car.getPrice())
				.average()
				.getAsDouble();
		
		
		//System.out.println("expectedaveragePrice" + expectedAveragePrice);
		//System.out.println("averagePrice" + averagePrice);
		
		assertEquals(expectedAveragePrice, averagePrice);
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
