import java.util.Arrays;

import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.Merge;

public class FastCollinearPoints {
	
	private Point[] points;
	private int n;
	private int numberOfSegments;
	private LinkedQueue<LineSegment> segmentQueue;
	private LineSegment[] segments;
	private static final int LINE_LENGTH = 4;
	
	public FastCollinearPoints(Point[] points) {
		this.points = points;
		this.numberOfSegments = 0;
		this.n = points.length;
		this.segmentQueue = new LinkedQueue<LineSegment>();
		
		Arrays.sort(points);
		
		//Find all segments.
		
		for (Point point : points) {														//For every point, which we will refer to as the origin
			Point[] tempPoints = points.clone();											//create a copy of the points
			Arrays.sort(tempPoints, point.slopeOrder()); 									//And sort the array copy by slope to origin
			//API says this is stable, so equal points should still be in natural order.
			double[] slopes = new double[tempPoints.length];								
			
			for (int i = 0; i < slopes.length; i++) {										//Calculate and store the slopes relative to our origin point
				slopes[i] = point.slopeTo(tempPoints[i]);
			}
			
			int i = 0;
			int j = 1;
			while (i < (tempPoints.length+2 - LINE_LENGTH)) {								//Then for each other point
				if (slopes[i] == slopes[j]) {												//check if it has the same slope as the next point
					j++;
					if (j == tempPoints.length) {
						i++;
						j = i+1;
					}
				} else {
					if (j-i >= (LINE_LENGTH-1)) {
						System.out.println("Found a new segment");
						numberOfSegments++;
						segmentQueue.enqueue(new LineSegment(tempPoints[i], tempPoints[j-1]));
					}
					i++;
					j = i+1;
				}
			}
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
	

}
