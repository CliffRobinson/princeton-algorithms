import java.util.Arrays;

import edu.princeton.cs.algs4.ResizingArrayQueue;


public class BruteCollinearPoints {
	private static final int PERM_SIZE = 2;
	private static Point[] test = {
			new Point(0,0),
			new Point(1,1),
			new Point(2,2),
//			new Point(3,3),
//			new Point(4,4),
//			new Point(5,5)

	};
	private int n;
	private int Pn;
	private int truePn;
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
		this.n = points.length;
		this.Pn = 0;
		this.truePn = f(n)/(f(PERM_SIZE)* f(n-PERM_SIZE));
		System.out.printf("n is %d, Pn is %d\n", n, truePn);
		
		getPerms(points);
		
		if (Pn == truePn) {
			System.out.printf("Expected %d permutations, got %d, correct number found!\n", truePn, Pn);
		} else {
			System.out.printf("Expected %d permutations, got %d, incorrect number found\n", truePn, Pn);
		}
	}
	
	private void getPerms(Point[] points) {
		Point[] permSoFar = //{new Point(0,0), new Point(1,1)}; 
				new Point[0];
		int generation = 0;
		getPerms(points, permSoFar, generation);
	}
	
	private void getPerms(Point[] points, Point[] permSoFar, int generation) {
		generation++;
		//if (Pn == truePn) return;
		if (permSoFar.length == PERM_SIZE) {
			System.out.printf("New perm found: %s,\n", Arrays.toString(permSoFar));
			Pn++;
		} else {
			for (int i = 0; i < PERM_SIZE - permSoFar.length; i++) {
				//System.out.printf("Gen %d outer loop iteration %d start\n", generation, i);
				Point[] permNow = new Point[permSoFar.length+1];
				for (int j = 0; j < permSoFar.length; j++) {
					permNow[j] = permSoFar[j];
				}
				//System.out.printf("permNow is: %s\n",Arrays.toString(permNow));
				
				for (int k = permSoFar.length; k < points.length; k++) {
					System.out.printf("Gen %d inner loop iteration %d start\n", generation, k);
					permNow[permNow.length-1] = points[k];
					//System.out.printf("permNow is: %s\n",Arrays.toString(permNow));
					getPerms(points, permNow, generation);
					//System.out.printf("Gen %d inner loop iteration %d end\n", generation, k);
				}
				//System.out.printf("Gen %d outer loop iteration %d end\n", generation, i);
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

		System.out.printf("Array is: %s\n", Arrays.toString(test));
		BruteCollinearPoints b = new BruteCollinearPoints(test);
		
	}
	
}
