import java.util.Arrays;

import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
	
	private int numberOfSegments;
	private LinkedQueue<LineSegment> segmentQueue;
	private LineSegment[] segments;
	private static final int LINE_LENGTH = 4;
	
	public FastCollinearPoints(Point[] inputPoints) {
		
		if (inputPoints == null) throw new IllegalArgumentException("Points array is Null");
		Point[] points = inputPoints.clone();
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) throw new IllegalArgumentException("A point is Null");
		}
		Arrays.sort(points);
		for (int i = 0; i < points.length; i++) {
			if (i+1 < points.length && points[i].equals(points[i+1])) throw new IllegalArgumentException("A point is repeated");
		}
			
		this.numberOfSegments = 0;
		this.segmentQueue = new LinkedQueue<LineSegment>();
				
		//Find all segments.
		
		for (int origin = 0; origin < points.length; origin++) {														//For every point, which we will refer to as the origin
			//System.out.printf("For loop starting, Origin point is: %s\n", points[origin].toString());
			Point[] tempPoints = Arrays.copyOfRange(points, origin, points.length);									//create a copy of the points
			Arrays.sort(tempPoints, points[origin].slopeOrder()); 									//And sort the array copy by slope to origin
			//API says this is stable, so equal points should still be in natural order.
			double[] slopes = new double[tempPoints.length];								
			
			for (int i = 0; i < slopes.length; i++) {										//Calculate and store the slopes relative to our origin point
				slopes[i] = points[origin].slopeTo(tempPoints[i]);
			}
			
			int i = 1;
			int j = 2;
			while (i <= (tempPoints.length - (LINE_LENGTH-1))) {								//Then for each other point
				//System.out.printf("While loop starting: I is now: %d, J is now: %d\n", i, j);
				//System.out.printf("i is: %d, while loop max condition is: %d\n", i, tempPoints.length - (LINE_LENGTH - 1));
				//System.out.printf("Comparing slope of %s:%f to slope of %s:%f\n", tempPoints[i].toString(), slopes[i], tempPoints[j].toString(), slopes[j] );
				
				if (slopes[i] == slopes[j]) {												//check if it has the same slope as the next point
					//System.out.println("Slopes are equal, incrementing j");
					j++;
					if (j == tempPoints.length) {
						//System.out.println("A set of equal slopes is at the end of the array");
						setOfEqualSlopes(tempPoints, i,j-1);
						i++;
						j = i+1;
					} else {
						//Don't think I need to do anything here. 
					}
				} else {
					//System.out.println("Slopes are not equal");
					if(setOfEqualSlopes(tempPoints, i,j-1)) {			
						//System.out.printf("previous %d slopes made a line", (j-i));
						i = j - 1;
					} else {
						//System.out.printf("previous %d slopes did not make a line", (j-i));
						i++;
						j = i+1;
					}					
				}
				//System.out.printf("While loop finishing: I is now: %d, J is now: %d\n", i, j);
			}
			//System.out.println("For loop finishing");
		}
		
		//Put the found segments into an array
		
		this.segments = new LineSegment[numberOfSegments];
		int i = 0;
		while (!segmentQueue.isEmpty()) {
			segments[i] = segmentQueue.dequeue();
			i++;
		}
		
		
	}
	
	public LineSegment[] segments() {
		return segments;
	}
	
	public int numberOfSegments() {
		return numberOfSegments;
	}
	
	private boolean setOfEqualSlopes(Point[] points, int i, int j) {
		//System.out.printf("Slopes between %d and % d are equal, seeing if that's big enough.\n", i,j);
		if (j - i >= (LINE_LENGTH-2) ) {
			LineSegment newSegment = new LineSegment(points[0], points[j]);
			System.out.printf("NEW SEGMENT FOUND: %s\n", newSegment.toString());
			numberOfSegments++;
			segmentQueue.enqueue(newSegment);
			newSegment.draw();
			StdDraw.show();
			return true;
		} else {
			return false;
		}
	}
	
	
}
