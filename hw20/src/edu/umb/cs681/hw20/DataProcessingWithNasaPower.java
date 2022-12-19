package edu.umb.cs681.hw20;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DataProcessingWithNasaPower {
	
	public static void main(String[] args) throws IOException {
		
		List<List<Double>> points = new ArrayList<List<Double>>();
		
		Distance dis = new Distance();
		
		List<List<String[]>> all_files = new ArrayList<List<String[]>>();
		
		
		
		for (int i = 1; i < 10; i++)
		{
			Path path =  Paths.get("/Users/wenxiuye/Desktop/homwork_eclipse/hw6/src/edu/umb/cs681/hw06/loc"+ i + ".csv");
			try (Stream<String> lines = Files.lines(path)) {
				List<String[]> lines_list = lines.map((String line) -> line.split(",")).collect(Collectors.toList());
				
				//get the 
				double loc1_x = Double.parseDouble(lines_list.get(3)[0].split(" ")[3]);
				double loc1_y = Double.parseDouble(lines_list.get(3)[0].split(" ")[7]);
				
				List<Double> point = Arrays.asList(loc1_x, loc1_y);
				points.add(point);
				all_files.add(lines_list);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		//get location1 info
		List<String[]> list1 = all_files.get(0);
		List<Double> point1 = points.get(0);
		//get Surface Pressure data for location1
		List<String[]> surfacePressureList = list1.subList(28, 69);
		
		//get monthly Surface Pressure Average
		for (int i = 0; i < surfacePressureList.size(); i++) {
			Double averageSurfacePressure = Arrays.stream(surfacePressureList.get(i))
												.parallel()
												.skip(2)
												.mapToDouble(string ->Double.parseDouble(string))
												.average().getAsDouble();
			point1.get(0);
			System.out.println("At Location " + point1.get(0) + ", "+ point1.get(1) + ", the"+ " Surface Pressure Monthly Average in " + surfacePressureList.get(i)[1] + "is " + averageSurfacePressure);
		}
						
		
		DistanceMetric manhattan = (p1, p2) -> {
			double sumOfDifference = 0.0;
			for(int i=0; i < p1.size(); i++) {
					sumOfDifference += Math.abs( p1.get(i)-p2.get(i));
			}
			return Math.sqrt(sumOfDifference);
		};
		
		//Calculate distance between two location
		List<Double> location1 = points.get(0);
		List<Double> location2 = points.get(1);
		double distance = dis.get(location1, location2, manhattan);
		System.out.println("Distance between location1 and location2 is " + distance);
		
		
		//Calculate distance among different location
		List<List<Double>> expectedMatrix = dis.matrix(points, manhattan);
		System.out.println("Distance Metrix for all locations");
		for (List<Double> p : expectedMatrix)
			System.out.println(p);
	}

}
