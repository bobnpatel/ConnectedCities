package com.connected.util;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.connected.city.CityNode;
import com.connected.city.VisitStatus;

public class ConnectionFinder {

	private Map<String, CityNode> allConnections;
	private String source;
	private String destination;
	
	public ConnectionFinder(Map<String, CityNode> allConnections, String source, String destination) {
		this.allConnections = allConnections;
		this.source = source;
		this.destination = destination;
	}
	
	public String isConnected() {
		String isConnected = "no";
		CityNode city = allConnections.get(source);
		if (city == null || city.getConnectedCities().isEmpty())
			return isConnected;
		boolean connection = findConnection(city);
		return connection == true ? "yes" : "no";
	}
	
	private boolean findConnection(CityNode city) {
		Queue<CityNode> q = new LinkedList<>();
		
		for (CityNode node : city.getConnectedCities()) {
			node.resetStatus();
		}
		city.visited();
		
		q.add(city);
		while (!q.isEmpty()) {
			CityNode cityNode = q.poll();
			if (cityNode != null) {
				for (CityNode cNode : cityNode.getConnectedCities()) {
					if (cNode.getStatus() == VisitStatus.NOT_VISITED) {
						if (cNode.getCity().equals(destination))
							return true;
						else {
							cNode.visited();
							q.add(cNode);
						}
					}
				}
			}
		}
		return false;
	}
}
