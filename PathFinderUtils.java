// Student Name:
// Student ID:
// Date:

package MyProject; 
import EE324StandardProjectClasses.*;

import java.util.ArrayList;
import java.util.List;   

public class PathFinderUtils {

	/**
	 * Exercise 1: Return a list of all points of a ShapeMap that are visible from a specified point p
	 * @author Edy Nastase
	 * @param p Reference point
	 * @param map The ShapeMap object
	 * @return List of all points visible from p
	 */
	
	public static List<Point2D> visibleFrom(Point2D p, ShapeMap map) {
		//list of visible points declared, so that it can be returned at the end
		List<Point2D> visiblePoint=new ArrayList<Point2D>();
		
		//for loop that goes through all the hull points without including source and destination
		for(int i =0; i<map.getAllPoints().size(); i++)
		{
			//variables used to keep track of the touches and intersections
			int t = 0;
			int inters = 0;
			//each hull point is declared as a Point2D object
			Point2D pnt = map.getAllPoints().get(i);
			
			
			//for each loop that goes through all the polygons within the map
			//attempts were made to use a normal for loop, but it most of the methods work with Point2D
			//not with Point2D[], which would have been required to form the polygons
			for(Polygon2D poly : map) {
				//boolean function touches returns true if the specified points touch each other
				//touches checks if the intersection of the interiors is empty, while the intersection
				//of their geometries is zero
				if(poly.getHull().touches(p, pnt)==true) 
					t++;
				//boolean function intersects returns true if the specified points intersect each other
				//intersection checks the case hull points cases, where two points might share a common point
				if(poly.getHull().intersects(p,pnt)==true)
					inters++;
			}

			//to add points in the visiblePoint list, one needs to check two specific cases
			//one where the source/destination point are a point on the hull are analyzed
			//this means that the hull will be touched and intersected only once
			//another where the hull points are analyzed
			//meaning that the hull be touched and intersected twice
			if (t==1&&inters==1||t==2&&inters==2)
				visiblePoint.add(pnt);
		}
		//the visiblePoint is being returned.
		return visiblePoint;
		
	}
	
	// Checks if a visible point is on the hull or not
	static int hull_or_not(List<Point2D> hull_points,	Point2D p) {
	
		for (int i=0;i<hull_points.size();i++) {
		
			if(hull_points.get(i).isEqual(p))
				return i;
			
		}
		return 0;
	}

	
	
	// Retrieve the hull points of the choice of map.
	static ShapeMap getHullPoints(ShapeMap map_choice) {
		ShapeMap map = new ShapeMap();
		for (Polygon2D poly : map_choice) {
			map.addPolygon(poly.getHull());
		}
		return map;
	}



}
