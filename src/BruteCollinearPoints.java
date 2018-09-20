import java.util.Arrays;

import edu.princeton.cs.algs4.ResizingArrayQueue;


public class BruteCollinearPoints {
	
	private int n;
	private int Pn;
	private ResizingArrayQueue<LineSegment> segments;
	
	private static int f(int n) {
		int product = 1;
		for (int i = n; i > 0; i--) {
			product *= i;
		}
		return product;
	}
	
	public BruteCollinearPoints(Point[] points) {
		//Need to get every permutation.
		n = points.length;
		Pn = 0;
		int truePn = f(n)/(f(4)* f(n-4));
		System.out.printf("n is %d, Pn is %d\n", n, truePn);
		
		getPerms(points);
		
		if (Pn == truePn) {
			System.out.println("Correct # of permutations");
		} else {
			System.out.println("incorrect # of permutations");
		}
	}
	
	private void getPerms(Point[] points) {
		Point[] permSoFar = new Point[0];
		getPerms(points, permSoFar);
	}
	
	private void getPerms(Point[] points, Point[] permSoFar) {
		if (permSoFar.length == 4) {
			System.out.printf("New perm found: %s, %s, %s, %s\n", permSoFar[0], permSoFar[1], permSoFar[2], permSoFar[3]);
			Pn++;
		} else {
			for (int i = 0; i < points.length; i++) {
				Point[] permNow = new Point[permSoFar.length+1];
				for (int j = 0; j < permSoFar.length; j++) {
					permNow[j] = permSoFar[j];
				}
				
				for (int k = permSoFar.length; k < points.length; k++) {
					permNow[permNow.length-1] = points[k];
					//System.out.println(Arrays.toString(permNow));
					getPerms(points, permNow);
				}
			}
		}
	}
	
	private void checkForLine(Point[] points) {
		System.out.println("Wrong number of points checked");
		Arrays.sort(points);
		if(	points[0].slopeTo(points[1]) == points[1].slopeTo(points[2]) && 	// a == b
			points[0].slopeTo(points[1]) == points[2].slopeTo(points[3]) ) {	// a == c
			this.segments.enqueue(new LineSegment(points[0], points[3]));
		}		
	}
	
	public int numberOfSegments() {
		return n;
	}
	
	public LineSegment[] segments() {
		LineSegment[] segs = new LineSegment[this.numberOfSegments()];
		int i = 0;
		while(!this.segments.isEmpty()) {
			segs[i] = this.segments.dequeue();
			i++;
		}
		return segs;
	}
	
	public static void main(String[] args) {

//		for (int i = 4; i < 8; i++) {
//			BruteCollinearPoints b = new BruteCollinearPoints(new Point[i]);
//		}
//		
		Point[] test = {
				new Point(0,0),
				new Point(1,1),
				new Point(2,2),
				new Point(3,3),
				new Point(4,4),

		};
		System.out.println(Arrays.toString(test));
		BruteCollinearPoints b = new BruteCollinearPoints(test);
		
	}
	
}
