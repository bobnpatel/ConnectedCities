package com.connected.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.connected.city.CityNode;


public class FileLoader {

	private static final String DELIMITER = ",";

	
	public Map<String, CityNode> loadAllConnections(File file) {
		
		Map<String, CityNode> allCityConnections = new HashMap<>();
		String line;
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(file));
			while ((line = bReader.readLine()) != null) {
				String[] connectedCities = parseLine(line);
				if (connectedCities.length == 2) 
					makeConnection(allCityConnections, connectedCities);
			}
			bReader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		finally {
			try {
		        //need to check for null
		        if ( bReader != null ) {
		        	bReader.close();
		        }
		      }
		      catch(IOException ex){
		    	System.out.println("Problem occured. Cannot close reader : " + ex.getMessage());
				System.exit(1);
		      }
		}
		return allCityConnections;
	}
	
	
	private void makeConnection(Map<String, CityNode> allCityConnections, String[] connectedCities) {
		
		String source = connectedCities[0];
		String destination = connectedCities[1];
		CityNode sourceCity = allCityConnections.get(source);
		if ( sourceCity == null) {
			sourceCity = new CityNode(source);
			allCityConnections.put(source, sourceCity);
		}
		
		CityNode destCity = allCityConnections.get(destination);
		if (destCity == null) {
			destCity = new CityNode(destination);
			allCityConnections.put(destination, destCity);
		}
		sourceCity.addConnection(destCity);
		destCity.addConnection(sourceCity);
	}
	
	private String[] parseLine(String line) {
		return line.replace(DELIMITER+" ", DELIMITER).split(DELIMITER);
	}
	
}
