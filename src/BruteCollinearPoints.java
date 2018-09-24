import java.util.Arrays;

import edu.princeton.cs.algs4.ResizingArrayQueue;


public class BruteCollinearPoints {
	private static final int PERM_SIZE = 4;
	private static Point[] test = {
			new Point(0,0),
			new Point(1,1),
			new Point(2,2),
			new Point(3,3),
			new Point(4,4),
			new Point(5,5)

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
		//getIterativePerms(points);
		Point[] permSoFar = new Point[0];
		int nextIndex = 0;
		int generation = 0;
		getRecursivePerms(points, permSoFar, nextIndex, generation);
	}
	
	private void getRecursivePerms(Point[] points, Point[] permSoFar, int nextIndex, int generation) {
		generation++;
		if (permSoFar.length == PERM_SIZE) {
			System.out.printf("New perm found: %s,\n", Arrays.toString(permSoFar));
			Pn++;
		} else {
			int dI = points.length - PERM_SIZE;
			for (int i = nextIndex; i <= (permSoFar.length+dI); i++) {
				Point[] permNow = new Point[permSoFar.length + 1];
				for (int j = 0; j < permSoFar.length; j++) {
					permNow[j] = permSoFar[j];
				}
				permNow[permNow.length-1] = points[i];
				getRecursivePerms(points, permNow, i+1, generation);
			}	
			
		}
	}
	
	private void getIterativePerms(Point[] points) {
		int dI = points.length - 4; //PERM_SIZE;
		for (int i = 0; i <= dI; i++) {
			for (int j = (i+1) ; j <= (dI+1) ;j++) {
				for (int k = (j+1) ; k <= (dI+2) ;k++) {
					for (int l = k+1; l < points.length; l++) {
					Pn++;
					System.out.printf("Perm is: %s, %s, %s, %s\n",i,j,k,l );
					}
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

		System.out.printf("Array is: %s\n", Arrays.toString(test));
		BruteCollinearPoints b = new BruteCollinearPoints(test);
		
	}
	
}
