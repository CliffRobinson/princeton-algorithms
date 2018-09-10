import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class DequeTester {

	private static void test(Object expected, Object actual, int n) {
		System.out.printf("Test %d: ", n);
		if (expected.equals(actual)) {
			System.out.print("passed!");
		} else {
			System.out.print("FAILED.");
		}
		System.out.print("\n");
	}
	
	private static void testArray(Object[] expected, Object[] actual, int n ) {
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
	
	public static void main(String[] args) {
		Deque<String> d = new Deque<String>();
		// Test 1: isEmpty returns correctly for new deque.
		int n = 1;
		Object expected = (boolean) true;
		Object actual = (boolean) d.isEmpty();
		test(expected, actual, n++);

		// Test 2: size returns correctly for new deque.
		expected = (int) 0;
		actual = (int) d.size();
		test(expected, actual, n++);

		// Test 3: isEmpty returns correctly with one item added.
		d.addFirst("One");
		expected = (boolean) false;
		actual = d.isEmpty();
		test(expected, actual, n++);

		// Test 4: size returns correctly with one item added.
		expected = (int) 1;
		actual = d.size();
		test(expected, actual, n++);

		// Test 5: removeFirst returns "One".
		expected = (String) "One";
		actual = (String) d.removeFirst();
		test(expected, actual, n++);
		
		//Test 6: addFirst One, Two, Three, expect One, Two, Three, from removeLast.
		expected = (String) "OneTwoThree";
		d.addFirst("One");
		d.addFirst("Two");
		d.addFirst("Three");
		
		actual = (String) d.removeLast()+d.removeLast()+d.removeLast();
		test(expected, actual, n++);

		//Test 7: addLast One, Two, Three, expect One, Two, Three, from removeFirst.
		expected = (String) "OneTwoThree";
		d.addLast("One");
		d.addLast("Two");
		d.addLast("Three");
		
		actual = (String) d.removeFirst()+d.removeFirst()+d.removeFirst();
		test(expected, actual, n++);
		
		//Test 8: Time to get big! Are 1000 numbers the same in this puppy?
		Deque<Integer> id = new Deque<Integer>();
		int num = 1000;
		Object[] expectedArray = new Integer[num];
		Object[] actualArray = new Integer[num];
		
		Stopwatch stoppy = new Stopwatch();
		for (int i = 0; i < num; i++) {
			Integer rando = StdRandom.uniform(1000);
			expectedArray[i] = rando;
			id.addFirst(rando);
		}
		System.out.printf("Time elapsed: %f\n", stoppy.elapsedTime());
		for (int i = 0; i< num; i++) {
			actualArray[i] = id.removeLast();
		}
		
		System.out.printf("Time elapsed: %f\n", stoppy.elapsedTime());
		testArray(expectedArray, actualArray, n++);		
	}

}
