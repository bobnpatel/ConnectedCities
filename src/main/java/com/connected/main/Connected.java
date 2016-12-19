package com.connected.main;

//import java.io.File;
//import java.util.Map;
//
//import com.connected.util.ConnectionFinder;
//import com.connected.util.FileLoader;
//import com.connected.city.CityNode;
//
//public class Connected {
//
//	
//	public static void main(String[] args) {
//		
//		if (args.length != 3) {
//			System.out.println("Usage java Connected <filename> <cityname1> <cityname2>");
//			System.exit(1);
//		}
//		String fileName = args[0];
//		String sourceCity = args[1].trim();
//		String destCity = args[2].trim();
//		
//		if (sourceCity.isEmpty()) {
//			System.out.println("Source City not specified");
//			System.exit(1);
//		}
//			
//		if (destCity.isEmpty()) {
//			System.out.println("Destination City not specified");
//			System.exit(1);
//		}
//
//		// Source and Destination are same.
//		if (sourceCity.equals(destCity)) {
//			System.out.println("yes");
//			System.exit(0);
//		}
//			
//		File dataFile = new File(fileName);
//		
//		FileLoader fileLoader = new FileLoader();
//		Map<String, CityNode> allConnections = fileLoader.loadAllConnections(dataFile);
//		
//		ConnectionFinder finder = new ConnectionFinder(allConnections, sourceCity, destCity);
//		System.out.println(finder.isConnected());
//	}
//}


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Connected {

	public static void main(String[] args) {
		
		if(args.length!=3){
			System.err.println("Invalid Arguments , Usage:Connected <<filename>> <origin> <destination>");
			System.exit(-1);
		}
		Graph cityGraph = buildCityGraph(args[0]);
		if(isConnected(cityGraph,args[1],args[2])){
			System.out.println("Yes");
		}else{
			System.out.println("No");
		}
		
	}
	
	public static Graph buildCityGraph(String fileName){
		
		Graph g = new Graph();
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(new File(fileName)));
			String line = br.readLine();
			while(line!=null && !line.isEmpty()){
				if(line.indexOf(",")!=-1){
					String[] cities = line.split(",");
					g.addEdge(cities[0].trim(), cities[1].trim());
					g.addEdge(cities[1].trim(), cities[0].trim());
					line=br.readLine();
				}
				
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found:"+e.getMessage());
		}catch(IOException e){
			System.err.println("Exception while reading the file:"+e.getMessage());
		}finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return g;
	}
	
	private static boolean isConnected(Graph graph , String origin,String destination){
		  Set<String> visited = new HashSet<String>();
		  Queue<String> queue = new LinkedList<String>();
		  boolean isConnected = false;
		  queue.add(origin);
		  visited.add(origin);
		  while(!queue.isEmpty()){
			  String next = queue.remove(); 
		        for (String neighbor : graph.getNeighbors(next)) {
		            if (!visited.contains(neighbor)) {
		                queue.add(neighbor);
		                visited.add(neighbor);
		            }
		        }
		        if(visited.contains(destination)){
            		isConnected = true;
            		break;
            	}
		  }
		  return isConnected;
		
	}
	

}
class Graph{
	 private Map<String, List<String>> edges = new HashMap<String, List<String>>();

	    public void addEdge(String origin, String dest) {
	        List<String> originNeighbors = this.edges.get(origin);
	        if (originNeighbors == null) {
	        	originNeighbors = new ArrayList<String>();
	            this.edges.put(origin,originNeighbors );
	        }
	        originNeighbors.add(dest);
	    }

	    public Iterable<String> getNeighbors(String vertex) {
	        List<String> neighbors = this.edges.get(vertex);
	        if (neighbors == null) {
	            return Collections.emptyList();
	        } else {
	            return Collections.unmodifiableList(neighbors);
	        }
	    }
	}