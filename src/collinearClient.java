import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class collinearClient {
	public static void main(String[] args) {

	    // read the n points from a file
	    In in;
		try {
			in = new In(args[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String file = "collinearData/" + "grid4x4.txt";
			in = new In(file);
		}
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	        System.out.printf("Point %d is %s\n", i, points[i].toString());
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	        //System.out.println("Printing: " + p);
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    System.out.println("Fast: ");
	    FastCollinearPoints collinearA = new FastCollinearPoints(points);
	    System.out.printf("Fast found %d segments.\n", collinearA.numberOfSegments());
	    System.out.println("Brute: ");
	    BruteCollinearPoints collinearB = new BruteCollinearPoints(points);
	    System.out.printf("Brute found %d segments.\n", collinearB.numberOfSegments());
	    for (LineSegment segment : collinearB.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}
