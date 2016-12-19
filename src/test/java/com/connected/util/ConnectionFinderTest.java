package com.connected.util;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.connected.city.CityNode;

public class ConnectionFinderTest {

	Map<String, CityNode> connections = null;
	
	@Before
	public void setUp() throws Exception {
		String filename = "cities.txt";
		connections = new FileLoader().loadAllConnections(new File(filename));
	}
	
	@Test
	public void checkInDirectConnection() {
		String source = "Boston";
		String destination = "Hartford";
		
		ConnectionFinder finder = new ConnectionFinder(connections, source, destination);
		assertTrue("yes".equals(finder.isConnected()));
	}
	
	@Test
	public void checkDirectConnection() {
		String source = "Boston";
		String destination = "New York";

		ConnectionFinder finder = new ConnectionFinder(connections, source, destination);
		assertTrue("yes".equals(finder.isConnected()));
	}
	
	@Test
	public void checkNoConnection() {
		String source = "Boston";
		String destination = "Tampa";

		ConnectionFinder finder = new ConnectionFinder(connections, source, destination);
		assertTrue("no".equals(finder.isConnected()));
	}
	
	@Test
	public void checkDestinationNotInFile() {
		String source = "Boston";
		String destination = "Ypsilanti";

		ConnectionFinder finder = new ConnectionFinder(connections, source, destination);
		assertTrue("no".equals(finder.isConnected()));
	}

}
