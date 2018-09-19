import java.util.Iterator;

import edu.princeton.cs.algs4.Stopwatch;

public class Suite {
	
	public static void test(Object expected, Object actual, int n) {
		System.out.printf("Test %d: ", n);
		if (expected.equals(actual)) {
			System.out.print("passed!");
		} else {
			System.out.print("FAILED.\n");
			System.out.printf("Expected: %s\n", expected.toString());
			System.out.printf("Received: %s\n", actual.toString());
		}
		System.out.print("\n");
	}
	
	public static void testArray(Object[] expected, Object[] actual, int n ) {
		System.out.printf("Test %d: ", n);
		boolean result = true;
		for (int i = 0; i< expected.length; i++) {
			if (!expected[i].equals(actual[i])) result = false;
		}
		
		if (result) {
			System.out.print("passed!");
		} else {
			System.out.print("FAILED.");
		}
		System.out.print("\n");
	}		
	
	public static void timeTrial(int n) {
		Deque<Integer> time = new Deque<Integer>();
		
		Stopwatch stop1 = new Stopwatch();
		for (int i = 0; i < n; i++) {
			time.addFirst(i);
		}
		System.out.printf("Time to add %d is 		%f", n, stop1.elapsedTime());
		
		Stopwatch stop1a = new Stopwatch();
		Iterator<Integer> t1i = time.iterator();
		while(t1i.hasNext()) {
			t1i.next();
		}
		System.out.printf("	Time to iterate thru %d is 	%f\n", n, stop1a.elapsedTime());
	}
	
}
