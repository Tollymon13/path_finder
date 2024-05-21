package MyProject;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import EE324StandardProjectClasses.Dijkstra;
import EE324StandardProjectClasses.Point2D;
import EE324StandardProjectClasses.ShapeMap;
import edu.princeton.cs.introcs.StdDraw;

// Write any unit or temporary tests for path finder development here
// THIS CODE FILE IS NOT GRADED. - Don't put any code here that is part
// of your final path finder solution.

public class PathFinderTester {
	// Basic test to load and draw a map file
	public static List<Point2D> points_hull;

	public static void main(String args[]) throws InterruptedException {
		
		int anime = 0;
		String map_name = null;
	
		// Used for the command prompt
		for(int i=0;i<args.length;i++) {
			String s = args[i];
	
			//Animate
			if(s.contains("DEMO-MAP-1.TXT"))
				map_name="DEMO-MAP-1.TXT";
			
			else if(s.contains("DEMO-MAP-2.TXT")) 
				map_name="DEMO-MAP-2.TXT";
			
			else if(s.contains("DEMO-MAP-3.TXT")) 
				map_name="DEMO-MAP-3.TXT";
			
			else if(s.contains("TEST-MAP-0.TXT"))
				map_name="TEST-MAP-0.TXT";
			
			else if(s.contains("TEST-MAP-1.TXT")) 
				map_name="TEST-MAP-1.TXT";
			
			else if(s.contains("TEST-MAP-2.TXT")) 
				map_name="TEST-MAP-2.TXT";
			
			if(s.contains("-D")) 
				anime=1;

		}
		
		//Map choice
		ShapeMap map_choice = new ShapeMap(map_name); // src\\MAPS\\name_of_the_map
		// Convert to a map containing just HullPoints
		
		ShapeMap map_hull = new ShapeMap();
				map_hull=PathFinderUtils.getHullPoints(map_choice);
				
		// Get all the points, define that the last 2 points are the source and the destination 
		points_hull= new ArrayList<Point2D>(map_hull.getAllPoints());
		points_hull.add(map_choice.sourcePoint());
		points_hull.add(map_choice.destinationPoint());
				
		// Execution time code provided from the specification page
		for (int i=0;i<2;i++) {
			long startTime = System.currentTimeMillis();
			PathFinder.quickPath(map_hull,points_hull);
			long runTime = System.currentTimeMillis() - startTime;
			System.out.println("Run number: " + i);
			System.out.println("Execution time (ms) = " + runTime);
			System.out.println("Length of path found = " +PathFinder.Len);
			//error if i run more times.
				}
			
		// Set up canvas
		StdDraw.setCanvasSize(1000, 1000);

		//Draw the map (which is formed by the hull points) overlap the map0 (which is containing all the points) 
		map_hull.drawFilled(Color.magenta);
		map_choice.drawFilled(Color.ORANGE);


		// anime == 1 is used with the CMD
		// if this case is true, then lines between visible points are drawn
		if(anime==1) {
			// Visible points are drawn
			for (int i=0;i<points_hull.size();i++) {
					
				List<Point2D> points= new ArrayList<Point2D>();
				// Find all the visibile points and store them in a list
				points = PathFinderUtils.visibleFrom(points_hull.get(i), map_hull);
					
				// Using a for loop go around the list and draw lines between the node and its children
				// The drawTo function draws the lines
				// The draw function draws all the hull points
				// Note that the source point is red and the destination is green
					for (int j=0; j<points.size();j++) {
							points_hull.get(i).drawTo(points.get(j),Color.GRAY);
							points.get(j).draw(Color.BLACK, 0.025);
						}
						//	points_hull.get(i).draw(Color.BLACK, 0.025);
							map_choice.sourcePoint().draw(Color.RED, 0.025);
							map_choice.destinationPoint().draw(Color.GREEN, 0.025);
						}
				
		// Dijkstra Algorithm Animation
			for(int j=0;j<Dijkstra.counter-1;j++) {
				//sleep used to slow down the drawing process
			//	Thread.sleep(100);
				// Change colour of the edges that have been checked
				points_hull.get(Dijkstra.nodes[0][j]).drawTo(points_hull.get(Dijkstra.nodes[1][j]),Color.red, 0.005);
				// Change colour of the new nodes
				points_hull.get(Dijkstra.nodes[0][j]).draw(Color.cyan,0.025);			
					}
			}
					
				
		// Connect the links and create the quickets path
			for(int j=0;j<PathFinder.path.size()-1;j++) {
				points_hull.get(PathFinder.path.get(j)).drawTo(points_hull.get(PathFinder.path.get(j+1)),Color.BLACK,0.008);
				}
				
		//return original color source/destination
			map_choice.sourcePoint().draw(Color.RED, 0.025);
			map_choice.destinationPoint().draw(Color.GREEN, 0.025);

	}
}
