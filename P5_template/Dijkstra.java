import java.io.*;
import java.util.*;

public class Dijkstra {

	private ArrayList<String> cities = new ArrayList<String>(); //store the list of nodes
	
	/**
		read the file that contains the nodes and edges
		@param sf : the File
	*/
	public void readMap(File sf) {
		try 
		{
			Scanner sc = new Scanner(sf);
			// your code ...
			
			
		}
		catch (IOException ioE) 
		{}
	}
	
	public String[] getCities() {
		String[] c = new String[cities.size()];
		
		for (int i=0; i<cities.size(); i++) {
			c[i]=cities.get(i); 
		}
		Arrays.sort(c);
		
		return c;
	}
	
	public String getShortestPath(String start, String end) {
		String report = null;
		
		// your code
		
		return report;
	}
	// your code
	// you'll need many private methods
	
}