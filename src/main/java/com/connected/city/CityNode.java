package com.connected.city;

import java.util.ArrayList;
import java.util.List;

public class CityNode {

	private final String city;
	private List<CityNode> connectedCities;
	private VisitStatus status;
	
	public CityNode (String city) {
		this.city = city;
		this.connectedCities = new ArrayList<>();
		this.status = VisitStatus.NOT_VISITED;
	}

	public String getCity() {
		return city;
	}

	public List<CityNode> getConnectedCities() {
		return connectedCities;
	}

	public VisitStatus getStatus() {
		return status;
	}

	public void visited () {
		this.status = VisitStatus.VISITED;
	}
	
	public void resetStatus() {
		this.status = VisitStatus.NOT_VISITED;
	}
	
	public void addConnection(CityNode city) {
		if (!connectedCities.contains(city))
			connectedCities.add(city);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityNode other = (CityNode) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		return true;
	} 
}
