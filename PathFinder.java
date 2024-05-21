// Student Name:
// Student ID:
// Date:

package MyProject;

import java.util.ArrayList;
import java.util.List;

import EE324StandardProjectClasses.Dijkstra;
import EE324StandardProjectClasses.Point2D;
import EE324StandardProjectClasses.ShapeMap;
import EE324StandardProjectClasses.WeightedGraph;

//code used to find the shortest path

public class PathFinder {

	//list of the path positions in the form of integers
	//this is because all the positions are fixed to an integer number i.e. 1 2 3 
	static List<Integer> path;
	//variable that holds the length of the path
	static double Len;
	//counter
	static int counter;
	//starting point
	static int start_pnt;
	//destination point
	static int destination_pnt;
	
	//method that will be passed, to find the shortest path
	public static void quickPath(ShapeMap sample_map, List<Point2D> hull_points) {
		//Initialization of various variables
		Len=0;
		counter=0;
		start_pnt=0;
		destination_pnt=0;

		// Nodes in form of a matrix
		// The first [] represents whether the node is at tail or at head
		// Then, the second [] represents the size/amount of nodes that be accessed
		int[][] nodes=new int[2][hull_points.size()*hull_points.size()];
		
		// Array used to find the weight of each edge
		// This is used for Dijkstra Algorithm, in order to form the spanning tree
		double[] weight=new double [hull_points.size()*hull_points.size()];
		
		// For loop, that goes through all the hull points
		for (int i=0;i<hull_points.size();i++) {
			//Visible Points are stored
			List<Point2D> points= new ArrayList<Point2D>(PathFinderUtils.visibleFrom(hull_points.get(i), sample_map));
			// [0][1 2 3]
			// [1][2 4 5]
			// Go through all the visible points
			// Form nodes using the visible points
			for (int j=0; j<points.size();j++) {
				// marks all visited nodes 
				nodes[0][counter]=i; 
				// goes to unvisited nodes
				nodes[1][counter]=PathFinderUtils.hull_or_not(hull_points,points.get(j));
				// calculates weight between hull points and visible points
				weight[counter]=hull_points.get(i).distanceTo(points.get(j));
				counter++;
			}
		}

		// Using methods provided in the Week 7, we can add weight to our edges
		WeightedGraph G = new WeightedGraph(counter);
		for(int i=0;i<counter;i++) 
			G.addEdge(nodes[0][i],nodes[1][i],weight[i]); 

		// source is declared as the second last point 
		start_pnt=nodes[0][counter-1]-1; 
		// destination is the declared as the last point 
		destination_pnt=nodes[0][counter-1]; 

		// Dijkstra Scan code provided in the Week 7 lectures
		Dijkstra scan = new Dijkstra(G, start_pnt);
		
		// Insert into the path list, the ascending order of nodes, up to the destination
		path = new ArrayList<Integer>(scan.pathTo(destination_pnt));

		// The length of the path is being calculated
		// It uses the distance between the hull_points on the shortest path
		for(int j=0;j<path.size()-1;j++) {
			Len=Len+hull_points.get(path.get(j)).distanceTo(hull_points.get(path.get(j+1)));
		}
	}
}