package edu.umb.cs681.hw06;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class DataProcessingWithNasaPowerTest {

    @Test
    void test() throws IOException {
        List<List<Double>> points = new ArrayList<List<Double>>();
        
        Distance dis = new Distance();
        
        List<List<String[]>> all_files = new ArrayList<List<String[]>>();
        
        
        
        for (int i = 1; i < 10; i++)
        {
            Path path =  Paths.get("data/loc" + i +  ".csv");
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
        
        
        
        for(int i = 0; i < all_files.size(); i++) {
            List<String[]> data = all_files.get(i);
            List<Double> loc_points = points.get(i);
            //get Surface Pressure data for location1
            List<String[]> surfacePressureList = data.subList(28, 69);
            List<String[]> earthSkinTemperatureList = data.subList(70, 110);
            
            //get monthly Surface Pressure Average
            for (int j = 0; j  < surfacePressureList.size(); j++) {
                Double averageSurfacePressure = Arrays.stream(surfacePressureList.get(j)).skip(2).mapToDouble(string ->Double.parseDouble(string)).average().getAsDouble();
                System.out.println("At Location " + loc_points.get(0) + ", "+ loc_points.get(1) + ", the"+ " Surface Pressure Monthly Average in " + surfacePressureList.get(j)[1] + " is " + averageSurfacePressure);
            }
            
            
            for (int j = 0; j  < earthSkinTemperatureList.size(); j++) {
                Double maxEarthSkinTemperatureList = Arrays.stream(surfacePressureList.get(j)).skip(2).mapToDouble(string ->Double.parseDouble(string)).max().getAsDouble();
                System.out.println("At Location " + loc_points.get(0) + ", "+ loc_points.get(1) + ", the"+ " Maxiumn Monthly Earth Skin Temperature in " + surfacePressureList.get(j)[1] + " is " + maxEarthSkinTemperatureList);
            }
        }
    
        DistanceMetric manhattan = (p1, p2) -> {
            double sumOfDifference = 0.0;
            for(int i=0; i < p1.size(); i++) {
                    sumOfDifference += Math.abs( p1.get(i)-p2.get(i));
            }
            return Math.sqrt(sumOfDifference);
        };
        
        Manhattan manhattanDistance = new Manhattan();
        //Calculate the distance between two points using Manhattan distance metric
        
        //check to see if the distance matches
       
        
        //Calculate distance between two location
        for (int i = 0; i < points.size() - 1; i++) {
            List<Double> location1 = points.get(i);
            for( int j = i + 1; j < points.size(); j++) {
                List<Double> location2 = points.get(j);
                double actualDistance = dis.get(location1, location2, manhattan);
                double expectedDistanceBetweenTwoPoints = manhattanDistance.distance(location1, location2);
                
                assertEquals(expectedDistanceBetweenTwoPoints, actualDistance);
                System.out.println("Distance between location " + location1.get(0) + ", "+ location1.get(1 )
                + " and location " + location2.get(0) + ", "+ location2.get(1) + " is " + actualDistance);
            }
        }
        
        //get the matrix using Manhattan Distance Matrix created by Manhattan Class
        List<List<Double>> expectedMatrix = dis.matrix(points, manhattanDistance);
        //get the matrix using Manhattan Distance Matrix created by lambda expression      
        //Calculate distance among different location
        List<List<Double>> actualMatrix = dis.matrix(points, manhattan);
        assertArrayEquals(convertListofListToString(expectedMatrix), convertListofListToString(actualMatrix));
        
        System.out.println("Distance Metrix for all locations");
        expectedMatrix.stream().forEach(System.out::println);
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
